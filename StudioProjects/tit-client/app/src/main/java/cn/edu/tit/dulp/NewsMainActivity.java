package cn.edu.tit.dulp;

import android.app.Activity;
import cn.edu.tit.R;
import cn.edu.tit.util.Toaster;

public class NewsMainActivity extends Activity {

	
	/**
	 * ���������η��ؼ����˳�
	 */
	private long firstTime;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - firstTime < 3000) {
			finish();
		} else {
			firstTime = System.currentTimeMillis();
			Toaster.makeText(this, R.string.press_again_exit,0).show();
		}
	}

}
