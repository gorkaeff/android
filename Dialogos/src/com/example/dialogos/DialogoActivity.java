package com.example.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogoActivity extends Activity {

	private final int NOTIF_ALERT_ID = 1;
	final Context context = this;
	private Button btnAlerta;
	private Button btnConfirmacion;
	private Button btnSeleccion;
	private Button btnNotificacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialogo);
		btnAlerta = (Button) findViewById(R.id.buttonAlerta);
		btnConfirmacion = (Button) findViewById(R.id.buttonConfirmacion);
		btnSeleccion = (Button) findViewById(R.id.buttonSeleccion);
		btnNotificacion = (Button) findViewById(R.id.buttonNotificacion);
		
		btnAlerta.setOnClickListener(new OnClickListener() {		
			public void onClick(View v) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Diálogo de Ejemplo");
				alertDialogBuilder.setMessage("La ventana creada es para mostrar un dialogo de alerta de ejemplo.");
				alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {					
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		});
		
		btnConfirmacion.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Pregunta de Confirmación");
				builder.setMessage("¿Está seguro que quiere salir?");
				
				builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {		
					public void onClick(DialogInterface dialog, int which) {
						//salir de app
						finish();
					}
				});
				
				builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {		
					public void onClick(DialogInterface dialog, int which) {
						//salir de app
						dialog.cancel();
					}
				});
				
				AlertDialog alertConfirmacion = builder.create();
				alertConfirmacion.show();
				
			}
		});
		
		btnSeleccion.setOnClickListener(new OnClickListener() {		
			public void onClick(View v) {
				final String[] items = {"Español","Inglés","Francés"};
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				
				builder.setTitle("Seleccione un idioma");
				/*
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.i("Dialogos", "Opción elegida: " + items[which]);	
					}
				});
				*/
				/*
				builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {	
					public void onClick(DialogInterface dialog, int which) {
						Log.i("Dialogos","Opción elegida: " + items[which]);
					}
				});*/
				builder.setMultiChoiceItems(items,null, new DialogInterface.OnMultiChoiceClickListener() {				
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						Log.i("Dialogos","Opción elegida: " + items[which]);
					}
				});
				AlertDialog alertSeleccion = builder.create();
				alertSeleccion.show();
			}
		});
		
		btnNotificacion.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				//Referencia al servicio de notificaciones
				String ns = Context.NOTIFICATION_SERVICE;
				NotificationManager notManager = (NotificationManager) getSystemService(ns);
				//Configurar la notificacion
				int icono = android.R.drawable.stat_sys_warning;
				CharSequence textoNotificacion = "Alerta!";
				long hora = System.currentTimeMillis();
				
				Notification notificationAlert = new Notification(icono, textoNotificacion, hora);
				
				//Configurar intent
				Context contexto = getApplicationContext();
				CharSequence titulo = "Mensaje de Alerta";
				CharSequence descripcion = "Ejemplo de notificación";
				
				Intent notIntent = new Intent(contexto, DialogoActivity.class);
				PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
				
				notificationAlert.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
				//alertas - desaparecer
				notificationAlert.flags |= Notification.FLAG_AUTO_CANCEL;
				//añadir sonido, vibracion, luces
				notificationAlert.defaults |= Notification.DEFAULT_SOUND;
				notificationAlert.defaults |= Notification.DEFAULT_VIBRATE;
				notificationAlert.defaults |= Notification.DEFAULT_LIGHTS;
				
				notManager.notify(NOTIF_ALERT_ID,notificationAlert);
				
			}
		});
		
	}
}
