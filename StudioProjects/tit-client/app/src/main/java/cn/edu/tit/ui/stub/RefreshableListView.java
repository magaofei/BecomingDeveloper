package cn.edu.tit.ui.stub;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.edu.tit.R;

public class RefreshableListView extends ListView implements OnScrollListener {

	private final static int RELEASE_To_REFRESH = 0;// ����ˢ��
	private final static int PULL_To_REFRESH = 1;// ��������
	private final static int REFRESHING = 2;// ˢ��
	private final static int DONE = 3;// ���
	private final static int LOADING = 4;// ������

	private final static int ENDINT_DONE = 0;// ���/�ȴ�ˢ��
	private final static int ENDINT_LOADING = 1;// ������

	// ʵ�ʵ�padding�ľ����������ƫ�ƾ���ı���
	private final static int RATIO = 3;

	// ���ڱ�֤startY��ֵ��һ��������touch�¼���ֻ����¼һ��
	private boolean isRecored;

	private int startY;
	private int firstItemIndex;
	private boolean canloadmore = true;// ���Լ��ظ��ࣿ
	private boolean canrefresh = true;// ����ˢ�£�
	private boolean isBack;
	private Context mContext;
	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;
	private boolean isRefreshable;
	private LayoutInflater inflater;
	private LinearLayout headView;
	private ImageView head_arrowImageView;
	private ProgressBar head_progressBar;
	private TextView head_lastUpdatedTextView;
	private int headContentHeight;
	private int headContentWidth;

	private int headstate;
	private int endingstate;

	private LinearLayout endingView;
	private TextView head_tipsTextView;
	private ProgressBar ending_progressBar;
	private int count;
	private boolean enougcount;
	private int lastItemIndex;

	private OnRefreshListener refreshListener;
	private OnLoadMoreListener loadMoreListener;
	private TextView ending_tipsTextView;

	public RefreshableListView(Context context) {
		super(context);
		mContext = context;
		initView();
		// TODO Auto-generated constructor stub
	}

