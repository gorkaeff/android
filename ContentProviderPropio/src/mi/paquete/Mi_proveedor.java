package mi.paquete;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

public class Mi_proveedor extends ContentProvider{
	
	private static final String _uri_st = "content://mi.paquete.mi_bd/Usuarios";
	public static final Uri CONTENT_URI = Uri.parse(_uri_st);
	
	private Mi_BD _BD;
	private static final String BD_NOMBRE = "Mi_BD";
	private static final int BD_VERSION = 1;
	private static final String TABLA_USUARIOS = "Usuarios";
	
	private static final int USUARIOS = 1;
	private static final int USUARIO = 2;
	private static final UriMatcher _mi_uriMatcher;
	
	static{
		_mi_uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		_mi_uriMatcher.addURI("mi.paquete.MiContentProvider.Mi_BD", "Usuarios", USUARIOS);
		_mi_uriMatcher.addURI("mi.paquete.MiContentProvider.Mi_BD", "clientes/#", USUARIO);
	}
	
	public static final class Usuarios implements BaseColumns{
		private Usuarios(){
			final String COL_NOMBRE = "nombre";
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		String _cad = selection;
		if (_mi_uriMatcher.match(uri) == USUARIOS){
			_cad = "_id=" + uri.getLastPathSegment();
		}
		SQLiteDatabase _desc_cons = _BD.getWritableDatabase();
		int _res = _desc_cons.delete(TABLA_USUARIOS, _cad, selectionArgs);
		return _res;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		int match = _mi_uriMatcher.match(uri);
		switch (match) {
		case USUARIOS:
			return "vnd.android.cursor.dir/vnd.MiContentProvider.cliente";
		case USUARIO:
			return "vnd.android.cursor.item/vnd.MiContentProvider.cliente";
		default:
			return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		long _id = 1;
		SQLiteDatabase _desc_ins = _BD.getWritableDatabase();
		_id = _desc_ins.insert(TABLA_USUARIOS, null, values);
		Uri _elem_uri = ContentUris.withAppendedId(CONTENT_URI, _id);
		return _elem_uri;
	}

	@Override
	public boolean onCreate() {
		_BD = new Mi_BD(getContext(), BD_NOMBRE, null, BD_VERSION);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		String _cad = selection;
		if (_mi_uriMatcher.match(uri) == USUARIOS){
			_cad = "_id=" + uri.getLastPathSegment();
		}
		SQLiteDatabase _desc_cons = _BD.getWritableDatabase();
		Cursor _res = _desc_cons.query(TABLA_USUARIOS, projection, _cad, selectionArgs, null, null, sortOrder);
		return _res;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		String _cad = selection;
		if (_mi_uriMatcher.match(uri) == USUARIOS){
			_cad = "_id=" + uri.getLastPathSegment();
		}
		SQLiteDatabase _desc_cons = _BD.getWritableDatabase();
		int _res = _desc_cons.update(TABLA_USUARIOS, values, _cad, selectionArgs);
		return _res;
	}
	
}