package cn.edu.tit.atys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.tit.R;
import cn.edu.tit.adapter.RefreshableListViewAdapter;
import cn.edu.tit.bean.HttpPostDao;
import cn.edu.tit.bean.PostParamsBean;
import cn.edu.tit.bean.RequestDataService;
import cn.edu.tit.swipeback.SwipeBackActivity;
import cn.edu.tit.ui.stub.RefreshableListView;
import cn.edu.tit.ui.stub.RefreshableListView.OnLoadMoreListener;
import cn.edu.tit.ui.stub.RefreshableListView.OnRefreshListener;
import cn.edu.tit.util.NetUtil;
import cn.edu.tit.util.Toaster;

public class MyNewsListActivity extends SwipeBackActivity implements OnRefreshListener,
		OnLoadMoreListener, OnItemClickListener {

	private static final int REQUEST_DATA_CODE = 0x123;
	private RefreshableListView listview; // �ɻ�����listView�ؼ�
	private LinearLayout view_load_fail; // ���س���
	private List<Map<String, Object>> lists; // ���ݼ�
	private TextView details_textview_title;
	private RefreshableListViewAdapter mAdapter; // ��ͼ������
	private Button bt_refresh;
	private ProgressDialog pdialog;
	private static String weburl;
	private static String name;
	private static int PageCount = 1;
	private static final String TAG = "NewsActivity";

	private ImageView details_imageview_gohome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ò���
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_list_optimus);
		// ѧԺҪ��
		weburl = getIntent().getExtras().getString("url");
		name = getIntent().getExtras().getString("name");
		initView();
		initData();
		lists = new ArrayList<Map<String, Object>>();
		listview.setAdapter(mAdapter);
		// ������Ҫ�ж�����״̬
		if (NetUtil.getNetworkState(getApplicationContext())<0) { // ���粻��ʹ��
			view_load_fail.setVisibility(View.VISIBLE);
			listview.setVisibility(View.GONE);
			// ����һ�����󴰿�
			return; //
		}

		new AsyncTask<String, Void, List<Map<String, Object>>>() {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected List<Map<String, Object>> doInBackground(String... params) {
				// TODO ������վ����
				if (!params[0].equals("")) {
					// ����HTTP POST�������������NameValuePair����
					SharedPreferences sp = getSharedPreferences("config",
							MODE_PRIVATE);
					HttpPostDao postDao = new HttpPostDao();
					postDao.setNowPage("1");
					postDao.setPage(sp.getString("page", "0"));
					String htmlpage = RequestDataService.RequestPostInAndroid(
							weburl, getApplicationContext(),
							PostParamsBean.getPostParams(postDao));

					// System.out.println("htmlpage:"+htmlpage);
					return RequestDataService.NewsTagInfos(htmlpage);
					// Log.e("success", datas.get(0).get("title")+"");
				}
				return null;
			}

			@Override
			protected void onPostExecute(List<Map<String, Object>> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				pdialog.dismiss();
				mAdapter.appendToList(result);
			}

		}.execute(weburl, null, null);

	}

	public void initView() {
		listview = (RefreshableListView) findViewById(R.id.list_view);
		listview.setOnRefreshListener(this);
		listview.setOnLoadMoreListener(this);
		listview.setOnItemClickListener(this);
		mAdapter = new RefreshableListViewAdapter(this);
		view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
		bt_refresh = (Button) this.findViewById(R.id.bn_refresh);
		bt_refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ������Ҫ�ж�����״̬
				if (NetUtil.getNetworkState(getApplicationContext())<0) { // ���粻��ʹ��
					view_load_fail.setVisibility(View.GONE);
					listview.setVisibility(View.VISIBLE);
					// TODO ���¼�������
					return; //
				}
			}
		});

		pdialog = new ProgressDialog(this);
		pdialog.setTitle(getString(R.string.pd_tips));
		pdialog.setMessage(getString(R.string.pd_message_downloading));
		pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pdialog.setCanceledOnTouchOutside(false);
//		pdialog.setCancelable(false);
		pdialog.show();

		details_imageview_gohome = (ImageView) this
				.findViewById(R.id.iv_goBack);
		details_imageview_gohome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyNewsListActivity.this.finish();

			}
		});
		// this.details_imageview_gohome.setVisibility(View.GONE);
		details_textview_title = (TextView) this
				.findViewById(R.id.tv_title);
		if (name != null) {
			details_textview_title.setText(name);
		}

	}

	public void initData() {

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		// ���ظ�������

		// ��Ҫ����ʱ���޸�������룡
		new AsyncTask<Void, Void, String>() {
			// �ӳ٣�doInBackground
			protected String doInBackground(Void... params) {
				try {
					Thread.sleep(1000);
					SharedPreferences sp = getSharedPreferences("config",
							MODE_PRIVATE);
					Editor editor = sp.edit();
					editor.putString("page", (++PageCount) * 20 + "");
					editor.commit();
					// System.out.println(PageCount);
					HttpPostDao postDao = new HttpPostDao();
					postDao.setActionType("NextPage");
					postDao.setPage(sp.getString("page", "0"));

					return RequestDataService.RequestPostInAndroid(weburl,
							getApplicationContext(),
							PostParamsBean.getPostParams(postDao));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String htmlpage) {
				if (mAdapter.getCount() > 40000) {
					listview.setCanLoadMore(false);
				} else {
					mAdapter.appendToList(RequestDataService
							.NewsTagInfos(htmlpage));
					// mAdapter.appendToList(lists);
					listview.onLoadMoreComplete();// ���ظ�����ɣ�
				}
			}

		}.execute(null, null, null);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		// ˢ������

		// ��Ҫ����ʱ���޸�������룡
		new AsyncTask<Void, Void, Void>() {
			// ˢ���ӳ٣�doInBackground

			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// mAdapter.refresh();
				// ��Ҫ�����޸Ĵ���

				listview.setCanLoadMore(true);// ���Լ��ظ���
				listview.onRefreshComplete();// ����ˢ�£�
			}
		}.execute(null, null, null);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		lists = mAdapter.getmList();
		if (lists == null) {
			Toaster.makeText(getApplicationContext(), "listΪ��", 0).show();

		}

		String url = (String) lists.get(position - 1).get("href");
		Toaster.makeText(getApplicationContext(), url, 0).show();
		Log.e(TAG, url + 123);
		Intent intent = new Intent(MyNewsListActivity.this,
				MyNewsDetailsActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);

	}

}
