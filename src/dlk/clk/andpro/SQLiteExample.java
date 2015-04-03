package dlk.clk.andpro;

import dlk.clk.andpro.R;
import dlk.clk.andpro.R.id;
import dlk.clk.andpro.R.layout;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlliteexample);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLopenView);
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		
		sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);
		sqlGetInfo.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSQLUpdate:
			
			boolean didItWork = true;
			try {
			String name = sqlName.getText().toString();
			String hotness = sqlHotness.getText().toString();
		
			//setUp variables to HotOrNot CLASS
			HotOrNot entry = new HotOrNot(SQLiteExample.this);
			entry.open();
			entry.createEntry(name, hotness); //we`ll create this method
			entry.close();
			} catch (Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Heck Yea!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show(); 
				
			} finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success!");
					d.setContentView(tv);
					d.show();
				}
				
			}

			break;
			case R.id.bSQLopenView:
			Intent i = new Intent("android.intent.action.SQLVIEW");
			startActivity(i);
			break;
			
			case R.id.bgetInfo:
				try {
				String s = sqlRow.getText().toString();
				long l =Long.parseLong(s);
				HotOrNot hon = new HotOrNot(this);
				hon.open();
				String returnedName = hon.getName(l);
				String returnedHotness = hon.getHotness(l);
				hon.close();
				
				sqlName.setText(returnedName);
				sqlHotness.setText(returnedHotness);
				} catch (Exception e){
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show(); 
					
				}
				break;
			case R.id.bSQLmodify:
				try{
				String mName = sqlName.getText().toString();
				String mHotness = sqlHotness.getText().toString();
				String sRow = sqlRow.getText().toString();
				long lRow = Long.parseLong(sRow);
				
				HotOrNot ex = new HotOrNot(this);
				ex.updateEntry(lRow, mName, mHotness);
				ex.open();
				} catch (Exception e){
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show(); 
					
				}
				
				break;
			case R.id.bSQLdelete:
				try{
				String sRow1 = sqlName.getText().toString();
				long lRow1 = Long.parseLong(sRow1);
				HotOrNot ex1 = new HotOrNot(this);
				ex1.open();
				ex1.deleteEntry(lRow1);
				ex1.close();
				} catch (Exception e){
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show(); 
					
				}
				break;
			}
		}
}