	public RefreshableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		// TODO Auto-generated constructor stub
	}

	public RefreshableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
		// TODO Auto-generated constructor stub
	}

	// initial view attrs
	private void initView() {
		addHeadView();
		addEndingView();
		// listener
		setOnScrollListener(this);
		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(250);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(200);
		reverseAnimation.setFillAfter(true);

		isRefreshable = false;
	}

	private void addHeadView() {
		inflater = LayoutInflater.from(mContext);
		headView = (LinearLayout) inflater.inflate(
				R.layout.view_refreshable_listview_list_head, null);
		head_arrowImageView = (ImageView) headView
				.findViewById(R.id.head_arrowImageView);
		head_arrowImageView.setMinimumWidth(70);
		head_arrowImageView.setMinimumHeight(50);
		head_progressBar = (ProgressBar) headView
				.findViewById(R.id.head_progressBar);
		head_tipsTextView = (TextView) headView
				.findViewById(R.id.head_tipsTextView);
		head_lastUpdatedTextView = (TextView) headView
				.findViewById(R.id.head_lastUpdatedTextView);
		// measure the size of view
		measureView(headView);
		headContentHeight = headView.getMeasuredHeight();
		headContentWidth = headView.getMeasuredWidth();
//		headView.setPadding(0, -1 * headContentHeight, 0, 0);
//		headView.invalidate();

		// invoke the override method
		addHeaderView(headView, null, false);

		// flag to estimate the headview`s state
		headstate = DONE;
	}

	private void addEndingView() {
		inflater = LayoutInflater.from(mContext);
		endingView = (LinearLayout) inflater.inflate(
				R.layout.view_refreshable_listview_list_ending, null);
		ending_progressBar = (ProgressBar) endingView
				.findViewById(R.id.ending_progressBar);
		ending_tipsTextView = (TextView) endingView
				.findViewById(R.id.ending_tipsTextView);
		endingView.invalidate();
		// invoke the override method
		addFooterView(endingView, null, false);
		// flag to estimate the endingview`s state
		endingstate = ENDINT_DONE;
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		firstItemIndex = firstVisibleItem;
		lastItemIndex = firstVisibleItem + visibleItemCount - 2;
		count = totalItemCount - 2;
		if (totalItemCount > visibleItemCount) {
			enougcount = true;
		} else {
			enougcount = false;
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (lastItemIndex == count && scrollState == SCROLL_STATE_IDLE) {

			// SCROLL_STATE_IDLE=0������ֹͣ
			if (endingstate != ENDINT_LOADING) {
				endingstate = ENDINT_LOADING;
				onLoadmore();
			}
		}
		changeEndingViewByState();
	}


	public boolean onTouchEvent(MotionEvent event) {
		if (canrefresh) {
			if (isRefreshable) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (firstItemIndex == 0 && !isRecored) {
						isRecored = true;
						startY = (int) event.getY();
						// Log.v(TAG, "��downʱ���¼��ǰλ�á�");
					}
					break;

				case MotionEvent.ACTION_UP:

					if (headstate != REFRESHING && headstate != LOADING) {
						if (headstate == DONE) {
							// ʲô������
						}
						if (headstate == PULL_To_REFRESH) {
							headstate = DONE;
							changeHeaderViewByState();

							// Log.v(TAG, "������ˢ��״̬����done״̬");
						}
						if (headstate == RELEASE_To_REFRESH) {
							headstate = REFRESHING;
							changeHeaderViewByState();
							onRefresh();

							// Log.v(TAG, "���ɿ�ˢ��״̬����done״̬");
						}
					}

					isRecored = false;
					isBack = false;

					break;

				case MotionEvent.ACTION_MOVE:
					int tempY = (int) event.getY();

					if (!isRecored && firstItemIndex == 0) {
						// Log.v(TAG, "��moveʱ���¼��λ��");
						isRecored = true;
						startY = tempY;
					}

					if (headstate != REFRESHING && isRecored
							&& headstate != LOADING) {

						// ��֤������padding�Ĺ����У���ǰ��λ��һֱ����head������������б�����Ļ�Ļ����������Ƶ�ʱ���б��ͬʱ���й���

						// ��������ȥˢ����
						if (headstate == RELEASE_To_REFRESH) {

							setSelection(0);

							// �������ˣ��Ƶ�����Ļ�㹻�ڸ�head�ĳ̶ȣ����ǻ�û���Ƶ�ȫ���ڸǵĵز�
							if (((tempY - startY) / RATIO < headContentHeight)
									&& (tempY - startY) > 0) {
								headstate = PULL_To_REFRESH;
								changeHeaderViewByState();

								// Log.v(TAG, "���ɿ�ˢ��״̬ת�䵽����ˢ��״̬");
							}
							// һ�����Ƶ�����
							else if (tempY - startY <= 0) {
								headstate = DONE;
								changeHeaderViewByState();

								// Log.v(TAG, "���ɿ�ˢ��״̬ת�䵽done״̬");
							}
							// �������ˣ����߻�û�����Ƶ���Ļ�����ڸ�head�ĵز�
							else {
								// ���ý����ر�Ĳ�����ֻ�ø���paddingTop��ֵ������
							}
						}
						// ��û�е�����ʾ�ɿ�ˢ�µ�ʱ��,DONE������PULL_To_REFRESH״̬
						if (headstate == PULL_To_REFRESH) {

							setSelection(0);

							// ���������Խ���RELEASE_TO_REFRESH��״̬
							if ((tempY - startY) / RATIO >= headContentHeight) {
								headstate = RELEASE_To_REFRESH;
								isBack = true;
								changeHeaderViewByState();

								// Log.v(TAG, "��done��������ˢ��״̬ת�䵽�ɿ�ˢ��");
							}
							// ���Ƶ�����
							else if (tempY - startY <= 0) {
								headstate = DONE;
								changeHeaderViewByState();

								// Log.v(TAG, "��DOne��������ˢ��״̬ת�䵽done״̬");
							}
						}

						// done״̬��
						if (headstate == DONE) {
							if (tempY - startY > 0) {
								headstate = PULL_To_REFRESH;
								changeHeaderViewByState();
							}
						}

						// ����headView��size
						if (headstate == PULL_To_REFRESH) {
							headView.setPadding(0, -1 * headContentHeight
									+ (tempY - startY) / RATIO, 0, 0);

						}

						// ����headView��paddingTop
						if (headstate == RELEASE_To_REFRESH) {
							headView.setPadding(0, (tempY - startY) / RATIO
									- headContentHeight, 0, 0);
						}

					}

					break;
				}
			}
		}
		return super.onTouchEvent(event);
	}
	private void changeHeaderViewByState() {
		switch (headstate) {
		case RELEASE_To_REFRESH:
			head_arrowImageView.setVisibility(View.VISIBLE);
			head_progressBar.setVisibility(View.GONE);
			head_tipsTextView.setVisibility(View.VISIBLE);
			head_lastUpdatedTextView.setVisibility(View.VISIBLE);

			head_arrowImageView.clearAnimation();
			head_arrowImageView.startAnimation(animation);

			head_tipsTextView.setText("�ɿ�����ˢ��");

			// Log.v(TAG, "��ǰ״̬���ɿ�ˢ��");
			break;
		case PULL_To_REFRESH:
			head_progressBar.setVisibility(View.GONE);
			head_tipsTextView.setVisibility(View.VISIBLE);
			head_lastUpdatedTextView.setVisibility(View.VISIBLE);
			head_arrowImageView.clearAnimation();
			head_arrowImageView.setVisibility(View.VISIBLE);// �ɲ�����
			// ����RELEASE_To_REFRESH״̬ת������
			if (isBack) {
				isBack = false;
				head_arrowImageView.clearAnimation();
				head_arrowImageView.startAnimation(reverseAnimation);

				head_tipsTextView.setText("�����϶�����ˢ��");
			} else {
				head_tipsTextView.setText("�����϶�����ˢ��");
			}
			// Log.v(TAG, "��ǰ״̬������ˢ��");
			break;

		case REFRESHING:

			headView.setPadding(0, 0, 0, 0);

			head_progressBar.setVisibility(View.VISIBLE);
			head_arrowImageView.clearAnimation();
			head_arrowImageView.setVisibility(View.GONE);
			head_tipsTextView.setText("����ˢ��...");
			head_lastUpdatedTextView.setVisibility(View.VISIBLE);
			// Log.v(TAG, "��ǰ״̬,����ˢ��...");
			break;
		case DONE:
			headView.setPadding(0, -1 * headContentHeight, 0, 0);

			head_progressBar.setVisibility(View.GONE);
			head_arrowImageView.clearAnimation();
			head_arrowImageView
					.setImageResource(R.drawable.ic_pulltorefresh_arrow);
			head_tipsTextView.setText("�ɿ�����ˢ��");
			head_lastUpdatedTextView.setVisibility(View.VISIBLE);

			// Log.v(TAG, "��ǰ״̬��done");
			break;
		}
	}


	public void setOnRefreshListener(OnRefreshListener refreshListener) {
		this.refreshListener = refreshListener;
		isRefreshable = true;
	}

	public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
		this.loadMoreListener = loadMoreListener;
	}

	public interface OnRefreshListener {
		public void onRefresh();
	}

	public interface OnLoadMoreListener {
		public void onLoadMore();
	}
	public void setCanLoadMore(boolean args) {
		canloadmore = args;
		endingstate = ENDINT_DONE;
		changeEndingViewByState();
	}

	public void setCanreFresh(boolean arg) {
		canrefresh = arg;
	}



	/*
	 * ˢ�����
	 */
	public void onRefreshComplete() {
		headstate = DONE;
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��  HH:mm");
		String date = format.format(new Date());
		head_lastUpdatedTextView.setText("�ϴ�ˢ�� ��" + date);
		changeHeaderViewByState();
	}

	/**
	 * ���ظ������
	 */
	public void onLoadMoreComplete() {
		endingstate = ENDINT_DONE;
		changeEndingViewByState();
	}

	/**
	 * �ı���ظ���״̬
	 */
	private void changeEndingViewByState() {
		if (canloadmore) {
			// ������ظ���
			switch (endingstate) {
			case ENDINT_LOADING:// ˢ����
				ending_tipsTextView.setText("������...");
				ending_tipsTextView.setVisibility(View.VISIBLE);
				ending_progressBar.setVisibility(View.VISIBLE);
				break;
			case ENDINT_DONE:// ��ɡ��ȴ�ˢ��
			default:
				if (enougcount) {
					endingView.setVisibility(View.VISIBLE);
				} else {
					endingView.setVisibility(View.GONE);
				}
				ending_tipsTextView.setText("����");
				ending_tipsTextView.setVisibility(View.VISIBLE);
				ending_progressBar.setVisibility(View.GONE);
				break;
			}
		} else {
			endingView.setVisibility(View.GONE);
			ending_tipsTextView.setVisibility(GONE);
			ending_progressBar.setVisibility(GONE);
		}

	}
	private void onRefresh() {
		if (null != refreshListener) {
			refreshListener.onRefresh();
		}
	}

	private void onLoadmore() {
		if (canloadmore) {
			if (null != loadMoreListener) {
				loadMoreListener.onLoadMore();
			}
		}
	}
	// �˷���ֱ���հ��������ϵ�һ������ˢ�µ�demo���˴��ǡ����ơ�headView��width�Լ�height
	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}




	public void setAdapter(BaseAdapter adapter) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��  HH:mm");
		String date = format.format(new Date());
		head_lastUpdatedTextView.setText("�ϴ�ˢ�� ��" + date);

		// ��ʼ�����صģ���Ϊ���ݿ��ܲ����Գ�����Ļ
		endingView.setVisibility(View.GONE);

		super.setAdapter(adapter);
	}

}
