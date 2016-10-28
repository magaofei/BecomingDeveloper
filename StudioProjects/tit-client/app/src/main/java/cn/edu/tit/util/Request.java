package cn.edu.tit.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import cn.edu.tit.atys.ClassTableActivity;
import cn.edu.tit.atys.LessonTableActivity;
import cn.edu.tit.atys.LoginActivity;

import android.graphics.drawable.Drawable;
import android.text.GetChars;
import android.util.Log;

/**
 * �����࣬��Ҫ����url��ȡͼƬ�������ķ���
 * 
 */
public class Request {
	public static InputStream HandlerData(String url) {
		InputStream inStream = null;

		try {
			URL feedUrl = new URL(url);
			URLConnection conn = feedUrl.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(10 * 1000);
			inStream = conn.getInputStream();
			Map<String, List<String>> map = conn.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				Log.e("header", "Key : " + entry.getKey() + 
                        " ,Value : " + entry.getValue());
			}
		}
		catch (UnknownHostException e){
			Log.e("load validate img error", e.toString() + " " +  e.getMessage());
			e.printStackTrace();
			return null;
		}
		catch (SocketTimeoutException e){
			Log.e("load validate img error", e.toString() + " " +  e.getMessage());
			//e.printStackTrace();s
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.e("load validate img error", e.toString() + " " +  e.getMessage());
			return null;
		}

		return inStream;
	}
	public static InputStream handlerDataWithHeader(String url, String cookie) {
		InputStream inStream = null;

		try {
			URL feedUrl = new URL(url);
			URLConnection conn = feedUrl.openConnection();
			conn.setConnectTimeout(10 * 1000);
			inStream = conn.getInputStream();
			Map<String, List<String>> map = conn.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				Log.d("header", "Key : " + entry.getKey() + 
                        " ,Value : " + entry.getValue());
			}
			//��ȡhttpͷ�е�Set-Cookie�ֶ�
			List<String> set_cookie = map.get("Set-Cookie");
			if (set_cookie == null) {
				Log.e("header", "Header Key 'set_cookie' is not found!");
			}
			else{
				//for (String values : set_cookie) {
                //    Log.e("header", values);
                //    LessonTableActivity.cookie = values.toString();
				//}
				//cookie = (String)set_cookie.get(0).split(";")[0]; //���ﲻ֪����ô���������ô��ݵ�Ч��
				LessonTableActivity.cookie = (String)set_cookie.get(0).split(";")[0];
				ClassTableActivity.cookie = (String)set_cookie.get(0).split(";")[0];
				LoginActivity.cookie = (String)set_cookie.get(0).split(";")[0];
				Log.d("header", "get LessonTableActivity cookie:" + LessonTableActivity.cookie);
				Log.d("header", "get ClassTableActivity cookie:" + ClassTableActivity.cookie);
			}
		} catch (Exception e) {
			Log.e("load validate img error", e.toString() + " " +  e.getMessage());
			e.printStackTrace();
		}

		return inStream;
	}

	/** ֱ�ӷ���Drawable��������ͼƬ */
	public static Drawable loadImageFromNetwork(String imageUrl) {
		Drawable drawable = null;
		try {
			// ����������ͨ���ļ������жϣ��Ƿ񱾵��д�ͼƬ
			drawable = Drawable.createFromStream(
					new URL(imageUrl).openStream(), "image.jpg");
		} catch (IOException e) {
			Log.d("test", e.getMessage());
		}
		if (drawable == null) {
			Log.d("test", "null drawable");
		} else {
			Log.d("test", "not null drawable");
		}

		return drawable;
	}
}