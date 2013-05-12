package com.example.viewcompuesto;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ViewCompuestoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_controlador);
		ControladorVoto _mi_view = (ControladorVoto) findViewById(R.id.Voto);
		//_mi_view.isInEditMode();
		final TextView _nombre = (TextView) findViewById(R.id._nombre);
		final TextView _puntuacion = (TextView) findViewById(R.id._puntuacion);
		
		_mi_view.setOnVoteListener(new OnVoteListener() {
			
			public void onVote(float voto, String nombre) {
				_nombre.setText("Nombre: " + nombre);
				_puntuacion.setText("Puntuacion: " + String.valueOf(voto));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_view_compuesto, menu);
		return true;
	}

}
