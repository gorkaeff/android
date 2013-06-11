package com.example.localizacion;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LocalizacionActivity extends Activity {

    private LocationManager locationManager;
    private LocationListener locationListener;

    private TextView latitud;
    private TextView longitud;
    private TextView precision;
    private TextView estado;

    private Button btnComenzar;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_localizacion);

	latitud = (TextView) findViewById(R.id._latitud);
	longitud = (TextView) findViewById(R.id._longitud);
	precision = (TextView) findViewById(R.id._precision);
	estado = (TextView) findViewById(R.id._estado);

	btnComenzar = (Button) findViewById(R.id._comenzar);
	btnFinalizar = (Button) findViewById(R.id._finalizar);

	btnFinalizar.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		locationManager.removeUpdates(locationListener);
	    }
	});

	btnComenzar.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		init();
	    }
	});

    }

    private void init() {
	// referencia a location manager
	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	// ultima posicion conocida
	// LocationManager.GPS_PROVIDER
	Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

	mostrarLocalizacion(location);

	locationListener = new LocationListener() {

	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		estado.setText("Provider Status: " + status);
	    }

	    @Override
	    public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		estado.setText("Provider ON");

	    }

	    @Override
	    public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		estado.setText("Provider OFF");
	    }

	    @Override
	    public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		mostrarLocalizacion(location);
	    }
	};

	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 0, locationListener);

    }

    private void mostrarLocalizacion(Location location) {
	if (location != null) {
	    latitud.setText("Latitud: " + String.valueOf(location.getLatitude()));
	    longitud.setText("Longitud: " + String.valueOf(location.getLongitude()));
	    precision.setText("Precisión: " + String.valueOf(location.getAccuracy()));
	} else {
	    latitud.setText("Latitud: (sin datos)");
	    longitud.setText("Longitud: (sin datos)");
	    precision.setText("Precisión: (sin datos)");
	}
    }

    /*
     * 
     * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this adds items to the action bar if it is present.
     * getMenuInflater().inflate(R.menu.localizacion, menu); return true; }
     */

}
