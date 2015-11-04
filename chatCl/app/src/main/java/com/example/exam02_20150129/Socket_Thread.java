package com.example.exam02_20150129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Socket_Thread extends Thread{
	Socket soc;
	PrintWriter pw;
	BufferedReader in;
	
	Handler mHandler;
	
	boolean mStart = true;
	Socket_Thread(Handler _h){
		mHandler = _h;
	}
	
	
	public void run(){
		try{
			Log.i("jw","소켓연결");
			soc = new Socket("192.168.12.4", 9895);
			
			pw = new PrintWriter(soc.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			
			String _msg;
			while(mStart){
				_msg = in.readLine();
				
				Message msg = new Message();
				msg.obj = _msg;
				mHandler.sendMessage(msg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String _str){
		Log.i("jw","pw : "+pw+" / _str : "+_str);
		pw.println(_str);
	}
	
	public void closeSocket(){
		mStart = false;
		try{
		soc.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
