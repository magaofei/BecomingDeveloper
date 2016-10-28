package cn.edu.tit.ui.stub;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.tit.Application;
import cn.edu.tit.R;
import cn.edu.tit.bean.City;
import cn.edu.tit.bean.GetWeatherTask;
import cn.edu.tit.bean.WeatherInfo;
import cn.edu.tit.util.BaseTools;
import cn.edu.tit.util.Toaster;

/**
 * ���������Զ��岼��
 * 
 * @author Ra blog : http://blog.csdn.net/vipzjyno1/
 */
public class CurtainView extends RelativeLayout implements OnTouchListener {
	public static final int GET_WEATHER_SCUESS = 3;
	public static final int GET_WEATHER_FAIL = 4;
	private static String TAG = "CurtainView";
	private Context mContext;
	/** Scroller �϶��� */
	private Scroller mScroller;
	/** ��Ļ�߶� */
	private int mScreenHeigh = 0;
	/** ��Ļ��� */
	private int mScreenWidth = 0;
	/** ���ʱ��Y������ */
	private int downY = 0;
	/** �϶�ʱ��Y������ */
	private int moveY = 0;
	/** �϶�ʱ��Y�ķ������ */
	private int scrollY = 0;
	/** �ɿ�ʱ��Y������ */
	private int upY = 0;
	/** ���Ļ���ĸ߶� */
	private int curtainHeigh = 0;
	/** �Ƿ� �� */
	private boolean isOpen = false;
	/** �Ƿ��ڶ��� */
	private boolean isMove = false;
	/** ���ӵ�ͼƬ */
	private ImageView img_curtain_rope;
	/** ����ͼƬ */
	// private ImageView img_curtain_ad;

	private View layout_curtain_ad;
	/** ��������ʱ�� */
	private int upDuration = 1000;
	/** ���䶯��ʱ�� */
	private int downDuration = 500;
	private Application mApplication = Application.getInstance();

	public CurtainView(Context context) {
		super(context);
		init(context);
	}

	public CurtainView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public CurtainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/** ��ʼ�� */
	private void init(Context context) {
		this.mContext = context;
		// Interpolator ����Ϊ�з���Ч���� ��Bounce��������
		Interpolator interpolator = new BounceInterpolator();
		mScroller = new Scroller(context, interpolator);
		mScreenHeigh = BaseTools.getWindowHeigh(context);
		mScreenWidth = BaseTools.getWindowWidth(context);
		// �������ó�͸��
		this.setBackgroundColor(Color.argb(0, 0, 0, 0));
		final View view = LayoutInflater.from(mContext).inflate(
				R.layout.curtain, null);
		layout_curtain_ad = view.findViewById(R.id.layout_curtain_ad);
		// img_curtain_ad = (ImageView)view.findViewById(R.id.img_curtain_ad);
		img_curtain_rope = (ImageView) view.findViewById(R.id.img_curtain_rope);
		addView(view);
		layout_curtain_ad.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				curtainHeigh = layout_curtain_ad.getHeight();
				Log.d(TAG, "curtainHeigh= " + curtainHeigh);
				CurtainView.this.scrollTo(0, curtainHeigh);
				// ע��scrollBy��scrollTo������
			}
		});

		img_curtain_rope.setOnTouchListener(this);
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		tv_curTime = (TextView) this.findViewById(R.id.tv_curTime);
		now_tmp = (TextView) this.findViewById(R.id.now_tmp);
		tv_date = (TextView) this.findViewById(R.id.tv_date);
		tv_city = (TextView) this.findViewById(R.id.tv_city);
		// tv_city.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(getContext(),SelectCtiyActivity.class);
		// getContext().startActivity(intent);
		// }
		// });
		weather_summary = (TextView) this.findViewById(R.id.weather_summary);
		timeHandler.sendEmptyMessage(0);
		updateWeatherTask(mApplication.getCity());
	}

	public void updateWeatherTask(City city) {
		// City city = new City("����", "����", "101010100", "beijing", "bj");
		// System.out.println("ִ����updateWeatherTask");
		new GetWeatherTask(timeHandler, city).execute();

	}

	private Handler timeHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (getVisibility() == VISIBLE) {
				timeHandler.sendEmptyMessageDelayed(0, 1000);
				refreshTime();
				//
			}
			switch (msg.what) {
			case GET_WEATHER_SCUESS:
				updateWeatherUI();

				break;
			case GET_WEATHER_FAIL:
				Toaster.makeText(getContext(), "��ȡ����ʧ�ܣ�������", Toast.LENGTH_SHORT);
				break;
			}

		}

	};

	@SuppressWarnings("static-access")
	private void refreshTime() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		// tv_curTime.setText(new String().format("%d��%d",
		// c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
		tv_curTime.setText(getStrTime_hm(c.getTimeInMillis() + ""));

