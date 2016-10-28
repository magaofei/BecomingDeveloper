package cn.edu.tit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import cn.edu.tit.bean.City;
import cn.edu.tit.bean.WeatherInfo;
import cn.edu.tit.config.Config;
import cn.edu.tit.db.CityDB;
import cn.edu.tit.util.NetUtil;
import cn.edu.tit.util.SharePreferenceUtil;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class Application extends android.app.Application {
	public static final int CITY_LIST_SCUESS = 100;
	private static Application mApplication;
	private HashMap<String, Integer> mWeatherIcon;// ����ͼ��
	private HashMap<String, Integer> mWidgetWeatherIcon;// �������ͼ��
	// ����ĸ��
	private List<String> mSections;
	// ��������ĸ�������
	// ����ĸλ�ü�
	private List<Integer> mPositions;
	// ����ĸ��Ӧ��λ��
	private Map<String, Integer> mIndexer;
	private CityDB mCityDB;

	private SharePreferenceUtil mSpUtil;
	public static int mNetWorkState;
	private NotificationManager mNotificationManager;

	public static synchronized Application getInstance() {
		return mApplication;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
		initImageLoader(getApplicationContext());
		mCityDB = openCityDB();// ����������ȸ�����,�����ҷ��ڵ��߳��д���,���Ż�
		initData();
	}

	public void initData() {
		mNetWorkState = NetUtil.getNetworkState(this);
		initCityList();
		mSpUtil = new SharePreferenceUtil(this,
				SharePreferenceUtil.CITY_SHAREPRE_FILE);
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
	}

	public synchronized SharePreferenceUtil getSharePreferenceUtil() {
		if (mSpUtil == null)
			mSpUtil = new SharePreferenceUtil(this,
					SharePreferenceUtil.CITY_SHAREPRE_FILE);
		return mSpUtil;
	}

	/** ��ʼ��ImageLoader */
	public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"titnews/Cache");// ��ȡ�������Ŀ¼��ַ
		Log.d("cacheDir", cacheDir.getPath());
		// ��������ImageLoader(���е�ѡ��ǿ�ѡ��,ֻʹ����Щ������붨��)����������趨��APPLACATION���棬����Ϊȫ�ֵ����ò���
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				// .memoryCacheExtraOptions(480, 800) // max width, max
				// height���������ÿ�������ļ�����󳤿�
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75,
				// null) // Can slow ImageLoader, use it carefully (Better don't
				// use it)���û������ϸ��Ϣ����ò�Ҫ�������
				.threadPoolSize(3)
				// �̳߳��ڼ��ص�����
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
				// 1024)) // You can pass your own memory cache
				// implementation�����ͨ���Լ����ڴ滺��ʵ��
				// .memoryCacheSize(2 * 1024 * 1024)
				// /.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())// �������ʱ���URI������MD5
																		// ����
				// .discCacheFileNameGenerator(new
				// HashCodeFileNameGenerator())//�������ʱ���URI������HASHCODE����
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .discCacheFileCount(100) //�����File����
				.discCache(new UnlimitedDiscCache(cacheDir))// �Զ��建��·��
				// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				// .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
				// 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)��ʱʱ��
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// ȫ�ֳ�ʼ��������
	}

	private City city;

	public City getCity() {
		if (city != null) {
			return city;
		}
		if (!mSpUtil.getCity().endsWith("")) {
			return mCityDB.getCity(mSpUtil.getCity());
		}

		return new City("ɽ��", "̫ԭ", "101100101", "taiyuan", "ty");
	}

	public void setCity(City city) {
		this.city = city;
		mSpUtil.setCity(city.getName());
	}

	private WeatherInfo allWeather;

	public WeatherInfo getAllWeather() {
		return allWeather;
	}

	public void setAllWeather(WeatherInfo allWeather) {
		this.allWeather = allWeather;
	}

	private List<City> mCityList;
	// ��������ĸ�������
	private Map<String, List<City>> mMap;

	private CityDB openCityDB() {
		String path = "/data"
				+ Environment.getDataDirectory().getAbsolutePath()
				+ File.separator +Config.DEFAULT_DB_PARENT_PATH + File.separator
				+ CityDB.CITY_DB_NAME;
		File db = new File(path);
		if (!db.exists() || getSharePreferenceUtil().getVersion() < 0) {
			try {
				InputStream is = getAssets().open(CityDB.CITY_DB_NAME);
				FileOutputStream fos = new FileOutputStream(db);
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					fos.flush();
				}
				fos.close();
				is.close();
				getSharePreferenceUtil().setVersion(1);// ���ڹ������ݿ�汾��������ݿ����ش����ʱʹ��
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return new CityDB(this, path);
	}

	public List<City> getCityList() {
		return mCityList;
	}

	public List<String> getSections() {
		return mSections;
	}

	public Map<String, List<City>> getMap() {
		return mMap;
	}

	public List<Integer> getPositions() {
		return mPositions;
	}

	public Map<String, Integer> getIndexer() {
		return mIndexer;
	}

	// �������ں�̨����ʱ���ͷ��ⲿ����ռ�ڴ����Դ
	public void free() {
		mCityList = null;
		mSections = null;
		mMap = null;
		mPositions = null;
		mIndexer = null;
		mWeatherIcon = null;
		System.gc();
	}

	private boolean prepareCityList() {
		mCityList = new ArrayList<City>();
		mSections = new ArrayList<String>();
		mMap = new HashMap<String, List<City>>();
		mPositions = new ArrayList<Integer>();
		mIndexer = new HashMap<String, Integer>();
		mCityList = mCityDB.getAllCity();// ��ȡ���ݿ������г���
		for (City city : mCityList) {
			String firstName = city.getPy().substring(0, 1).toUpperCase();// ��һ����ƴ���ĵ�һ����ĸ
			if (firstName.matches(Config.FORMAT)) {
				if (mSections.contains(firstName)) {
					mMap.get(firstName).add(city);
				} else {
					mSections.add(firstName);
					List<City> list = new ArrayList<City>();
					list.add(city);
					mMap.put(firstName, list);
				}
			} else {
				if (mSections.contains("#")) {
					mMap.get("#").add(city);
				} else {
					mSections.add("#");
					List<City> list = new ArrayList<City>();
					list.add(city);
					mMap.put("#", list);
				}
			}
		}
		Collections.sort(mSections);// ������ĸ��������
		int position = 0;
		for (int i = 0; i < mSections.size(); i++) {
			mIndexer.put(mSections.get(i), position);// ����map�У�keyΪ����ĸ�ַ�����valueΪ����ĸ��listview��λ��
			mPositions.add(position);// ����ĸ��listview��λ�ã�����list��
			position += mMap.get(mSections.get(i)).size();// ������һ������ĸ��listview��λ��
		}
		return true;
	}

	private void initCityList() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				prepareCityList();
			}
		}).start();
	}

}
