package com.example.exam02_20150129;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener, OnClickListener {
	ListView mList;
	ArrayList<MainActivity_Value> mValue;
	MainActivity_Adapter mAdapter;
	
	
	EditText et01;
	EditText et02;
	EditText et03;
	
	Button btn01;
	
	
	Socket_Thread mThread;
	DBManager mDB;
	
	Handler mHandler =new Handler(){
		public void handleMessage(Message msg){
			Log.i("jw","메시지 : "+(String)msg.obj);
			uiRefresh((String)msg.obj);
		}
	};
	
	private void uiRefresh(String _str){
		MainActivity_Value _temp = new MainActivity_Value();
		_temp.name = _str;
		_temp.id = _str;
		_temp.pwd = _str;
		
		mValue.add(0, _temp);
		mAdapter.notifyDataSetChanged();
		
		et01.setText("");
		et02.setText("");
		et03.setText("");
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mThread = new Socket_Thread(mHandler);
		mThread.start();
		
		mDB = new DBManager(this);
		
		mList = (ListView)findViewById(R.id.listView1);
		mValue = new ArrayList<MainActivity_Value>();
		
		btn01 = (Button)findViewById(R.id.button1);
		et01 = (EditText)findViewById(R.id.editText1);
		et02 = (EditText)findViewById(R.id.editText2);
		et03 = (EditText)findViewById(R.id.editText3);
		
		btn01.setOnClickListener( this );
		
		getMyData();
		
		mAdapter = new MainActivity_Adapter(this, mValue);
		mList.setAdapter(mAdapter);
		
		mList.setOnItemClickListener(this);
		mList.setOnItemLongClickListener( this );
		
		
		
	}
	
	private void getMyData(){
		mValue = mDB.getValueList();
	}
	


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(this, mValue.get(arg2).name, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
//		Toast.makeText(this, "롱클릭", Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("알림");
		alert.setMessage("선택한 놈을 삭제할래?");
		final int _temp = arg2;
		alert.setNegativeButton("삭제",  new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mValue.remove(_temp);		
				mAdapter.notifyDataSetChanged();
			}
		});
		alert.setPositiveButton("취소", null);
		alert.show();
		return true;
	}



	@Override
	public void onClick(View arg0) {		
		mThread.sendMessage(et01.getText().toString());
		
	}



	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	

	
}
