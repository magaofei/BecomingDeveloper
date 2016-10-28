package cn.edu.tit.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import cn.edu.tit.R;
import cn.edu.tit.db.DBHelper;
import cn.edu.tit.db.ImageCacheColumn;

public class ImageUtil {
	private static final String TAG = "ImageUtil";
	private static int DayCount = 15;// ����
	private static final long CLEARTIME = DayCount * 24 * 60 * 60 * 1000;
	/**
	 * Ĭ��ͼƬ
	 */
	private final static int Default_Img = R.drawable.bg_load_default;

	private static Object lock = new Object();

	/**
	 * �ڴ�ͼƬ�����û���
	 */
	private static LinkedHashMap<String, SoftReference> imageCache = new LinkedHashMap<String, SoftReference>(
			20);

	/**
	 * ���
	 * 
	 * @param imageUrl
	 * @param iv_item_image
	 * @param context
	 * @param callback
	 * @param b
	 */
	public static void setThumbnailView(String imageUrl,
			ImageView iv_item_image, Context context, ImageCallback callback,
			boolean b) {
		DBHelper dbHelper = DBHelper.getInstance(context);
		String md5 = ImageUtil.md5(imageUrl);

		// ����Ŀ¼

		if (!CommonUtil.sdCardIsAvailable())/* true Ϊ���� */{
			String cachePath = context.getCacheDir().getAbsolutePath() + "/"
					+ md5; // data��Ļ���
			setThumbnailImage(iv_item_image, imageUrl, cachePath, dbHelper,
					callback, b);
			iv_item_image.setTag(cachePath);
		} else {
			String imagePath = getExternalCacheDir(context) + File.separator
					+ md5; // sd��
			setThumbnailImage(iv_item_image, imageUrl, imagePath, dbHelper,
					callback, b);
			iv_item_image.setTag(imagePath);
		}
	}

	/**
	 * ��ó�����sd���ϵ�cahceĿ¼
	 * 
	 * @param context
	 *            The context to use
	 * @return The external cache dir
	 */
	@SuppressLint("NewApi")
	public static String getExternalCacheDir(Context context) {
		// android 2.2 �Ժ��֧�ֵ�����
		if (hasExternalCacheDir()) {
			// if (Environment.isExternalStorageEmulated() ) {
			// Logger.e(TAG,
			// "emulated?:"+Environment.isExternalStorageEmulated());
			// Logger.e(TAG, context.getCacheDir()+"");
			//
			// context.getCacheDir();
			//
			// } else {

			return context.getCacheDir().getPath() + File.separator + "img";
			// }
		}

		// Before Froyo we need to construct the external cache dir ourselves
		// 2.2��ǰ������Ҫ�Լ�����
		final String cacheDir = "/Android/data/" + context.getPackageName()
				+ "/cache/img/";
		return Environment.getExternalStorageDirectory().getPath() + cacheDir;
	}

	public static boolean hasExternalCacheDir() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	/**
	 * ����ͼƬ����
	 * 
	 * @param view
	 * @param imageUrl
	 * @param cachePath
	 * @param callback
	 * @param b
	 */
	private static void setThumbnailImage(ImageView view, String imageUrl,
			String cachePath, DBHelper dbHelper, ImageCallback callback,
			boolean b) {
		Bitmap bitmap = null;
		bitmap = ImageUtil.loadThumbnailImage(cachePath, imageUrl, dbHelper,
				callback, b);
		if (bitmap == null) {// �Ȳ������ݿ⣬�ٲ��ұ���sd��,��û��.�ٴ���վ���أ�����վ��û��ͼƬ�����ʱ����null
			// ����Ĭ��ͼƬ
			view.setImageResource(Default_Img);
		} else {
			// ���ñ���SD������ͼƬ
			view.setImageBitmap(bitmap);
		}
	}

