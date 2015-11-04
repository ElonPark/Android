package com.example.exam02_20150129;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBManager {
	static String TAG = "DBManager";
	
	private static final String DATABASE_NAME = "exam01.db";
	//������ ������ ���̽� �̸�. ���ϴ� ��� ���氡��.
	private static final int DATABASE_VERSION = 1;
	
	private static SQLiteDatabase db; // �����ͺ��̽� �۾��� �� �� �ְ� ����
	
    private static DBManagerOpenHelper dbHelper; // SQLite�� Ȱ���ϴ� ���[Open�̳� ��������]
    private Context mContext;
	
	public DBManager(Context _context) {
    	Log.v(TAG,"DataBaseManager(Context _context)");
    	mContext = _context;
        if(dbHelper == null){
        	dbHelper = new DBManagerOpenHelper(_context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        open();
    }
	
    // DB ����
    public void open() throws SQLiteException {
    	Log.v(TAG,"DataBaseManager / open()");
        try {
        	if(db == null){
        		db = dbHelper.getWritableDatabase(); // �а� ���� ���� Open
        	}
        } catch (SQLiteException ex) {
        	ex.printStackTrace();
        }
    }   

    // DB �ݱ�
    public void close() {
    	Log.v(TAG, "DB Connection close()");
        db.close();
    }      
    
    
    public void insertValue(String _n, String _p, String _id){
    	String _sql = "insert into test_table01(name, pwd, id) VALUES("
    				+"\'"+_n+"\', "
    				+"\'"+_p+"\', "
    				+"\'"+_id+"\')";
    	
    	db.execSQL(_sql);
    }
    
    public ArrayList<MainActivity_Value> getValueList(){
    	ArrayList<MainActivity_Value> _temp = new ArrayList<MainActivity_Value>();
    	String _sql = "SELECT * FROM test_table01";
    	Cursor _c = db.rawQuery(_sql, null);
    	
    	if(_c != null){
    		try{
    			
    			if(_c.moveToFirst()){    				
    				do{
    					MainActivity_Value _t = new MainActivity_Value();
    					_t.id = _c.getString(_c.getColumnIndex("id"));
    					_t.pwd = _c.getString(_c.getColumnIndex("pwd"));
    					_t.name = _c.getString(_c.getColumnIndex("name"));
    					
    					_temp.add(_t);
    				}while(_c.moveToNext());
    			}    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			_c.close();
    		}
    	}
    	
    	return _temp;
    }
    
    

	// create, update, open�� ���� Ŭ����
    private static class DBManagerOpenHelper extends SQLiteOpenHelper { 
    	 // ������ ���̺� ����
        private static final String TEST_TABLE01 = "CREATE TABLE IF NOT EXISTS " 
        		+"test_table01( "
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT "+", "
                +"name VARCHAR, phone VARCHAR, id VARCHAR, pwd VARCHAR)";
        
        private static final String TEST_TABLE011 = "CREATE TABLE IF NOT EXISTS " 
        		+"test_table01( "
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT "+", "
                +"name VARCHAR, phone VARCHAR, id VARCHAR, pwd VARCHAR)";

        public DBManagerOpenHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        } 
        // DB ������ ������ �� ȣ���
        public void onCreate(SQLiteDatabase _db) {
        	Log.v(TAG, "make TABLE : "+TEST_TABLE01);
        	
            _db.execSQL(TEST_TABLE01);
            //_db.execSQL(TEST_TABLE011);
        }
        // �������� �� �� ȣ���[������ ��ġ���� ���� ��
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion,   int _newVersion) {


        }

    }

}
