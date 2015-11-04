package kr.ac.induk.park.jsonlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mTextSource;
    TextView mTextMessage;
    String mStrJson;
    ListAdapter ld;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextSource = (TextView)findViewById(R.id.textSource);
        lv = (ListView) findViewById(R.id.listView);

        // JSON 원문 코드를 String 변수에 저장합니다.
        mStrJson = "[{name:Obama, math:50, phone: {mobile:111-1111, home:222-2222}},\n"
                + "{name:Psy, math:70, phone: {mobile:333-3333, home:444-4444}},\n"
                + "{name:Yuna, math:65, phone: {mobile:555-5555, home:666-6666}}]";
        // JSON 원문 코드를 TextView 에 표시합니다.
        mTextSource.setText(mStrJson);
    }

    // 단순 배열 JSON 파싱
    public void onBtnParse1() {
        String strJson = "[11, 22, 33, 44, 55]";
        String num[];
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONArray jAr = new JSONArray(strJson);
            num = new String[jAr.length()];
            for(int i=0; i < jAr.length(); i++) {
                // 배열에서 정수값을 구한다
                int t = jAr.getInt(i);
                num[i] = Integer.toString(t);

                //이름만 받아오기
                //JSONObject name = jAr.getJSONObject(i);
                //String a = name.getString("name");
                //num[i] = a;
            }
            ld = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, num);
            lv.setAdapter(ld);
            Toast.makeText(getApplicationContext(), "버튼1", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }
    }

    // 객체 배열 JSON 파싱
    public void onBtnParse2() {
        String strData = "";
        String[] str;
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONArray jAr = new JSONArray(mStrJson);
            str = new String[jAr.length()];
            for(int i=0; i < jAr.length(); i++) {
                // 개별 객체를 하나씩 추출
                JSONObject student = jAr.getJSONObject(i);
                str[i] = student.getString("name") + " - " +  student.getInt("math");
            }
            ld = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
            lv.setAdapter(ld);
            Toast.makeText(getApplicationContext(), "버튼2", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }

    }

    // 하위 객체 배열 JSON 파싱
    public void onBtnParse3() {
        String strData = "";
        String[] str;
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONArray jAr = new JSONArray(mStrJson);
            str = new String[jAr.length()];
            for(int i=0; i < jAr.length(); i++) {
                // 개별 객체를 하나씩 추출
                JSONObject student = jAr.getJSONObject(i);
                // 객체에서 하위 객체를 추출
                JSONObject phone = student.getJSONObject("phone");
                // 하위 객체에서 데이터를 추출
                str[i] = student.getString("name") + " - " + phone.getString("mobile") + " - " + phone.getString("home") + "\n";
            }
            ld = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, str);
            lv.setAdapter(ld);
            Toast.makeText(getApplicationContext(), "버튼3", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Log.d("tag", "Parse Error");
        }
    }

    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.btnParse1 :
                // 단순 배열 JSON 파싱
                onBtnParse1();
                break;
            case R.id.btnParse2 :
                // 객체 배열 JSON 파싱
                onBtnParse2();
                break;
            case R.id.btnParse3 :
                // 하위 객체 배열 JSON 파싱
                onBtnParse3();
                break;
        }
    }
}