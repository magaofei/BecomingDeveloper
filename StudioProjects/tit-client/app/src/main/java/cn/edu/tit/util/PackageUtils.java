package cn.edu.tit.util;

import cn.edu.tit.bean.AppInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class PackageUtils {
	
	public static final String TAG = "PackageUtils";
	
	
////  ��ȡ��ǰ����·��
//    getApplicationContext().getFilesDir().getAbsolutePath();
//
////  ��ȡ�ó���İ�װ��·��
//    String path=getApplicationContext().getPackageResourcePath();
//
////  ��ȡ����Ĭ�����ݿ�·��
//    getApplicationContext().getDatabasePath(s).getAbsolutePath();
//    

	/** 
	* ��ȡapk������Ϣ���汾�ţ����ƣ�ͼ��� 
	* @param absPath apk���ľ���·�� 
	* @param context  
	*/  
	public static AppInfo apkInfo(String absPath,Context context) {  
		AppInfo info = new AppInfo();

	    PackageManager pm = context.getPackageManager();  
	    PackageInfo pkgInfo = pm.getPackageArchiveInfo(absPath,PackageManager.GET_ACTIVITIES);  
	    if (pkgInfo != null) {  
	        ApplicationInfo appInfo = pkgInfo.applicationInfo;  
	        /* ����������䣬��Ȼ����icon��ȡ��default icon������Ӧ�ð���icon */  
	        appInfo.sourceDir = absPath;  
	        appInfo.publicSourceDir = absPath;  
	        String appName = pm.getApplicationLabel(appInfo).toString();// �õ�Ӧ����  
	        String packageName = appInfo.packageName; // �õ�����  
	        String versionName = pkgInfo.versionName; // �õ��汾��Ϣ  
	        int versionCode = pkgInfo.versionCode ;
	        /* icon1��icon2��ʵ��һ���� */  
	        Drawable icon1 = pm.getApplicationIcon(appInfo);// �õ�ͼ����Ϣ  
	        Drawable icon2 = appInfo.loadIcon(pm);  
	        String pkgInfoStr = String.format("PackageName:%s, Vesion: %s, AppName: %s", packageName, versionName, appName);  
	        Log.i(TAG, String.format("PkgInfo: %s", pkgInfoStr));
	        info.appIcon = icon1 ;
	        info.appName = appName ;
	        info.packageName=packageName;
	        info.versionCode =versionCode  ;
	        info.versionName = versionName ;
	    }  
	    return info ;
	}
	

}
