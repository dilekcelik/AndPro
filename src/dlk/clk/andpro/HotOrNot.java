package dlk.clk.andpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//this is a class where we will make all SQL stuffs
public class HotOrNot {
	
	//public= accessible static final=never change
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";
	
	//Setting Up DATABASE
	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;
	
	
	
	//instance of the class(DbHelper)
	private DbHelper ourHelper;
	//cret context for the class
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	///creating a new class
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//when we setup database, we will create table
			//SQL "(" column yapmak icin ve kolumlari alt alta yazdik burda
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_NAME + " TEXT NOT NULL, "+
			KEY_HOTNESS + " TEXT NOT NULL);"
			
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//Upgrades our database
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public HotOrNot(Context c){
		ourContext = c;
	}
	
	//open database method
	public HotOrNot open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	//creating another method
	public void close(){
		ourHelper.close();
		
	}

	public void createEntry(String name, String hotness) {
		//setUp content values, KIND OF OUR BUNDLE
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
	
	public String getData() {
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		//use cursor to read from database
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null );
		String result = "";
		
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iName) +
					" " + c.getString(iHotness) + "\n";
		}
		return result;
	}
	
	
	//SETTING UP THESE TWO METHODSS
	public String getName(long l) throws SQLException{
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,	null,	null,	null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	public String getHotness(long l) throws SQLException{
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,	null,	null,	null);
		if(c != null){
			c.moveToFirst();
			//HOTNESS COLUMN ONDAN 2
			String hotness = c.getString(2);
			return hotness;
		}
		return null;
	}
	
	//UPDATE METHOD
	public void updateEntry(long lRow, String mName, String mHotness) throws SQLException{
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_HOTNESS, mHotness);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
	}

	public void deleteEntry(long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
}
