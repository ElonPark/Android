package kr.ac.induk.park.project01;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends Activity {


    ListAdapter adt;
    String str[];
    String image[];
    ListAdapter la;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isNetWorkAvailable()) {
            DownTask task = new DownTask();
            task.execute("http://induk.awooltech.com/test/test.php?name=all");
        }




    }
    private  boolean isNetWorkAvailable(){
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable())
            available = true;
        return  available;

    }

    private  String downUrl(String strUrl) throws IOException
    {
        String s = null;
        byte[] buffer = new byte[1000];
        InputStream istram = null;


        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();
            istram = urlConnection.getInputStream();

            istram.read(buffer);
            s =new String(buffer);


        }catch (Exception e){
            Toast.makeText(getBaseContext(), "다운로드 실패\n"+e.toString(),Toast.LENGTH_LONG).show();

        }finally {
            istram.close();
        }
        return  s;
    }

    private class DownTask extends AsyncTask<String, String, String>
    {

        String s = null;
        @Override
        protected String doInBackground(String... url) {
           try{
               s = downUrl(url[0]);

           }catch (Exception e){
               Toast.makeText(getBaseContext(), "URL 다운로드 실패\n"+e.toString(),Toast.LENGTH_LONG).show();
           }

            return  s;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(), "시작!",Toast.LENGTH_SHORT).show();

            try {
                // JSON 구문을 파싱해서 JSONArray 객체를 생성
                JSONArray jAr = new JSONArray(s);
                image = new String[jAr.length()];
                str = new String[jAr.length()];
                for(int i=0; i < jAr.length(); i++) {
                    // 개별 객체를 하나씩 추출
                    JSONObject snsd = jAr.getJSONObject(i);
                    // 객체에서 데이터를 추출
                    str[i] = snsd.getString("name") + " / " + snsd.getString("song") + "\n";
                    image[i] = snsd.getString("image");
                }

                lv = (ListView) findViewById(R.id.listView);
                la = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, str);
                lv.setAdapter(la);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                        try {
                            String address = image[pos];
                            Intent Act = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                            startActivity(Act);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"브라우징 오류 "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (JSONException e) {
                Toast.makeText(getBaseContext(), "Parse Error\n"+e.toString(),Toast.LENGTH_LONG).show();
                Log.d("tag", "Parse Error");
            }
        }
    }







}

       // String strData = "";
      //  listView = (ListView) findViewById(R.id.list);
      //  JsonTask task = new JsonTask();
       // task.execute("http://induk.awooltech.com/test/test.php?name=all");

/*ListView listView;
    Document doc;
    String mStrJson;
    ListAdapter adp;
    JSONObject jo;


        try {
           JSONObject jsonObj = fetchJSONFromUrl("http://induk.awooltech.com/test/test.php?name=all");
          //  JSONObject jsonObj = fetchJSONFromUrl("http://storage.googleapis.com/automotive-media/music.json");
            if (jsonObj == null) {
                return;
            }
            /*
            JSONArray tracks = jsonObj.getJSONArray();

            for(int i=0; i < tracks.length(); i++) {
                // 개별 객체를 하나씩 추출
                JSONObject student = tracks.getJSONObject(i);
                // 객체에서 데이터를 추출
                strData += student.getString("name");
            }

            //adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strData);

            strData += jsonObj.getString("name") +"-"+ jsonObj.getString("song");

            tv.setText(strData+"test");

            //listView.setAdapter(adp);

        }catch (Exception e){
             Toast.makeText(getBaseContext(), "실패", Toast.LENGTH_SHORT).show();
        }



    }

        @SuppressLint("NewApi")
    private  class  JsonTask extends AsyncTask<String, Void, Document>{

        private Activity context;


        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try{

                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db;

                db= dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();



            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }
        @Override
          protected void onPostExecute(Document doc) {
            NodeList nodeList = doc.getElementsByTagName("name");
            String s[] = new String[nodeList.getLength()];





        }
    }
    private JSONObject fetchJSONFromUrl(String urlString) {
        BufferedReader reader = null;
        try {
            URLConnection urlConnection = new URL(urlString).openConnection();
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return new JSONObject(sb.toString());
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "로딩 실패", Toast.LENGTH_SHORT).show();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
*/

