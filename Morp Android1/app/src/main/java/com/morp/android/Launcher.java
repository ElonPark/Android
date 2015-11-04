package com.morp.android;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Launcher extends Activity implements OnClickListener {

	public static String SERVER_IP = "192.168.36.91";
	public static int SERVER_PORT = 19999;

	Button sendButton;
	EditText inputField;
	TextView textView;

	String name;

	Socket socket;
	DataInputStream input;
	DataOutputStream output;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sendButton = (Button) findViewById(R.id.sendButton);
		inputField = (EditText) findViewById(R.id.inputField);
		textView = (TextView) findViewById(R.id.textView);

		sendButton.setOnClickListener(this);
		intro();
	}

	@Override
	public void onDestroy() {
super.onDestroy();
		try {
			socket.close();
			input.close();
			output.close();
		} catch (IOException e) {
		}
	}


	public void intro() {
		print("접속 완료\n");
		print("대화명을 입력하시고 전송 버튼을 눌러 주세요.");
	}

	public void print(Object message) {
		textView.append(message + "\n");
	}

	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.sendButton:

				if (inputField.getText().toString() == null)
					return;

				if (name == null) {
					String text = inputField.getText().toString();
					inputField.setText("");
					name = text;
					connect();
				} else {
					String text = inputField.getText().toString();
					inputField.setText("");

					try {
						output.writeUTF("c`" + text);
						output.flush();
					} catch (IOException e) {
						print("메시지 전송을 실패하였습니다.");
					}

				}
				break;
		}
	}

	public void connect() {
		try {

			print(SERVER_IP + ":" + SERVER_PORT + "로 접속 중...");

			socket = new Socket(SERVER_IP, SERVER_PORT);

			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());

			while (socket != null) {
				if (socket.isConnected()) {
					output.writeUTF("r`1`1`" + name + "`");
					output.flush();
					break;
				}
			}

			MessageReciver messageReceiver = new MessageReciver();
			messageReceiver.start();

		} catch (Exception e) {
			print("서버에 접속할 수 없습니다.");
			this.finish();

		}

	}

	public String chatMessage;

	public class MessageReciver extends Thread {
		public void run() {
			try {
				String received;

				while ((received = input.readUTF()) != null) {

					String[] buffer = received.split("`");

					switch (buffer[0].charAt(0)) {
						case 'n':
							chatMessage = "**" + buffer[1] + "**";

							break;
						case 'c':
							chatMessage = buffer[1] + ": " + buffer[2];
							break;
						case 'x':
							chatMessage = "##" + buffer[1] + "##";
							break;
					}

					Message message = handler.obtainMessage(1, received);

					handler.sendMessage(message);

				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	Handler handler = new Handler() {

		public void handleMessage(Message message) {
			super.handleMessage(message);

			print(chatMessage);

		}
	};

}