	private static Bitmap getImageFromDB(String imagePath, String imageUrl,
			DBHelper dbHelper) {
		Cursor cursor = queryFromDbByImgUrl(dbHelper, imageUrl);
		if (cursor.moveToFirst()) {
			long currTimestamp = (new Date()).getTime();
			long timestamp = cursor.getLong(cursor
					.getColumnIndex(ImageCacheColumn.TIMESTAMP));
			long spanTime = currTimestamp - timestamp;
			int Past_time = cursor.getInt(cursor
					.getColumnIndex(ImageCacheColumn.PAST_TIME));
			if (spanTime > Past_time * 24 * 60 * 60 * 1000) {
				// ����
				// ɾ�������ļ�
				deleteImageFromLocal(imagePath);
				return null;
			} else {
				// û����
				return getImageFromLocal(imagePath);
			}
		} else {
			return null;
		}
	}

	private static Cursor queryFromDbByImgUrl(DBHelper dbHelper, String imageUrl) {
		// return dbHelper.query(ImageCacheColumn.TABLE_NAME, null,
		// ImageCacheColumn.Url + "=?", new String[] { imageUrl });
		return dbHelper.rawQuery("select * from " + ImageCacheColumn.TABLE_NAME
				+ "  where " + ImageCacheColumn.Url + "='" + imageUrl + "'",
				null);
	}

	/**
	 * ����ͼƬ��SD��
	 * 
	 * @param imagePath
	 * @param buffer
	 * @throws IOException
	 */
	public static void saveImage(String imagePath, byte[] buffer)
			throws IOException {
		File f = new File(imagePath);
		if (f.exists()) {
			return;
		} else {
			File parentFile = f.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(imagePath);
			fos.write(buffer);
			fos.flush();
			fos.close();
		}
	}

	/**
	 * ����ͼƬ������
	 * 
	 * @param imagePath
	 * @param bm
	 */
	public static void saveImage(String imagePath, Bitmap bm) {

		if (bm == null || imagePath == null || "".equals(imagePath)) {
			return;
		}

		File f = new File(imagePath);
		if (f.exists()) {
			return;
		} else {
			try {
				File parentFile = f.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				f.createNewFile();
				FileOutputStream fos;
				fos = new FileOutputStream(f);
				bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.close();
			} catch (FileNotFoundException e) {
				f.delete();
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				f.delete();
			}
		}
	}

	private static void saveImageByDb(String imageUrl, DBHelper dbHelper) {
		String sql = null;
		if (queryFromDbByImgUrl(dbHelper, imageUrl).moveToFirst()) {
			sql = "update " + ImageCacheColumn.TABLE_NAME + " set "
					+ ImageCacheColumn.TIMESTAMP + "='"
					+ (new Date().getTime()) + "' where "
					+ ImageCacheColumn.Url + "='" + imageUrl + "'";
		} else {
			sql = "insert into " + ImageCacheColumn.TABLE_NAME + "("
					+ ImageCacheColumn.Url + "," + ImageCacheColumn.TIMESTAMP
					+ "," + ImageCacheColumn.PAST_TIME + ") values('"
					+ imageUrl + "'," + (new Date().getTime()) + "," + DayCount
					+ ")";
		}
		dbHelper.ExecSQL(sql);
	}

	/**
	 * ��SD������ͼƬ
	 * 
	 * @param imagePath
	 * @return
	 */
	public static Bitmap getImageFromLocal(String imagePath) {
		File file = new File(imagePath);
		if (file.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			file.setLastModified(System.currentTimeMillis());
			return bitmap;
		}
		return null;
	}

