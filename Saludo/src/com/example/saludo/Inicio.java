package com.example.saludo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class Inicio extends Activity {

    String estudio = "No - Por defecto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// layour a utilizar
	setContentView(R.layout.inicio);

	// instancia de los objetos de layout
	// el campo a introducir el nombre
	final EditText _txtNombre = (EditText) findViewById(R.id.txtNombre);
	// boton
	final ImageButton _btnAceptar = (ImageButton) findViewById(R.id.btnAceptar);

	final CheckBox _checkboxFumas = (CheckBox) findViewById(R.id.chkFumar);

	final RadioGroup _radioGroup = (RadioGroup) findViewById(R.id.radioEstudio);

	final Spinner _spinnerJuerga = (Spinner) findViewById(R.id.spinnerJuerga);
	final String[] _diasJuerga = new String[] { "Jueves", "Viernes", "Sábado", "Domingo" };
	ArrayAdapter<String> _adaptadorJuerga = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, _diasJuerga);
	_spinnerJuerga.setAdapter(_adaptadorJuerga);

	final ListView _listViewJugador = (ListView) findViewById(R.id.listViewJugadorPreferido);
	final String[] _jugadores = new String[] { "Messi", "Cristiano Ronaldo", "Falcao", "Ronney" };
	ArrayAdapter<String> _adaptadorJugador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _jugadores);
	_listViewJugador.setAdapter(_adaptadorJugador);

	// evento de pulsar el boton anteriormente instanciado
	_btnAceptar.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {

		// recogo el string del EditText
		String _strJuerga = _spinnerJuerga.getSelectedItem().toString();
		String _strNombre = _txtNombre.getText().toString();
		String _strFumas = "No";
		if (_checkboxFumas.isChecked()) {
		    _strFumas = "Si";
		}

		if (!_strNombre.equals("")) {
		    // creamos intent
		    Intent intent = new Intent(Inicio.this, Saludo.class);
		    // creamos el bundle
		    Bundle bundle = new Bundle();
		    // añadimos el contenido de nombre a "NOMBRE" - PAR CLAVE / VALOR
		    bundle.putString("FUMAS", _strFumas);
		    bundle.putString("NOMBRE", _strNombre);
		    bundle.putString("ESTUDIO", estudio);
		    bundle.putString("JUERGA", _strJuerga);

		    // añadimos la informacion al intent
		    intent.putExtras(bundle);

		    // comenzamos la siguiente actividad
		    startActivity(intent);
		} else {
		    Toast.makeText(getApplicationContext(), "Introduce un nombre", Toast.LENGTH_SHORT).show();
		}
	    }
	});

	_checkboxFumas.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
		    Toast.makeText(getApplicationContext(), "Fumas", Toast.LENGTH_SHORT).show();
		} else {
		    Toast.makeText(getApplicationContext(), "No fumas", Toast.LENGTH_SHORT).show();
		}

	    }

	});

	_radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	    public void onCheckedChanged(RadioGroup group, int checkedId) {
		// Toast.makeText(getApplicationContext(), checkedId, Toast.LENGTH_SHORT).show();
		switch (checkedId) {
		case R.id.rdBotonSi:
		    Toast.makeText(getApplicationContext(), "Estudias", Toast.LENGTH_SHORT).show();
		    estudio = "Si";
		    break;
		case R.id.rdBotonNo:
		    Toast.makeText(getApplicationContext(), "No estudias", Toast.LENGTH_SHORT).show();
		    estudio = "No";
		    break;
		case R.id.rdBotonRaraVez:
		    Toast.makeText(getApplicationContext(), "Rara vez estudias", Toast.LENGTH_SHORT).show();
		    estudio = "Rara vez";
		    break;
		default:
		    break;
		}
	    }
	});

	_listViewJugador.setOnItemClickListener(new OnItemClickListener() {

	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Toast.makeText(getApplicationContext(), _listViewJugador.getAdapter().getItem(arg2).toString(), Toast.LENGTH_SHORT).show();

	    }

	});
    }
}
