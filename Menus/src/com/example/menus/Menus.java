package com.example.menus;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Menus extends Activity {

	private ListView _lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menus);
		
		_lista = (ListView) findViewById(R.id._lista);
		String[] _datos = new String[]{"Item A","Item B", "Item C"};
		ArrayAdapter<String> _adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,_datos);
		_lista.setAdapter(_adaptador);
		
		ImageButton _boton = (ImageButton) findViewById(R.id._toolsbotton);
		registerForContextMenu(_boton);
		registerForContextMenu(_lista);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_menus, menu);
		return true;
		
		//menu.add(Menu.NONE, R.id.menu_camara, Menu.NONE, "Opción 1").setIcon(R.drawable.camera);
		//menu.add(Menu.NONE, R.id.menu_galeria, Menu.NONE, "Opción 2").setIcon(R.drawable.gallery);
		//menu.add(Menu.NONE, R.id.menu_add, Menu.NONE, "Opción 3").setIcon(R.drawable.plus);
		
		//ejemplo de subMenu
		/*
		SubMenu sm1 = menu.addSubMenu(Menu.NONE, MENUITEM1, Menu.NONE, "1er item").setIcon(R.drawable.camera);
		sm1.add(Menu.NONE,SUBMENUITEM1, Menu.NONE, "Item 1.1");
		sm1.add(Menu.NONE,SUBMENUITEM2, Menu.NONE, "Item 1.2");
		*/
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		
		if(v.getId() == R.id._lista){		
			menu.setHeaderTitle(_lista.getAdapter().getItem(info.position).toString());
		}else if (v.getId() == R.id._toolsbotton){
			menu.setHeaderTitle("ImageButton Menu");
		}
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu_contextual, menu);
	}

}
