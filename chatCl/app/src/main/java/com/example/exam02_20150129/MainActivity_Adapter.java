package com.example.exam02_20150129;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity_Adapter extends BaseAdapter {
	Context mContext;
	ArrayList<MainActivity_Value> mValue;
	LayoutInflater mInflater;

	
	MainActivity_Adapter(Context _c, ArrayList<MainActivity_Value> _list){
		mContext = _c;
		mValue = _list;		
		mInflater = LayoutInflater.from(_c);
	}
	
	@Override
	public int getCount() {
		//����Ʈ�䰡 ����(�)�̳�?
		return mValue.size();
	}

	@Override
	public Object getItem(int position) {
		//�Ű������� �Ѿ�� ��ġ���� ����
		return mValue.get(position);
	}

	@Override
	public long getItemId(int position) {
		//�ǹ̾���.
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//���ٿ� �ش�Ǵ� �並 ����
		
		View view = null;
		ViewHolder _holder = null;
		
		Log.i("jw","position : "+position+" / convertView : "+convertView);
		
		if(convertView == null){
			//�����ؾ� �Ѵ�.
			view = mInflater.inflate(R.layout.mainactivity_adapter_item, null, true);
			_holder = new ViewHolder();
			_holder.tv01 = (TextView)view.findViewById(R.id.textView1);
			_holder.tv02 = (TextView)view.findViewById(R.id.textView2);
			_holder.tv03 = (TextView)view.findViewById(R.id.textView3);
			
			view.setTag(_holder);
		}else{
			//���� �Ѵ�.
			view = convertView;
			_holder = (ViewHolder)view.getTag();
		}
		
		_holder.tv01.setText(mValue.get(position).name);
		_holder.tv02.setText(mValue.get(position).id);
		_holder.tv03.setText(mValue.get(position).pwd);
		
		return view;
	}
	
	class ViewHolder{
		TextView tv01;
		TextView tv02;
		TextView tv03;
	}

}
