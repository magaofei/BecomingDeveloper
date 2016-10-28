package cn.edu.tit.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import cn.edu.tit.R;
import cn.edu.tit.bean.Subject;
import cn.edu.tit.util.PinYin4j;

public class MyActvAdapter extends BaseAdapter implements Filterable {

	private int mResource;
	private int mFieldId = 0;
	private LayoutInflater mInflater;
	private int maxMatch = 10;// �����ʾ���ٸ�����ѡ��
	private List<Set<String>> pinyinList;// ֧�ֶ�����,����:{{z,c},{j},{z},{q,x}}�ļ���
	private Context mContext;
	private List<Subject> sbjList = new ArrayList<Subject>();
	private ArrayList<Subject> mOriginalValues;
	private ArrayFilter mFilter;
	private final Object mLock = new Object();
	public static final int ALL=-1;//ȫ��

	public MyActvAdapter(Context mContext, int maxMatch) {
		mContext = mContext;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		this.pinyinList = getHanziSpellList(sbjList);
		this.maxMatch = maxMatch;

	}

	/**
	 * ��ú���ƴ������ĸ�б�
	 */
	private List<Set<String>> getHanziSpellList(List<Subject> sbjList) {
		List<Set<String>> listSet = new ArrayList<Set<String>>();
		PinYin4j pinyin = new PinYin4j();
		for (int i = 0; i < sbjList.size(); i++) {
			listSet.add(pinyin.getPinyin(sbjList.get(i).getSbjname()));
		}
		return listSet;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sbjList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sbjList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void addAll(List<Subject> data){
		this.sbjList.addAll(data);
		this.pinyinList = getHanziSpellList(sbjList);
		notifyDataSetChanged();
	}
	
	public void clear(){
		sbjList.clear();
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_view_item, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(sbjList.get(position).getSbjname());
		return convertView;
	}

	private class ViewHolder {
		private TextView tv;
	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new ArrayFilter();
		}
		return mFilter;
	}

	private class ArrayFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			{
				FilterResults results = new FilterResults();

				if (mOriginalValues == null) {
					synchronized (mLock) {
						mOriginalValues = new ArrayList<Subject>(sbjList);//
					}
				}

				if (prefix == null || prefix.length() == 0) {
					synchronized (mLock) {
//						ArrayList<T> list = new ArrayList<T>();//��
						ArrayList<Subject> list = new ArrayList<Subject>(mOriginalValues);//List<T>
						results.values = list;
						results.count = list.size();
					}
				} else {
					String prefixString = prefix.toString().toLowerCase();

					final ArrayList<Subject> hanzi = mOriginalValues;//����String
					final int count = hanzi.size();

					final Set<Subject> newValues = new HashSet<Subject>(count);//֧�ֶ�����,���ظ�

					for (int i = 0; i < count; i++) {
						final Subject value = hanzi.get(i);//����String
						final String valueText = value.toString().toLowerCase();//����String
						final Set<String> pinyinSet=pinyinList.get(i);//֧�ֶ�����,����:{z,c}
						Iterator iterator= pinyinSet.iterator();//֧�ֶ�����
			        	while (iterator.hasNext()) {//֧�ֶ�����
			        		final String pinyin = iterator.next().toString().toLowerCase();//ȡ�����������һ����ĸ
			        		
			        		if (pinyin.indexOf(prefixString)!=-1) {//����ƥ��
			        			newValues.add(value);
			        		} 
			        		else if (valueText.indexOf(prefixString)!=-1) {//����Ǻ�����ֱ�����
			        			newValues.add(value);
			        		}
			        	}
			        	if(maxMatch>0){//����������
				        	if(newValues.size()>maxMatch-1){//��Ҫ̫��
				        		break;
				        	}
			        	}
						
					}
					List<Subject> list=Set2List(newValues);//ת��List
					results.values = list;
					results.count = list.size();
				}
				return results;
			}
			
		}
		
/*
		// TODO Auto-generated method stub
		FilterResults results = new FilterResults();
		if (mOriginalValues == null) {
			synchronized (mLock) {
				mOriginalValues = new ArrayList<Subject>();
			}
		}
		if (constraint == null || constraint.length() == 0) {
			synchronized (mLock) {
				List<Subject> list = new ArrayList<Subject>();
				results.values = list;
				results.count = list.size();
			}
		}else {
			String prefixString = constraint.toString().toLowerCase();

			final List<Subject> hanzi = mOriginalValues;// ����String
			final int count = hanzi.size();

			final Set<Subject> newValues = new HashSet<Subject>(count);// ֧�ֶ�����,���ظ�

			for (int i = 0; i < count; i++) {
				final Subject value = hanzi.get(i);// ����String
				final String valueText = value.toString().toLowerCase();// ����String
				final Set<String> pinyinSet = pinyinList.get(i);// ֧�ֶ�����,����:{z,c}
				Iterator iterator = pinyinSet.iterator();// ֧�ֶ�����
				while (iterator.hasNext()) {// ֧�ֶ�����
					final String pinyin = iterator.next().toString()
							.toLowerCase();// ȡ�����������һ����ĸ

					if (pinyin.indexOf(prefixString) != -1) {// ����ƥ��
						newValues.add(value);
					} else if (valueText.indexOf(prefixString) != -1) {// ����Ǻ�����ֱ�����
						newValues.add(value);
					}
				}
				if (maxMatch > 0) {// ����������
					if (newValues.size() > maxMatch - 1) {// ��Ҫ̫��
						break;
					}
				}

			}
			List<Subject> list = Set2List(newValues);// ת��List
			results.values = list;
			results.count = list.size();
		}
		return results;
	*/

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			sbjList = (List<Subject>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}

	public <T extends Object> List<T> Set2List(Set<T> oSet) {
		List<T> tList = new ArrayList<T>(oSet);
		// TODO ��Ҫ���õ���ʱ������д���죬������Ҫ����List�Ķ�Ӧ���ࡣ
		return tList;
	}

}
