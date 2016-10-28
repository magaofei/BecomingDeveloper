package cn.edu.tit.ui.stub;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;
import cn.edu.tit.R;

/** �̳� TabHost ��������������г��Ļ�������Ч���� */
public class AnimationTabHost extends TabHost {
	private Animation slideLeftIn;
	private Animation slideLeftOut;
	private Animation slideRightIn;
	private Animation slideRightOut;

	/** ��¼�Ƿ�򿪶���Ч�� */
	private boolean isOpenAnimation;
	/** ��¼��ǰ��ǩҳ������ */
	private int mTabCount;

	public AnimationTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);

		slideLeftIn = AnimationUtils.loadAnimation(context,
				R.anim.slide_in_left);
		slideLeftOut = AnimationUtils.loadAnimation(context,
				R.anim.slide_out_left);
		slideRightIn = AnimationUtils.loadAnimation(context,
				R.anim.slide_in_right);
		slideRightOut = AnimationUtils.loadAnimation(context,
				R.anim.slide_out_right);

		isOpenAnimation = false;
	}

	/**
	 * �����Ƿ�򿪶���Ч��
	 * 
	 * @param isOpenAnimation
	 *            true����
	 */
	public void setOpenAnimation(boolean isOpenAnimation) {
		this.isOpenAnimation = isOpenAnimation;
	}

	/**
	 * ���ñ�ǩ����������<br>
	 * ����˳��Ϊ���������>�������>�ҽ�����>�����
	 * 
	 * @param animationResIDs
	 *            ��������Դ�ļ�ID
	 * @return true���ĸ������ļ�;<br>
	 *         false�����ĸ������ļ����޷�ƥ�䣬����Ĭ�϶�����
	 */
	public boolean setTabAnimation(int[] animationResIDs) {
		if (3 == animationResIDs.length) {
			slideLeftIn = AnimationUtils.loadAnimation(getContext(),
					animationResIDs[0]);
			slideLeftOut = AnimationUtils.loadAnimation(getContext(),
					animationResIDs[1]);
			slideRightIn = AnimationUtils.loadAnimation(getContext(),
					animationResIDs[2]);
			slideRightOut = AnimationUtils.loadAnimation(getContext(),
					animationResIDs[3]);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return ���ص�ǰ��ǩҳ������
	 */
	public int getTabCount() {
		return mTabCount;
	}

	@Override
	public void addTab(TabSpec tabSpec) {
		mTabCount++;
		super.addTab(tabSpec);
	}

	@Override
	public void setCurrentTab(int index) {
		int mCurrentTabID = getCurrentTab();

		if (null != getCurrentView()) {
			// ��һ������ Tab ʱ����ֵΪ null��

			if (isOpenAnimation) {
				if (mCurrentTabID == (mTabCount - 1) && index == 0) {
					getCurrentView().startAnimation(slideLeftOut);
				} else if (mCurrentTabID == 0 && index == (mTabCount - 1)) {
					getCurrentView().startAnimation(slideRightOut);
				} else if (index > mCurrentTabID) {
					getCurrentView().startAnimation(slideLeftOut);
				} else if (index < mCurrentTabID) {
					getCurrentView().startAnimation(slideRightOut);
				}
			}
		}

		super.setCurrentTab(index);

		if (isOpenAnimation) {
			if (mCurrentTabID == (mTabCount - 1) && index == 0) {
				getCurrentView().startAnimation(slideLeftIn);
			} else if (mCurrentTabID == 0 && index == (mTabCount - 1)) {
				getCurrentView().startAnimation(slideRightIn);
			} else if (index > mCurrentTabID) {
				getCurrentView().startAnimation(slideLeftIn);
			} else if (index < mCurrentTabID) {
				getCurrentView().startAnimation(slideRightIn);
			}
		}
	}
}
