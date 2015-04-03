package dlk.clk.andpro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	
	String  classes[] = {"MainActivity","TextPlay","Email","Camera",
			"Data","GFX","CFXSurface", "SoundStuff", "Slider", "Tabs", 
			"SimpleBrowser", "Flipper", "SharedPrefs", "InternalData",
			"ExternalData", "SQLiteExample", "Accelerate",
			"HttpExample", "Voice", "TextVoice", "StatusBar", "SeekBarVolume"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//FULL screen setUp
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		
		//usingArrayAdapter like ListArray
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1 , classes));
				
	}	
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		//START Activity with a CLASS OBJECT
		try{
		Class ourClass = Class.forName("dlk.clk.andpro." + cheese);
		Intent ourIntent = new Intent(Menu.this, ourClass);
		startActivity(ourIntent);
		} catch (ClassNotFoundException e){
			e.printStackTrace(); //this is for debugging
		}
	}


	
	//CREATEMENU AND MENU ITEMS
	@Override
	public void closeOptionsMenu() {
		// TODO Auto-generated method stub
		super.closeOptionsMenu();
	}


	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}


	
	//Making MENU ITEMS DO SOMETHING
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent i = new Intent("android.intent.action.ABOUTUS");
			startActivity(i);
			break;
		
		case R.id.preferences:
			Intent p = new Intent("android.intent.action.PREFS");
			startActivity(p);
			break;
			
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}




}
