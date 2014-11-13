package com.example.annotations;

import java.io.ByteArrayOutputStream;

import wazzatimagescanner.WLClientAcitivityInfo;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends Activity /*implements WLAuthenticateListener */ {
	 //
	 ImageView imgFavorite;
	 Button B;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WLClientAcitivityInfo.setContext(this);
		//WLAuthenticate.Authenticate("a5b6bd09f915080ea998" );  //makes app crash for some reason
		B = (Button) findViewById(R.id.textView1);
		imgFavorite = (ImageView)findViewById(R.id.imageView1);
	    B.setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            open();
	         }
	      });
	}
	/*
	@Override
	public ProgressBar getProgressBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAuthenticationDone() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void whileAuthenticating() {
		// TODO Auto-generated method stub
		
	}
	*/
	static final int REQUEST_IMAGE_CAPTURE = 1;

	public void open(){
	      Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	      startActivityForResult(intent, 0);
	   }

	@Override
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	      // TODO Auto-generated method stub
		  String scanResult = "";
	      super.onActivityResult(requestCode, resultCode, data);
	      Bitmap bp = (Bitmap) data.getExtras().get("data");
	      ByteArrayOutputStream stream = new ByteArrayOutputStream();
	      bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
	      byte[] bytearray = stream.toByteArray();
	      
	      Display display = getWindowManager().getDefaultDisplay();
	      int width = display.getWidth();
	      int height = display.getHeight();
	      
	      //scanResult = WLImageScanner.scan(getFrameWidth(), getFrameHeight(), bytearray );  //The call to the SDK   
	      imgFavorite.setImageBitmap(bp);
	      TextView t = (TextView)findViewById(R.id.textView2);
		  t.setText(" more ");
		  //t.setText(scanResult);
		  
	   }

}
