package cn.edu.tit.util;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public class UserTool {
	private static boolean isOpenLog = true;
	private static final String TAG = "js456838";

	public static void printLog(String msg) {
		if (isOpenLog) {
			Log.e(TAG, msg);
		}
	}

	public static void BugCollectBin(String message) {		//�ռ�bug
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			UserTool.printLog("�Ҳ����ڴ濨");
			return;
		}
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "zngj" + "BugCollect.txt");
	}
}
