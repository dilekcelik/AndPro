package dlk.clk.andpro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{
	EditText sharedData;
	TextView dataResults;
	SharedPreferences someData;
	
	//we dont want it to change so put STATIC, others can access put PUBLIC
	public static String filename = "MySharedString";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);	
		setupVariables();
		
		someData = getSharedPreferences(filename, 0);
	}

	private void setupVariables() {
		// TODO Auto-generated method stub
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResults = (TextView) findViewById(R.id.tvLoadData);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSave:
			String stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor= someData.edit();
			editor.putString("sharedString", stringData);
			editor.commit();
			break;
			
		case R.id.bLoad:
			someData = getSharedPreferences(filename, 0);
			String dataReturned = someData.getString("sharedString", "Couldn`t Load");
			dataResults.setText(dataReturned);
			break;
		}
	}

}
