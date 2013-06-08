package mi.paquete;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MiSqliteActivity extends Activity {
	/** Called when the activity is first created. */
	Mi_BD _bd;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button _btn_busqueda = (Button) findViewById(R.id._buscar);
		final Button _btn_insert = (Button) findViewById(R.id._insertar);
		final EditText _ins_nombre = (EditText) findViewById(R.id._ins_nombre);
		final EditText _ins_dni = (EditText) findViewById(R.id._ins_dni);
		final EditText _cons_dni = (EditText) findViewById(R.id._busc_dni);
		final TextView _res_txt = (TextView) findViewById(R.id._res_ins);
		final TextView _nom_txt = (TextView) findViewById(R.id._res_nom);
		final Button _btn_borrar = (Button) findViewById(R.id._borrar);
		final EditText _dni_del = (EditText) findViewById(R.id._dni_del);
		final TextView _res_del = (TextView) findViewById(R.id._res_del);
		
		_bd = new Mi_BD(this, "Mi_DB", null, 1);

		_btn_insert.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (_bd != null) {
					String _nombre = _ins_nombre.getText().toString();
					String _dni = _ins_dni.getText().toString();
					int _res = _bd.insertar(_nombre, _dni);
						
					if(_res == 1){
						_res_txt.setText("Resultado: Insertado correctamente");
					}else if (_res == 0){
						_res_txt.setText("Resultado: Error al insertar");
					}else{
						_res_txt.setText("Resultado: DNI duplicado");
					}
					
				}
			}

		});

		_btn_busqueda.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String _dni = _cons_dni.getText().toString();
				String _nom = _bd.buscar(_dni);
				_nom_txt.setText("Nombre: "+_nom);
			}
		});
			
		_btn_borrar.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String _dni = _dni_del.getText().toString();
				int _res = _bd.eliminar(_dni);
				
				if(_res==0){
					_res_del.setText("Resultado: No existe el DNI");
				}else{
					_res_del.setText("Resultado: Eliminado correctamente");
				}
				
			}
			
		});
		
		
	}

}