//		Calendar calendar = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        String today = null;
        if (day == 2) {
            today = "��һ";
        } else if (day == 3) {
            today = "�ܶ�";
        } else if (day == 4) {
            today = "����";
        } else if (day == 5) {
            today = "����";
        } else if (day == 6) {
            today = "����";
        } else if (day == 7) {
            today = "����";
        } else if (day == 1) {
            today = "����";
        }
    
//        tv_date.setText(today);
		tv_date.setText((new String().format("%s / %d��%d��",
				today, c.get(Calendar.MONTH) + 1,
				c.get(Calendar.DAY_OF_MONTH))));

	};

	/*
	 * ��ʱ���תΪ�ַ��� ����ʽ��HH:mm
	 */
	public static String getStrTime_hm(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// ���磺cc_time=1291778220
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time));
		return re_StrTime;
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		// TODO Auto-generated method stub
		super.onVisibilityChanged(changedView, visibility);
		if (visibility == View.GONE) { // ���ɼ�״̬
			timeHandler.removeMessages(0); // ���ڸ���
		} else {
			timeHandler.sendEmptyMessage(0);
		}

	}

	private TextView tv_curTime;
	private TextView now_tmp;
	private TextView tv_date;
	private TextView tv_city;
	private TextView weather_summary;

	/**
	 * �϶�����
	 * 
	 * @param startY
	 * @param dy
	 *            ��ֱ����, ������y����
	 * @param duration
	 *            ʱ��
	 */
	public void startMoveAnim(int startY, int dy, int duration) {
		isMove = true;
		mScroller.startScroll(0, startY, 0, dy, duration);
		invalidate();// ֪ͨUI�̵߳ĸ���
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	public void computeScroll() {
		// �ж��Ƿ��ڹ��������ڹ���Ϊtrue
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			// ���½���
			postInvalidate();
			isMove = true;
		} else {
			isMove = false;
		}
		super.computeScroll();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (!isMove) {
			int offViewY = 0;// ��Ļ�����͸ò��ֶ����ľ���
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downY = (int) event.getRawY();
				offViewY = downY - (int) event.getX();
				return true;
			case MotionEvent.ACTION_MOVE:
				moveY = (int) event.getRawY();
				scrollY = moveY - downY;
				if (scrollY < 0) {
					// ���ϻ���
					if (isOpen) {
						if (Math.abs(scrollY) <= layout_curtain_ad.getBottom()
								- offViewY) {
							scrollTo(0, -scrollY);
						}
					}
				} else {
					// ���»���
					if (!isOpen) {
						if (scrollY <= curtainHeigh) {
							scrollTo(0, curtainHeigh - scrollY);
						}
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				upY = (int) event.getRawY();
				if (Math.abs(upY - downY) < 10) {
					onRopeClick();
					break;
				}
				if (downY > upY) {
					// ���ϻ���
					if (isOpen) {
						if (Math.abs(scrollY) > curtainHeigh / 2) {
							// ���ϻ������������Ļ�ߵ�ʱ�� ����������ʧ����
							startMoveAnim(this.getScrollY(),
									(curtainHeigh - this.getScrollY()),
									upDuration);
							isOpen = false;
						} else {
							startMoveAnim(this.getScrollY(),
									-this.getScrollY(), upDuration);
							isOpen = true;
						}
					}
				} else {
					// ���»���
					if (scrollY > curtainHeigh / 2) {
						// ���ϻ������������Ļ�ߵ�ʱ�� ����������ʧ����
						startMoveAnim(this.getScrollY(), -this.getScrollY(),
								upDuration);
						isOpen = true;
					} else {
						startMoveAnim(this.getScrollY(),
								(curtainHeigh - this.getScrollY()), upDuration);
						isOpen = false;
					}
				}
				break;
			default:
				break;
			}
		}
		return false;
	}

	/**
	 * ����������أ���չ���ر� ��onToch��ʹ������еķ�����������¼��������˵��ʱ����ӦonTouch���νӲ�������Ӱ��
	 */
	public void onRopeClick() {
		if (isOpen) {
			CurtainView.this.startMoveAnim(0, curtainHeigh, upDuration);
		} else {
			CurtainView.this.startMoveAnim(curtainHeigh, -curtainHeigh,
					downDuration);
		}
		isOpen = !isOpen;
	}

	public synchronized void updateWeatherUI() {
		WeatherInfo weatherInfo = mApplication.getAllWeather();
		// System.out.println(weatherInfo.toString());
		if (weatherInfo != null) {
			String tmp = weatherInfo.getWendu();
			String city = weatherInfo.getCity();
			String summary = weatherInfo.getWeatherSummary();
			if (now_tmp.equals("��")) {
				now_tmp.setText("0��");
			} else {
				now_tmp.setText(tmp);
			}
			if (TextUtils.isEmpty(city)) {
				tv_city.setText("δ֪����");
			} else {
				tv_city.setText(city);
			}
			if (summary.equals("")) {
				weather_summary.setText("������Ϣ");
			} else {
				weather_summary.setText(summary);
			}

		} else {
			now_tmp.setText("0��");
			tv_city.setText("δ֪����");
			weather_summary.setText("������Ϣ");
		}

	}
}
