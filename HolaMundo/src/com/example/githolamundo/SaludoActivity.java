package com.example.githolamundo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class SaludoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Ahora en vez de activity_main, asociamos su layout activity_saludo
		setContentView(R.layout.activity_saludo);
		
		//Declaramos la etiqueta con el saludo a mostrar
		final TextView lblSaludo = (TextView) findViewById(R.id.lblSaludo);
		
		//Recuperamos la informacion
		Bundle bundle = this.getIntent().getExtras();
		
		//Imprimimos el saludo ejemplo: Bienvenido Gorka, su password es: FFCCC
		lblSaludo.setText("Bienvenido " + bundle.getString("USUARIO") + ", su password es: " + bundle.getString("PASSWORD"));
		//Cambio de la letra de la etiqueta
		lblSaludo.setTextSize(30);
		//Cambio de color
		lblSaludo.setTextColor(Color.RED);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
