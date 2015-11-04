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
	//생성할 데이터 베이스 이름. 원하는 대로 변경가능.
	private static final int DATABASE_VERSION = 1;
	
	private static SQLiteDatabase db; // 데이터베이스 작업을 할 수 있게 제공
	
    private static DBManagerOpenHelper dbHelper; // SQLite를 활용하는 방법[Open이나 버젼관리]
    private Context mContext;
	
	public DBManager(Context _context) {
    	Log.v(TAG,"DataBaseManager(Context _context)");
    	mContext = _context;
        if(dbHelper == null){
        	dbHelper = new DBManagerOpenHelper(_context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        open();
    }
	
    // DB 열기
    public void open() throws SQLiteException {
    	Log.v(TAG,"DataBaseManager / open()");
        try {
        	if(db == null){
        		db = dbHelper.getWritableDatabase(); // 읽고 쓰기 모드로 Open
        	}
        } catch (SQLiteException ex) {
        	ex.printStackTrace();
        }
    }   

    // DB 닫기
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
    
    

	// create, update, open을 위한 클래스
    private static class DBManagerOpenHelper extends SQLiteOpenHelper { 
    	 // 내정보 테이블 생성
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
        // DB 파일이 생성될 때 호출됨
        public void onCreate(SQLiteDatabase _db) {
        	Log.v(TAG, "make TABLE : "+TEST_TABLE01);
        	
            _db.execSQL(TEST_TABLE01);
            //_db.execSQL(TEST_TABLE011);
        }
        // 버젼관리 할 때 호출됨[버젼이 일치하지 않을 때
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion,   int _newVersion) {


        }

    }

}
