package dlk.clk.andpro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener{
	ViewFlipper flippy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fliper);
		
		flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
		flippy.setOnClickListener(this);
		//every 5 scnd, it changes..
		flippy.setFlipInterval(500);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		flippy.showNext();
	}
	
	
	
}
