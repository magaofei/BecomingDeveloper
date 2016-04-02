package com.cduestc.scoreinquiry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.message.BufferedHeader;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import android.os.Handler;


public class httpUtil {
	
	
	static  HashMap<String, Integer> scores=new HashMap<String, Integer>();
	private static String cookie;
	private static final String quaryPath="http://newjw.cduestc.cn/bxqcjcxAction.do";
	
	public static String sendPost(Handler handler,String ID,String MM) throws IOException {
		URL url = new URL("http://newjw.cduestc.cn/loginAction.do");
		HttpURLConnection conn =(HttpURLConnection)url.openConnection();	
		conn.setDoOutput(true);
		conn.getOutputStream().write(("zjh="+ID+"&mm="+MM).getBytes());
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"gb2312"));
		StringBuffer response=new StringBuffer();
		String line;		
		while((line=reader.readLine())!=null){
			response.append(line);
			//System.out.println("test:"+reader.readLine());
		}
		cookie = conn.getHeaderField("Set-Cookie");
		reader.close();	
		conn.disconnect();
		
		for(int page=1;page<5;page++){
			if(!getData(quaryPath, "totalrows=21&page="+page+"&pageSize=20")){
				break;
			}
		}
		
	 	  handler.sendEmptyMessage(1);
	 	  return response.toString();
	}
	
	public static String sendGet(String urlPath,String params){
		try{
			URL url=new URL(urlPath+(params!=null?"?"+params:""));
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			//��cookie����
			conn.setRequestProperty("Cookie", cookie);
			conn.setDoOutput(true);
			conn.connect();
			BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"gb2312"));
			String line;
			Boolean flag=false;
			Integer order=0;
			StringBuffer result=new StringBuffer();
			while((line=reader.readLine())!=null){
				line=line.trim().toLowerCase();
					if(line.contains("<table")&&order++==6||flag){
						flag=true;
						if(line.toLowerCase().startsWith("</table"))	flag=false;
						if(!line.startsWith("<thead")){
							result.append(line.contains("&bnsp")?line.replace("&nbsp;", ""):line+(flag?"\n":""));
						}
					}
			}
			reader.close();
			conn.disconnect();
			return result.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "���Ӵ���";
		}
		
	}
	public static  Boolean getData(String url, String parms) throws IOException {
		try {
			String data = sendGet(quaryPath, parms);
			if(data.equals("���Ӵ���")){
				return false;
			}else{
				Document document=DocumentHelper.parseText(data);
				return parseDocument(document);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//�洢�ɼ�����
	@SuppressWarnings("unchecked")
    private static  Boolean parseDocument(Document document) {
        //ȡ���ĵ���Ԫ���µ�������Ԫ�أ�����<table>��ǩ�е�<tr>��ǩ����
        List<Element> elements = document.getRootElement().elements();
        //���Ϊ�գ�û�����ݣ�����
        if(elements.size()==0)  return false;
        //��1��ʼȡ���ų�<th>��
        for(int i=1;i<elements.size();i++){
            //ȡ��<tr>��ǩ��������Ԫ�أ���<td>
            List<Element> eles = elements.get(i).elements();
            //��3�д���ǿγ���
            String subject=eles.get(2).getText();
            //��7�д���ǳɼ�
            String temp = eles.get(6).getText().trim();
            //��û�гɼ�ʱ����Ϊ-1�֣����水����������Ҫ
            Integer score=temp.equals("")?-1:Integer.parseInt(temp);
            if(scores.containsKey(subject)){
                Integer tmp=scores.get(subject);
                score=tmp>score?tmp:score;
            }
            scores.put(subject, score);    

        }     
        return true;
    }	
}
