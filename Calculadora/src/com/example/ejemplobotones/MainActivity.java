package com.example.ejemplobotones;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    boolean _n1_empty = true;
    boolean _n2_empty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	// Creo los valores del spinner con un layout del sistema
	final String[] datosSpinner = new String[] { "Sumar", "Restar", "Multiplicar", "Dividir" };
	ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosSpinner);

	// Adjunto a mi spinner lo creado anteriormente
	final Spinner Opciones = (Spinner) findViewById(R.id._select_op);
	adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	Opciones.setAdapter(adaptador);

	// Traigo los elementos
	final Button _ejecucion = (Button) findViewById(R.id._ejecutar);
	_ejecucion.setEnabled(false);
	final EditText _n1 = (EditText) findViewById(R.id._numero1);
	final EditText _n2 = (EditText) findViewById(R.id._numero2);
	final EditText _res = (EditText) findViewById(R.id._resultado);
	// final Spinner _opciones = (Spinner) findViewById(R.id._select_op);

	_n1.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		if (_n1.getText().length() > 0) {
		    _n1_empty = false;
		} else {
		    _n1_empty = true;
		}

		if (!_n1_empty && !_n2_empty) {
		    _ejecucion.setEnabled(true);
		} else {
		    _ejecucion.setEnabled(false);
		}
	    }
	});

	_n2.addTextChangedListener(new TextWatcher() {
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    public void afterTextChanged(Editable s) {
		if (s.length() > 0) {
		    _n2_empty = false;
		} else {
		    _n2_empty = true;
		}

		if (!_n1_empty && !_n2_empty) {
		    _ejecucion.setEnabled(true);
		} else {
		    _ejecucion.setEnabled(false);
		}
	    }
	});

	_ejecucion.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Float _a = new Float(_n1.getText().toString());
		Float _b = new Float(_n2.getText().toString());

		if (Opciones.getSelectedItem().equals("Sumar")) {
		    _res.setText(String.valueOf(suma(_a, _b)));
		} else if (Opciones.getSelectedItem().equals("Restar")) {
		    _res.setText(String.valueOf(resta(_a, _b)));
		} else if (Opciones.getSelectedItem().equals("Multiplicar")) {
		    _res.setText(String.valueOf(multiplicacion(_a, _b)));
		} else if (Opciones.getSelectedItem().equals("Dividir")) {
		    if (_b != 0) {
			_res.setText(String.valueOf(division(_a, _b)));
		    } else {
			Toast.makeText(getApplicationContext(), "División entre 0 - No permitida", Toast.LENGTH_SHORT).show();
		    }
		} else {
		    _res.setText("Error en la aplicación");
		}

	    }
	});

    }

    public float suma(float _a, float _b) {
	return _a + _b;
    }

    public float multiplicacion(float _a, float _b) {
	return _a * _b;
    }

    public float resta(float _a, float _b) {
	return _a - _b;
    }

    public float division(float _a, float _b) {
	return _a / _b;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

}