	/**
	 * �ӱ����ļ���ɾ���ļ�
	 * 
	 * @param imagePath
	 */
	private static void deleteImageFromLocal(String imagePath) {
		File file = new File(imagePath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * �ӱ��ػ��߷�����첽��������ͼͼƬ
	 * 
	 * @return
	 * @param imagePath
	 *            ���ػ���·��
	 * @param imgUrl
	 *            ƴ�Ӻ������·��
	 * @param callback
	 *            �õ����ݺ�Ĵ������ص�
	 * @throws IOException
	 */
	public static Bitmap loadThumbnailImage(final String imagePath,
			final String imgUrl, final DBHelper dbHelper,
			final ImageCallback callback, final boolean b) {
		// �������ӻ����У��򷵻�Bitmap����
		if (imageCache.containsKey(imgUrl)) {
			SoftReference reference = imageCache.get(imgUrl);
			Bitmap bitmap = (Bitmap) reference.get();
			if (bitmap != null) {
				return bitmap;
			}
		}
		// �������ӻ���û��
		Bitmap bitmap = null;
		// ��ѯ���ݿ� ����bitmap
		bitmap = getImageFromDB(imagePath, imgUrl, dbHelper);// �ӱ��ؼ���
		if (bitmap != null) {
			return bitmap;
		} else {
			// �����ϼ���
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.obj != null) {
						Bitmap bitmap = (Bitmap) msg.obj;
						callback.loadImage(bitmap, imagePath);
					}
				}
			};
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						URL url = new URL(imgUrl);
						URLConnection conn = url.openConnection();
						conn.setConnectTimeout(5000);
						conn.setReadTimeout(5000);
						conn.connect();
						InputStream in = conn.getInputStream();
						BitmapFactory.Options options = new Options();
						options.inSampleSize = 1;
						Bitmap bitmap = BitmapFactory.decodeStream(in,
								new Rect(0, 0, 0, 0), options);
						imageCache.put(imgUrl, new SoftReference(bitmap));

						Message msg = handler.obtainMessage();
						msg.obj = bitmap;
						handler.sendMessage(msg);
						if (bitmap != null) {
							// �����ļ���sd��
							saveImage(imagePath, bitmap);
							// ���浽���ݿ�
							saveImageByDb(imgUrl, dbHelper);
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
						Log.e(ImageUtil.class.getName(), "ͼƬurl������");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			ThreadPoolManager.getInstance().addTask(runnable);
		}
		return null;
	}

	/**
	 * MD5
	 * 
	 * @param paramString
	 * @return
	 */
	private static String md5(String paramString) {
		return MD5.encode(paramString);
	}

	// ///////////////////////////////////////////////////////////////////////
	// ��������

	public interface ImageCallback {
		public void loadImage(Bitmap bitmap, String imagePath);
	}

	/**
	 * ÿ�δ򿪺��д���ͼƬ��activityʱ,��һ�����߳�,��鲢������
	 * 
	 * @param context
	 */
	public static void checkCache(final Context context) {
		new Thread() {
			public void run() {
				int state = 0;// ��¼������ 0Ϊ��û���, 1Ϊֻ�����sd��, 2Ϊֻ�����rom Cache ,3
								// Ϊ�������
				String cacheS = "0M";
				String cacheD = "0M";
				File sdCache = new File(getExternalCacheDir(context)); // sd��"mnt/sdcard/android/data/cn.eoe.app/cache/";
				File cacheDir = context.getCacheDir(); // �ֻ�data/data/com.mengniu.app/cache
				try {
					if (sdCache != null && sdCache.exists()) {
						long sdFileSize = getFileSize(sdCache);
						if (sdFileSize > 1024 * 1024 * 50) {
							// SD��Ҫ����
							long clearFileSize = clear(sdCache);
							state += 1;
							cacheS = clearFileSize + "";
						}
					}
					if (cacheDir != null && cacheDir.exists()) {
						long cacheFileSize = getFileSize(cacheDir);
						if (cacheFileSize > 1024 * 1024 * 50) {
							// ROM��Ҫ����
							long clearFileSize = clear(cacheDir);
							state += 2;
							cacheD = clearFileSize + "";
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	/**
	 * ���·��
	 * 
	 * @param cacheDir
	 * @return
	 */
	public static long clear(File cacheDir) {
		long clearFileSize = 0;
		File[] files = cacheDir.listFiles();
		if (files == null)
			return 0;
		for (File f : files) {
			if (f.isFile()) {
				if (System.currentTimeMillis() - f.lastModified() > CLEARTIME) {
					long fileSize = f.length();
					if (f.delete()) {
						clearFileSize += fileSize;
					}
				}
			} else {
				clear(f);
			}
		}
		return clearFileSize;
	}

	/**
	 * ȡ���ļ���С
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * ת���ļ���С
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

}
