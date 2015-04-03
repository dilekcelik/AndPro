package dlk.clk.andpro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dlk.clk.andpro.R;
import dlk.clk.andpro.R.drawable;
import dlk.clk.andpro.R.id;
import dlk.clk.andpro.R.layout;
import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener {

	private TextView canWrite, canRead;
	private String state;
	boolean canR, canW;
	Spinner spinner;
	String[] paths = { "Music", "Pictures", "Downloads" };
	File path = null;
	File file = null;
	EditText saveFile;
	Button confirm, save;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);

		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		confirm = (Button) findViewById(R.id.bConfirm);
		save = (Button) findViewById(R.id.bSaveFile);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		
		checkState();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			canWrite.setText("true");
			canRead.setText("true");
			canW = canR = true;

		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;

		} else {
			canWrite.setText("false");
			canRead.setText("false");
			canW = canR = false;
		}
		
		
	}
	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch(position){
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bConfirm:
			save.setVisibility(View.VISIBLE);
			break;
			
		case R.id.bSaveFile:
			String f = saveFile.getText().toString();
			//dosya adi ve uzantisi
			file = new File(path, f + ".png");
			
			checkState();
			if(canW == canR == true){
				path.mkdirs();
			
				
			try {
				InputStream is = getResources().openRawResource(R.drawable.yellowsquare);
				OutputStream os = new FileOutputStream(file);
				byte[] data = new byte[is.available()];
				is.read(data);
				os.write(data);
				is.close();
				os.close();
				
				//Update files for the user to use
				//Kaydedildi diye kisa uyari gonderdigimiz kucuk pencere
				Toast t = Toast.makeText(ExternalData.this, "File has been Saved", Toast.LENGTH_LONG);
				t.show();
				
				//update(ClassName, new String Array, null, scan completed)
				MediaScannerConnection.scanFile(ExternalData.this, new String[] {file.toString()}, null, 
						new MediaScannerConnection.OnScanCompletedListener() {
							
							@Override
							public void onScanCompleted(String path, Uri uri) {
								// TODO Auto-generated method stub
								Toast t = Toast.makeText(ExternalData.this, "Scan completed", Toast.LENGTH_SHORT);
								t.show();
							}
						});
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			break;
		
		}
	}

}
