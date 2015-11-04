package kr.ac.induk.park.xmllist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {


    Document doc = null;
    ListAdapter theAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);

    }

    public void onClick(View v) {
        GetXMLTask task = new GetXMLTask(this);

        task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=37&gridy=127");

    }

    @SuppressLint("NewApi")
    private class GetXMLTask extends AsyncTask<String, Void, Document> {
        private Activity context;


        public GetXMLTask(Activity context) {
            this.context = context;
        }

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db;

                db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            NodeList nodeList = doc.getElementsByTagName("data");
            String s[] = new String[nodeList.getLength()];


            for (int i = 0; i < nodeList.getLength(); i++) {

                s[i] = "" + (i+1) + ": 날씨 정보: ";
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;

                NodeList nameList = fstElmnt.getElementsByTagName("temp");
                Element nameElemnt = (Element) nameList.item(0);
                nameList = nameElemnt.getChildNodes();

                s[i] += "온도= " + ((Node) nameList.item(0)).getNodeValue() + " ,";

                NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
                Element websiteElement = (Element) websiteList.item(0);
                websiteList = websiteElement.getChildNodes();
                s[i] += "날씨 = " + ((Node) websiteList.item(0)).getNodeValue() + "\n";


            }
           theAdapter  = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_list_item_1,s);

            list.setAdapter(theAdapter);

        }

    }



}