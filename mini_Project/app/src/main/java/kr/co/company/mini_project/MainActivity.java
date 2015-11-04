package kr.co.company.mini_project;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    TextView tV;
    ListView list;
    ArrayAdapter<String> adapter;
    String j_str[];
    String image[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        if (isNetworkAvailable()) {
            Log.d("jin", "네트워크 연결성공");

            JsonTask jsonTask = new JsonTask();
            jsonTask.execute("http://induk.awooltech.com/test/test.php?name=all");
        } else {
            Toast.makeText(getBaseContext(), "Network is not Available", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isNetworkAvailable() {
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            available = true;

        return available;
    }


    private String downloadUrl(String strurl) throws IOException {

        Log.d("jin", "downloadUrl 실행");
        String s = null;
        byte buffer[] = new byte[1000];
        InputStream iStream = null;

        try {
            URL url = new URL(strurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            iStream.read(buffer);
            s = new String(buffer);
        } catch (Exception e) {
            Log.d("jin", "down에서 에러");
        }finally {
            iStream.close();
        }
        return s;
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        String s = null;

        @Override
        protected String doInBackground(String... url) {
            Log.d("jin", "doInBack 실행");
            try{
                s = downloadUrl(url[0]);

            }catch (Exception e){
                Log.d("jin", "doin 에러");
            }

            Log.d("jin", "doInBack 값 리턴");
            return s;
        }


        @Override
        protected void onPostExecute(String jsonCode) {
            Log.d("jin", "onPostExecute 실행");


            try {
                // JSON 구문을 파싱해서 JSONArray 객체를 생성
                JSONArray jAr = new JSONArray(jsonCode);
                j_str = new String[jAr.length()];
                image = new String[jAr.length()];
                Log.d("jin", "jAr.length의 크기 = " + jAr.length());

                for(int i=0; i < jAr.length(); i++) {
                    // 개별 객체를 하나씩 추출
                    JSONObject girls = jAr.getJSONObject(i);
                    j_str[i] = "Name : " + girls.getString("name") + "   Song : " +  girls.getString("song");
                    image[i] = girls.getString("image");
                }
                Log.d("jin", "for 끝  ㅇㅇ ");

                list = (ListView) findViewById(R.id.myList);
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, j_str);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), j_str[position], Toast.LENGTH_LONG).show();

                        try {
                            String address = image[position];
                            Intent Act = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                            startActivity(Act);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Log.d("jin", "onPostExecute 종료");
            } catch (JSONException e) {
                Log.d("jin", "Json Error 났다");
            }

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
