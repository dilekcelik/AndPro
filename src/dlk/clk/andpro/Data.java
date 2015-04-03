package dlk.clk.andpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Data extends Activity implements OnClickListener{
	Button start, startFor;
	EditText sendEt;
	TextView gotAnswer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		
		initialize();
	}



	private void initialize() {
		start = (Button) findViewById(R.id.bSA);
		startFor = (Button) findViewById(R.id.bSAFR);
		sendEt = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);
		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bSA:
			String bread = sendEt.getText().toString();
			//Bundles are kind of basket to send data
			Bundle basket = new Bundle();
			basket.putString("key", bread);
			
			//seupIntent Hangi Data hangi Class`a gittigini yazdik
			Intent a = new Intent(Data.this, OpenedClass.class);
			//setup basket within this intent
			a.putExtras(basket);
			//start intent
			startActivity(a);
			
			break;
			
		case R.id.bSAFR:
			Intent i = new Intent (Data.this, OpenedClass.class);
			//0 is default value
			startActivityForResult(i, 0);
			break;
		
		}
	}
	//method for 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			Bundle basket = data.getExtras();
			String s = basket.getString("answer");
			gotAnswer.setText(s);
		}
	}
	

}
