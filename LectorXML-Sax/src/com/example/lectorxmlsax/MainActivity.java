package com.example.lectorxmlsax;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button btnCargar;
	private TextView txtTitulo;
	private TextView txtLink;
	//private TextView txtCategory;
	private TextView txtDescripcion;
	private TextView txtPubDate;
	//private TextView txtCreator;
	private TextView txtGuid;

	private List<Noticia> noticias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lector_sax_acrivity);

		btnCargar = (Button) findViewById(R.id.btnCargar);
		txtTitulo = (TextView) findViewById(R.id.txtTitulo);
		txtLink = (TextView) findViewById(R.id.txtLink);
		txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
		//txtCategory = (TextView) findViewById(R.id.txtCategory);
		txtPubDate = (TextView) findViewById(R.id.txtPubDate);
		//txtCreator = (TextView) findViewById(R.id.txtCreador);
		txtGuid = (TextView) findViewById(R.id.txtGuid);

		btnCargar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// Sin Tarea Asíncrona
				// RssParserSax saxparser =
				// new RssParserSax("http://212.170.237.10/rss/rss.aspx");

				// noticias = saxparser.parse();

				// Tratamos la lista de noticias
				// Por ejemplo: escribimos los títulos en pantalla
				// txtResultado.setText("");
				// for(int i=0; i<noticias.size(); i++)
				// {
				// txtResultado.setText(
				// txtResultado.getText().toString() +
				// System.getProperty("line.separator") +
				// noticias.get(i).getTitulo());
				// }

				// Con Tarea Asíncrona
				CargarXmlTask tarea = new CargarXmlTask();
				// tarea.execute("http://212.170.237.10/rss/rss.aspx");
				tarea.execute("http://www.labaroteca.com/feed");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lector_sax_acrivity, menu);
		return true;
	}

	// Tarea Asíncrona para cargar un XML en segundo plano
	private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

		protected Boolean doInBackground(String... params) {

			RssParserSax saxparser = new RssParserSax(params[0]);

			noticias = saxparser.parse();

			return true;
		}

		protected void onPostExecute(Boolean result) {

			// Tratamos la lista de noticias
			// Por ejemplo: escribimos los títulos en pantalla
			txtTitulo.setText("");
			txtLink.setText("");
			txtDescripcion.setText("");
			txtPubDate.setText("");
			txtGuid.setText("");
			for (int i = 0; i < noticias.size(); i++) {
				txtTitulo.setText(noticias.get(i).getTitulo());
				txtLink.setText(noticias.get(i).getLink());
				txtDescripcion.setText(noticias.get(i).getDescripcion());
				txtPubDate.setText(noticias.get(i).getFecha());
				txtGuid.setText(noticias.get(i).getGuid());
			}
		}
	}
}
