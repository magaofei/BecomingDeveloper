package cn.edu.tit.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.text.TextUtils;
import android.util.Log;
import cn.edu.tit.config.Config;

public class NetOperation {

	private static final String TAG = "NetOperation";

	/**
	 * 
	 * @param weburl
	 * @param cookies
	 *            ���ã���Ϊ�ָ�,k,v
	 * @param header
	 *            ���ã���Ϊ�ָ�
	 * @param params
	 *            ���ã���Ϊ�ָ�
	 * @return
	 */
	public static String getContentFromNetByPost(String weburl, String cookies,
			Map<String, String> headers, Map<String, String> paramsmap) {
		String result;
		try {
			// ����post����
			HttpPost httpPost = new HttpPost(weburl);
			// ����headers
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					httpPost.addHeader(key, headers.get(key));
				}
			}
			// ����cookies
			if (!TextUtils.isEmpty(cookies)) {
				httpPost.setHeader("Cookie", cookies);
			}
			HttpResponse httpResponse = null;
			// ����httpPost�������
			List<NameValuePair> postparams = new ArrayList<NameValuePair>();
			postparams = new ArrayList<NameValuePair>();
			if (paramsmap != null) {
				Set<String> keys = paramsmap.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					postparams.add(new BasicNameValuePair(key, paramsmap.get(key)));
				}
			}
			Log.e("NetOperations", "begin setEntity");
			httpPost.setEntity(new UrlEncodedFormEntity(postparams, Config.CHARSET_GBK));
			Log.e("NetOperations", "begin execute");
			httpResponse = new DefaultHttpClient().execute(httpPost);
			//System.out.println("ResponseCode:"
			//		+ httpResponse.getStatusLine().getStatusCode());
			Log.e("NetOperations", "end execute");
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// ��������ʹ��getEntity������÷��ؽ��
				Log.e(TAG, "�ɹ�����");

				result = EntityUtils.toString(httpResponse.getEntity());
				//System.out.println("result:" + result);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static String getContentFromNetByGet(String weburl, String cookies,
			Map<String, String> headers, Map<String, String> paramsmap) {
		
		String result;
		//�Ƚ���������List���ٶԲ�������URL���� 
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
		
		// ����httpPost�������
		if (paramsmap != null) {
			Set<String> keys = paramsmap.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				params.add(new BasicNameValuePair(key, paramsmap.get(key)));
			}
		}
		
		//�Բ������� 
		String param = URLEncodedUtils.format(params, "UTF-8"); 
		//��URL�����ƴ�� 
		HttpGet getMethod = new HttpGet(weburl + "?" + param); 

		// ����headers
		if (headers != null) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				getMethod.addHeader(key, headers.get(key));
			}
		}
		
		HttpClient httpClient = new DefaultHttpClient(); 
		
		try {
		    HttpResponse response = httpClient.execute(getMethod); //����GET���� 
		    //Log.e(TAG, "resCode = " + response.getStatusLine().getStatusCode()); //��ȡ��Ӧ�� 
		    //Log.e(TAG, "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//��ȡ��������Ӧ����
		    
		    BufferedReader in = null;
		    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
            in.close();  
            result = sb.toString(); 
            return result;
		    
		} catch (ClientProtocolException e) { 
		    e.printStackTrace(); 
		    return null;
		} catch (IOException e) { 
		    e.printStackTrace();
		    return null;
		}
	}
}
