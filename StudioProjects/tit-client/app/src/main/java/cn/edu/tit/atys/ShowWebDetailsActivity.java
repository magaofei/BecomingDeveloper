package cn.edu.tit.atys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.R.string;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.tit.Application;
import cn.edu.tit.R;
import cn.edu.tit.bean.GetContentTask;
import cn.edu.tit.swipeback.SwipeBackActivity;

public class ShowWebDetailsActivity extends SwipeBackActivity {
	private static final String TAG = "ShowWebDetailsActivity";
	Application mApp;
	private WebView mWebView;
	private TextView tv_title;
	private ImageView iv_goBack;
	private ProgressDialog pd;
	private String webUrl;
	private String webview_method;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_web_details);
		initView();
		initData();
		// �õ����͹������ַ���
		webUrl = getIntent().getExtras().getString("url");
		webview_method = getIntent().getExtras().getString("webview_method");
		tv_title.setText(webUrl);
		if (webUrl.equals("")) {
			TextView tv = new TextView(this);
			tv.setText(R.string.code_error);
			setContentView(tv);
			return;
		}
		
		Map<String, String> params = new HashMap<String, String>();
		Bundle bundle = getIntent().getBundleExtra("params");
		Set<String> keySet = bundle.keySet(); // �õ�bundle�����е�key
		Iterator<String> iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			params.put(key, bundle.getString(key));
		}

		Map<String, String> headers = new HashMap<String, String>();
		Bundle bundle2 = getIntent().getBundleExtra("headers");
		Set<String> keySet2 = bundle2.keySet(); // �õ�bundle�����е�key
		Iterator<String> iter2 = keySet2.iterator();
		while (iter2.hasNext()) {
			String key = iter2.next();
			headers.put(key, bundle2.getString(key));
		}
		// System.out.println(map == null ? "mapΪ��" : "map��Ϊ��");
		// for(int i =0 ; i <headers.size();i++){
		// System.out.println(headers.get(Config.KEY_KECHENGKEBIAO_KECHENG));
		// System.out.println(headers.get(Config.KEY_KECHENGKEBIAO_XUEQI));
		//
		// }

		String cookies = getIntent().getExtras().getString("cookies");
		GetContentTask task = new GetContentTask(webview_method, pd, mWebView, webUrl, cookies,
				params, headers);
		task.execute();
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	public void initView() {
		this.tv_title = (TextView) this.findViewById(R.id.tv_title);
		this.iv_goBack = (ImageView) this.findViewById(R.id.iv_goBack);
		this.iv_goBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowWebDetailsActivity.this.finish();

			}
		});

		mWebView = (WebView) findViewById(R.id.detail_webView);  
		// mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		//this.mWebView.setBackgroundColor(0);  //��ΪMyNewsDetailsActivity����һ�ע��
		// mWebView.setBackgroundColor(R.color.transparent); //ok ����������
		this.mWebView.setBackgroundResource(R.color.detail_bgColor);
		// ���js�����ӿ��࣬������� imagelistner
		mWebView.addJavascriptInterface(new JavascriptInterface(
				getApplicationContext()), "imagelistner");
		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.setWebViewClient(new MyWebViewClient());

		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);            // ���ÿ�������JS�ű�
		
		settings.setDomStorageEnabled(true);            //  ���ÿ���DOM����

		settings.setSupportZoom(true);                  // ��������webview�Ŵ�
		settings.setBuiltInZoomControls(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setDefaultTextEncodingName("utf-8");
		// settings.setUseWideViewPort(true);
		// settings.setLoadWithOverviewMode(true);
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);

	}

	// ע��js��������
	private void addImageClickListner() {
		// ���js�����Ĺ��ܾ��ǣ��������е�img���㣬�����onclick�������ڻ���ִ�е�ʱ����ñ��ؽӿڴ���url��ȥ
		mWebView.loadUrl("javascript:(function(){"
				+ "var objs = document.getElementsByTagName(\"img\");"
				+ "var imgurl=''; " + "for(var i=0;i<objs.length;i++)  " + "{"
				+ "imgurl+=objs[i].src+',';"
				+ "    objs[i].onclick=function()  " + "    {  "
				+ "        window.imagelistner.openImage(imgurl);  "
				+ "    }  " + "}" + "})()");
	}

	// jsͨ�Žӿ�
	public class JavascriptInterface {

		private Context context;

		public JavascriptInterface(Context context) {
			this.context = context;
		}

		public void openImage(String img) {

			//
			String[] imgs = img.split(",");
			ArrayList<String> imgsUrl = new ArrayList<String>();
			for (String s : imgs) {
				imgsUrl.add(s);
				Log.i("ͼƬ��URL>>>>>>>>>>>>>>>>>>>>>>>", s);
			}
			Intent intent = new Intent();
			intent.putStringArrayListExtra("infos", imgsUrl);
			intent.setClass(context, ImageShowActivity.class);   //����MyNewsDetailsActivityû��ע��
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
	}

	// ����
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageFinished(view, url);
			// html�������֮����Ӽ���ͼƬ�ĵ��js����
			addImageClickListner();
			// progressBar.setVisibility(View.GONE);
			mWebView.setVisibility(View.VISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			view.getSettings().setJavaScriptEnabled(true);
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// progressBar.setVisibility(View.GONE);
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
	}

	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// TODO Auto-generated method stub
			if (newProgress != 100) {
				// progressBar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}
	}
}
