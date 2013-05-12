package com.example.viewcompuesto;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;

public class ControladorVoto extends LinearLayout {

	private Button _votar;
	private RatingBar _estrellas;
	private SeekBar _barra;
	private Spinner _list_peliculas;
	private OnVoteListener listener;
	final String[] _datos = new String[] { "Scarface", "Titanic",
			"Pretty Woman", "Star Wars", "El rey León", "E.T" };

	public ControladorVoto(Context context) {
		super(context);
		init(context);
	}

	public ControladorVoto(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		String infService = Context.LAYOUT_INFLATER_SERVICE;

		LayoutInflater _mi_li = (LayoutInflater) getContext().getSystemService(
				infService);
		_mi_li.inflate(R.layout.activity_view_compuesto, this, true);
		_votar = (Button) findViewById(R.id._votar);
		_estrellas = (RatingBar) findViewById(R.id._estrellas);
		_barra = (SeekBar) findViewById(R.id._barra);
		_list_peliculas = (Spinner) findViewById(R.id._list_peliculas);

		_barra.setMax(100);

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, _datos);

		_list_peliculas.setAdapter(adaptador);
		asignarEventos();
	}

	public void setOnVoteListener(OnVoteListener l) {
		listener = l;
	}

	private void asignarEventos() {
		_votar.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				listener.onVote(_estrellas.getRating(),
						(String) _list_peliculas.getSelectedItem());

			}
		});

		_barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				float rating = (float) arg0.getProgress() / (float) 20;
				_estrellas.setRating(rating);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			public void onStopTrackingTouch(SeekBar seekBar) {

			}

		});

		_estrellas
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						_barra.setProgress((int) (rating * 20));
					}

				});
	}

}