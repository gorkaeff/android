package com.example.sqliteejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BasesDatosActivity extends Activity {

	MiBaseDatos _db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bases_datos);
		
		final Button _btn_busqueda = (Button) findViewById(R.id._buscar);
		final Button _btn_insert = (Button) findViewById(R.id._insertar);
		final Button _btn_borrar = (Button) findViewById(R.id._borrar);
		
		final EditText _ins_nombre = (EditText) findViewById(R.id._ins_nombre);
		final EditText _ins_dni = (EditText) findViewById(R.id._ins_dni);
		final EditText _cons_dni = (EditText) findViewById(R.id._busc_dni);
		final EditText _dni_del = (EditText) findViewById(R.id._dni_del);
		
		final TextView _res_txt = (TextView) findViewById(R.id.textViewResultado);
		final TextView _nom_txt = (TextView) findViewById(R.id.textViewNombreDos);
		final TextView _res_del = (TextView) findViewById(R.id.textViewResultado2);
		
		_db = new MiBaseDatos(this, "BDUsuarios", null, 1);
		
		_btn_insert.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (_db != null){
					String _nombre = _ins_nombre.getText().toString();
					String _dni = _ins_dni.getText().toString();
					if (_nombre.length() > 0 && _dni.length() > 0){
						int _res = _db.insertar(_nombre, _dni);
						
						if (_res == 1){
							_res_txt.setText("Resultado: Insertado correctamente");
						}else if (_res == 0){
							_res_txt.setText("Resultado: Error al insertar");
						}else{
							_res_txt.setText("Resultado: DNI duplicado");
						}
					}else{
						Toast.makeText(getApplicationContext(), "Rellene los campos para insertar", Toast.LENGTH_SHORT).show();
					}

				}
			}
		});
		
		_btn_busqueda.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String _dni = _cons_dni.getText().toString();
				if (_dni.length() > 0){
					String _nom = _db.buscar(_dni);
					_nom_txt.setText("Nombre: " + _nom);
				}else{
					Toast.makeText(getApplicationContext(), "Introduce DNI", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		_btn_borrar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String _dni = _dni_del.getText().toString();
				if (_dni.length() > 0){
					int _res = _db.eliminar(_dni);
					
					if (_res == 0){
						_res_del.setText("Resultado: No existe el DNI");
					}else{
						_res_del.setText("Resultado: Eliminado correctamente");
					}
				}else{
					Toast.makeText(getApplicationContext(), "Introduce DNI a borrar", Toast.LENGTH_LONG).show();
				}

			}
		});
		
	}
}
