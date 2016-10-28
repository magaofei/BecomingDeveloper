package cn.edu.tit.util;

import java.io.ByteArrayInputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import cn.edu.tit.bean.WeatherInfo;

public class XmlPullParseUtil {
	public static WeatherInfo parseWeatherInfo(String result) {
		WeatherInfo allWeather = null;
		try {
			// ��ȡXmlPullParser��ʵ��
			XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance()
					.newPullParser();
			// ���������� xml�ļ�
			xmlPullParser.setInput(new ByteArrayInputStream(result.getBytes()),
					"UTF-8");
			// ��ʼ����
			int eventType = xmlPullParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String nodeName = xmlPullParser.getName();
				switch (eventType) {

				case XmlPullParser.START_DOCUMENT: // �ĵ���ʼ
					allWeather = new WeatherInfo();
					break;

				case XmlPullParser.START_TAG:// ��ʼ�ڵ�
					if(nodeName.equals("resp")){
//						System.out.println("resp�ڵ�");
					}
					if (nodeName.equals("city")) {
						allWeather.setCity(xmlPullParser.nextText());
					} else if (nodeName.equals("updatetime")) {
						allWeather.setUpdatetime(xmlPullParser.nextText());
					} else if (nodeName.equals("wendu")) {
						allWeather.setWendu(xmlPullParser.nextText());
					} else if (nodeName.equals("fengli")) {
						allWeather.setFengli(xmlPullParser.nextText());
					} else if (nodeName.equals("fengxiang")) {
						allWeather.setFengxiang(xmlPullParser.nextText());
					} 
					break;

				case XmlPullParser.END_TAG:// �����ڵ�
					break;

	
				}
				eventType = xmlPullParser.next();
			}
		} catch (Exception e) {
			//��������᷵�ؿ�
			e.printStackTrace();
		}
		return allWeather;
	}
}
