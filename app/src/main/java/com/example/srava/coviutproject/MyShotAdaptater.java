package com.example.srava.coviutproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class MyShotAdaptater {

    final static String DATABASE_NAME = "SqlLiteTp4";
    Integer DATABASE_VERSION = 1;

    private SQLiteDatabase shotsDB; // reference vers une base de données
    private ShotsDBhelper dbHelper; // référence vers le Helper de gestion de la base

    public MyShotAdaptater(Context context) { // constructeur
        dbHelper = new ShotsDBhelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void close() {
        Log.i("MyShotsAdapter close", " demande de fermeture de la base");
        dbHelper.close();
    } // fermeture de la base

    public void open() throws SQLiteException { // ouverture de la base
        try {
            shotsDB = dbHelper.getWritableDatabase();
            Log.i("MyShotsAdapter open", "Base ouverte en ecriture " + shotsDB);
        } catch (SQLiteException e) {
            shotsDB = dbHelper.getReadableDatabase();
            Log.i("MyShotsAdapter open", "Base ouverte en lecture " + shotsDB);
        }
    }


    public long insertShot(Integer ID, String EMAIL,String NOM,String PRENOM,String TELEPHONE) { // ajout d'une occurence
        ContentValues newValue = new ContentValues();
        newValue.put(dbHelper.KEY_ID, ID);
        newValue.put(dbHelper.KEY_EMAIL, EMAIL);
        newValue.put(dbHelper.KEY_NOM, NOM);
        newValue.put(dbHelper.KEY_PRENOM, PRENOM);
        newValue.put(dbHelper.KEY_TELEPHONE, TELEPHONE);
        Log.i("MyShotsAdapter Insert", "value " + newValue);
        return shotsDB.insert(ShotsDBhelper.NOM_TABLE, null, newValue);
    }
/*
    public boolean updateShot(int ID, String EMAIL) { // mise à jour
        ContentValues newValue = new ContentValues();
        newValue.put(dbHelper.KEY_ID, ID);
        newValue.put(dbHelper.KEY_EMAIL, EMAIL);
        Log.i("MyShotsAdapter update", "value : " + newValue);
        return shotsDB.update(ShotsDBhelper.NOM_TABLE, newValue, ShotsDBhelper.KEY_ID + " = " + ID, null) > 0;
    }*/

    public boolean removeShot(long ligneID) { // suppression
        return shotsDB.delete(ShotsDBhelper.NOM_TABLE, ShotsDBhelper.KEY_ID + " = " + ligneID,
                null) > 0;
    }
    public boolean removeAllShot() { // suppression
        return shotsDB.delete(ShotsDBhelper.NOM_TABLE, null,
                null) > 0;
    }

    public Cursor getAllData() { // select *
        return shotsDB.query(dbHelper.NOM_TABLE, new String[]{
                ShotsDBhelper.KEY_ID, ShotsDBhelper.KEY_EMAIL,ShotsDBhelper.KEY_NOM,ShotsDBhelper.KEY_PRENOM,ShotsDBhelper.KEY_TELEPHONE}, null, null, null, null, null);
    }


    public Cursor getSingleShot(long ID){ // select *
        return shotsDB.query(dbHelper.NOM_TABLE, new String[]{
                ShotsDBhelper.KEY_ID, ShotsDBhelper.KEY_EMAIL,ShotsDBhelper.KEY_NOM,ShotsDBhelper.KEY_PRENOM,ShotsDBhelper.KEY_TELEPHONE}, null, null, null, ShotsDBhelper.KEY_ID + " = " +ID, null);
    }
}
