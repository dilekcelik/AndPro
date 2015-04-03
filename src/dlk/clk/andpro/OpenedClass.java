package dlk.clk.andpro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

//you can extend 1 class but implements more than one class
//differences between extend and implements
public class OpenedClass extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread, setData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);

		initialize();
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		//key leri prfs.xml den aldik bilgileri
		String et = getData.getString("name", "Dilek is...");
		String values = getData.getString("list", "4");
		if(values.contentEquals("1")){
			question.setText(et);
		}
			
				
		
		//getting basket data from Data Class
		//Bundle gotBasket = getIntent().getExtras();
		//"key" is same from Data Class REMEMBER!!!
		//gotBread= gotBasket.getString("key");
		//question.setText(gotBread);

	}

	private void initialize() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		returnData.setOnClickListener(this);
		selectionList.setOnCheckedChangeListener(this);
	
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent person = new Intent ();
		Bundle backpack = new Bundle();
		backpack.putString("answer", setData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rCrazy:
			setData = "Probably right!";
			break;
			
		case R.id.rNice:
			setData = "Definitely right!";
			break;
			
		case R.id.rBoth:
			setData = "Spot On!";
			break;
		}
		
		test.setText(setData);
	}

}