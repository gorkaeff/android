package com.example.ejemplolistview;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

	private Dia[] _val = new Dia[]{new Dia("Lunes"), new Dia("Martes"), new Dia("Miercoles"), new Dia("Jueves"), new Dia("Viernes")};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		//Creamos el adaptador
		AdaptadorDia adaptador = new AdaptadorDia(this);
		//Rescatamos el listview
		ListView _horario = (ListView) findViewById(R.id.listViewDias);
		//enlazar adaptador con ListView
		_horario.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_view, menu);
		return true;
	}
	
	class AdaptadorDia extends ArrayAdapter<Dia> {

		Activity context;

		AdaptadorDia(Activity context) {
		    super(context, R.layout.activity_dias, _val);
		    this.context = context;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			//Generamos nuestro LayoutInflater
		    LayoutInflater inflater = context.getLayoutInflater();
		    //Creamos la vista que necesitamos
		    View item = inflater.inflate(R.layout.activity_dias, null);
		    
		    //obtenemos el textview que mostrara el dia de la semana
		    TextView _dia = (TextView) item.findViewById(R.id.txtDiaSemana);
		    //especificamos el dia de la semana que queremos que muestre
			_dia.setText(_val[position].getDia());
			// devolvemos el objeto para que lo pueda mostrar
		    return (item);
		}
		}

}
