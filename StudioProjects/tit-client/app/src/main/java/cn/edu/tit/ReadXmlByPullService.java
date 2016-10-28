package cn.edu.tit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
import cn.edu.tit.bean.RomUtils;

/**
 * ʹ��pull����xml 2010-04-26
 * 
 * @author leequer
 * 
 */
public class ReadXmlByPullService {

	public static List<RomUtils> ReadXmlByPull(InputStream inputStream)
			throws Exception {
		List<RomUtils> rosteam = null;
		/**
		 * android�������ṩ��xml �����õ�xmlpull������
		 */
		XmlPullParser xmlpull = Xml.newPullParser();
		/**
		 * ������������ �趨���뷽ʽ
		 * 
		 */
		xmlpull.setInput(inputStream, "utf-8");
		/**
		 * pull����xml�� �������� ��ȡ��xml��������������0 START_DOCUMENT; ��ȡ��xml�Ľ�����������1
		 * END_DOCUMENT ; ��ȡ��xml�Ŀ�ʼ��ǩ��������2 START_TAG ��ȡ��xml�Ľ�����ǩ��������3 END_TAG
		 * ��ȡ��xml���ı���������4 TEXT
		 */
		int eventCode = xmlpull.getEventType();
		/**
		 * ֻҪ����¼����صĲ���1 ���Ǿ�һֱ��ȡxml�ļ�
		 */
		RomUtils romUtils = null;
		while (eventCode != XmlPullParser.END_DOCUMENT) {

			switch (eventCode) {
			case XmlPullParser.START_DOCUMENT: {// ��ʼ�ĵ� new����
				rosteam = new ArrayList<RomUtils>();
				break;
			}
			case XmlPullParser.START_TAG: {
				if ("rom".equals(xmlpull.getName())) {
					romUtils = new RomUtils();
					// person.setId(xmlpull.getAttributeValue(0));
				} else if (romUtils != null) {
					if (("romname".equals(xmlpull.getName()))) {
						/**
						 * �����ǰԪ�ص���һ��Ԫ�����ı��ڵ� �Ϳ���ֱ����nextText()����������õ��ı��ڵ������
						 */
						romUtils.setRomName(xmlpull.nextText());
					} else if ("rominfo".equals(xmlpull.getName())) {
						/**
						 * �����ǰԪ�ص���һ��Ԫ�����ı��ڵ� �Ϳ���ֱ����nextText()����������õ��ı��ڵ������
						 */
						romUtils.setRomInfo(xmlpull.nextText());
					} else if ("romername".equals(xmlpull.getName())) {
						/**
						 * �����ǰԪ�ص���һ��Ԫ�����ı��ڵ� �Ϳ���ֱ����nextText()����������õ��ı��ڵ������
						 */
						romUtils.setRomerName(xmlpull.nextText());
					}
				}
				break;
			}

			case XmlPullParser.END_TAG: {
				if ("rom".equals(xmlpull.getName()) && romUtils != null) {
					rosteam.add(romUtils);
					romUtils = null;
				}
				break;
			}
			}

			eventCode = xmlpull.next();// û�н���xml�ļ����Ƶ��¸����н���

		}

		return rosteam;
	}

}
