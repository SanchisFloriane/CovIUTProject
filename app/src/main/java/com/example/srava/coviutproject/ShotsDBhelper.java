package com.example.srava.coviutproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ShotsDBhelper extends SQLiteOpenHelper {

    // membres public permettant de dfinir les champs de la base
    public static final String NOM_TABLE = "Personne";
    public static final String KEY_ID = "PERSONNE_ID";
    public static final String KEY_EMAIL = "PERSONNE_EMAIL";
    public static final String KEY_NOM = "PERSONNE_NOM";
    public static final String KEY_PRENOM = "PERSONNE_PRENOM";
    public static final String KEY_TELEPHONE = "PERSONNE_TELEPHONE";


    // String permettant la creation de la table
    private static final String DATABASE_CREATE = "create table " + NOM_TABLE +
            " (" + KEY_ID + " integer primary key, " +
            KEY_EMAIL + " text , " +
            KEY_NOM + " text , "+
            KEY_PRENOM + " text , "+
            KEY_TELEPHONE + " text );";

    public ShotsDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
        Log.i("ShotsDBhelper", "Helper construit");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    // creation de la table
        db.execSQL(DATABASE_CREATE);
        Log.i("ShotsDBhelper", "Database created with instruction : " +
                DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// destruction de la base et recreation
        db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE);
        Log.i("ShotsDBhelper", "Base mise a jour !!!");
        onCreate(db);
    }
}