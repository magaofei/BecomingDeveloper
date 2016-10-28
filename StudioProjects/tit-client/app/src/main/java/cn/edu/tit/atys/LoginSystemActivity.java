package cn.edu.tit.atys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import cn.edu.tit.R;
import cn.edu.tit.adapter.ImageAdapter;
import cn.edu.tit.ui.stub.CircleFlowIndicator;
import cn.edu.tit.ui.stub.ViewFlow;
import cn.edu.tit.util.Toaster;

/**
 * ����ģ��:����
 * @author pjm
 *
 */
public class LoginSystemActivity extends Activity implements OnClickListener {
	private ViewFlow viewFlow;
	private FrameLayout fl_lesson_table; // �γ̿α�
	private FrameLayout fl_class_table; // �༶�α�
	private FrameLayout fl_college_intro ; //ѧԺ���
	private FrameLayout fl_coolege_logo ; //ѧԺ��ʶ
	private FrameLayout fl_loan_free ; 		//��ѧ����
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_system);
		init();
	}
	private void init() {
		this.fl_lesson_table = (FrameLayout) this
				.findViewById(R.id.fl_lesson_table);
		this.fl_lesson_table.setOnClickListener(this);
		this.fl_class_table = (FrameLayout) this
				.findViewById(R.id.fl_class_table);
		this.fl_class_table.setOnClickListener(this);
		this.fl_college_intro = (FrameLayout) this
				.findViewById(R.id.fl_college_intro);
		this.fl_college_intro.setOnClickListener(this);
		this.fl_coolege_logo = (FrameLayout) this
				.findViewById(R.id.fl_coolege_logo);
		this.fl_coolege_logo.setOnClickListener(this);
		this.fl_loan_free = (FrameLayout) this
				.findViewById(R.id.fl_loan_free);
		this.fl_loan_free.setOnClickListener(this);
		findViewById(R.id.fl_pastern_setting).setOnClickListener(this);

		findViewById(R.id.fl_recurit).setOnClickListener(this);
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new ImageAdapter(this));
		viewFlow.setmSideBuffer(4);

		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(2 * 1000);
		viewFlow.startAutoFlowTimer();
	}

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
			Toaster.makeText(this,
					getResources().getText(R.string.press_again_exit), 0)
					.show();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.fl_lesson_table: // �γ̿α�
			startActivity(new Intent(LoginSystemActivity.this,
					LessonTableActivity.class));
			break;
		case R.id.fl_class_table: // �γ̿α�
			startActivity(new Intent(LoginSystemActivity.this,
					ClassTableActivity.class));
			break;
			
		case R.id.fl_college_intro://ѧԺ���
			startActivity(new Intent(LoginSystemActivity.this,
					CollegeIntroActivity.class));
		break; 
		case R.id.fl_coolege_logo://ѧԺ��ʶ
			startActivity(new Intent(LoginSystemActivity.this,
					CollegeLogoActivity.class));
		break; 
		case R.id.fl_loan_free :	//��ѧ����
			startActivity(new Intent(LoginSystemActivity.this,
					LoanFreeActivity.class));
			break ;
		case R.id.fl_pastern_setting :	//Ժϵ����
			startActivity(new Intent(LoginSystemActivity.this,
					PasternSettingActivity.class));
			break ;
		case R.id.fl_recurit :	//У����Ƹ
			startActivity(new Intent(LoginSystemActivity.this,
					RecuritActivity.class));
			break ;
			
			
		default:
			break;
		}

	}

}
