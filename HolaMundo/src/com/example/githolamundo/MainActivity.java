package com.example.githolamundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//layout de nuestra pantalla asociada
		setContentView(R.layout.activity_main);
		
		//Creamos los elementos
		final EditText txtUsuario = (EditText) findViewById(R.id.txtUsuario);
		final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
		final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		
		
		
		//evento al hacer click sobre boton
		btnSubmit.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				//recojo los valores en una variable string para comprobar que no son vacios
				String valorUsuario = txtUsuario.getText().toString();
				String valorPassword = txtPassword.getText().toString();
				
				if (valorUsuario.length() > 0 && valorPassword.length() > 0){
					//creacion del intent que actividad y cual abrira
					Intent intent = new Intent(MainActivity.this, SaludoActivity.class);
					//creacion de bundle para pasar parametros
					Bundle bundle = new Bundle();
					//asociamos los valores "clave - valor" de usuario y password
					bundle.putString("USUARIO", valorUsuario);
					bundle.putString("PASSWORD", valorPassword);
					//lo asociamos a nuestro intent
					intent.putExtras(bundle);
					//comenzamos la actividad
					startActivity(intent);
				}else{
					//Si hay campos vacios, mostrar un mensaje de aviso
					Toast.makeText(getApplicationContext(), "Debes rellenar los campos", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
