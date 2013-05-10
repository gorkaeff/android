package com.example.saludo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Saludo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.saludo);

	TextView _txtSaludo = (TextView) findViewById(R.id.txtSaludo);
	TextView _txtFumas = (TextView) findViewById(R.id.txtFumas);
	TextView _txtEstudio = (TextView) findViewById(R.id.txtEstudio);
	TextView _txtJuerga = (TextView) findViewById(R.id.txtJuerga);

	// Recuperar informacion
	Bundle bundle = this.getIntent().getExtras();

	_txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));
	_txtSaludo.setTextSize(30);
	_txtSaludo.setTextColor(Color.RED);

	_txtFumas.setText("Fumas :" + bundle.getString("FUMAS"));

	_txtEstudio.setText("Estudias : " + bundle.getString("ESTUDIO"));

	_txtJuerga.setText("Sueles salir el " + bundle.getString("JUERGA"));
    }
}
