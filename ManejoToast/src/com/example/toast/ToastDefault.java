package com.example.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastDefault extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);
		
		Button btnToastDefault = (Button) findViewById(R.id.buttonToast);
		Button btnToastGravity = (Button) findViewById(R.id.buttonToast2);
		Button btnToastPersonal = (Button) findViewById(R.id.buttonToast3);
		
		btnToastDefault.setOnClickListener(new OnClickListener() {		
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnToastGravity.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				Toast toastGravity = Toast.makeText(getApplicationContext(), "Toast con gravity", Toast.LENGTH_LONG);
				toastGravity.setGravity(Gravity.CENTER|Gravity.LEFT, 0, 0);
				toastGravity.show();
			}
		});
		
		btnToastPersonal.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast toastPersonal = new Toast(getApplicationContext());
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast_layout_personal,(ViewGroup) findViewById(R.id.lytLayout));
				
				TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
				txtMsg.setText("Toast personalizado");
				toastPersonal.setDuration(Toast.LENGTH_LONG);
				toastPersonal.setView(layout);
				toastPersonal.show();
			}
		});
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_toast, menu);
		return true;
	}
	*/

}
