package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class VoirTrajet2 extends Activity {

    private ListView activityList;
    private String depart = null;
    private String arrivee = null;
    private String date = null;
    private static String datefinal = null;
    private static List<String> month = new ArrayList<String>();
    private int numeroMonth;
    private SimpleAdapter itemsAdapter;

    private ArrayList<HashMap<String, String>> appItemList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_trajet2);

        month.add("Janvier");
        month.add("Février");
        month.add("Mars");
        month.add("Avril");
        month.add("Mai");
        month.add("Juin");
        month.add("Juillet");
        month.add("Août");
        month.add("Septembre");
        month.add("Octobre");
        month.add("Novembre");
        month.add("Décembre");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            depart = extras.getString("depart");
            arrivee = extras.getString("arrivee");
            date = extras.getString("date");
            numeroMonth = extras.getInt("numeroMonth");
        }


        TextView tw_depart = (TextView)findViewById(R.id.tw_nom_trajet);
        tw_depart.setText(depart + " >> " + arrivee);

        TextView tw_date = (TextView)findViewById(R.id.tw_date);
        tw_date.setText(date);

        activityList = (ListView) findViewById(R.id.ltv_trajet);

        itemsAdapter = new SimpleAdapter(this.getBaseContext(), appItemList, R.layout.app_item,
                new String[]{"heuretrajet", "prix", "destination", "nbplace", "NomAge"}, new int[]{R.id.heuretrajet, R.id.place, R.id.destination, R.id.nbplace, R.id.NomAge});
        Log.d("ok", "oka");
     /*   activityList.setAdapter(itemsAdapter);
        Log.d("ok", "oka");*/

        Credential credential = new Credential();
        ArrayList<String> vars = new ArrayList<String>();
        vars.add(depart);
        vars.add(arrivee);

        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat output = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date oneWayTripDate = input.parse(date);                 // parse input
            String formats = output.format(oneWayTripDate);        // format output

            vars.add(formats);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        credential.HttpRequest("searchtrajet", vars);

        HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
        result.execute(credential);


        activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ////méthode qui permet de récupérer l'id de l'item cliqué et qui permet de lancer différentes activités selon l'id retourné par le clique.
                if (id == 0) {
                    Intent voirtrajet3 = new Intent(getApplicationContext(), VoirTrajet3.class);
                    startActivity(voirtrajet3);

                } else if (id == 1) {

                } else if (id == 2) {


                } else if (id == 3) {


                }
            }
        });
    }

    public static char intToChar(int i){
        String s = ""+i;
        return s.charAt(0);
    }
/*
    public void retournerTrajet(Trajet t){


        //création des items et ajout à la liste
        appItemList.add(fillHashMap(t.getHEUREDEPART_TRAJET(), t.getPRIX_TRAJET() + " €/place", "... >> " + depart + " >> " + arrivee, t.getNBPLACE_TRAJET() + " restante(s)", "nom prenom age"));

    }*/

    private HashMap<String, String> fillHashMap(String Heure, String Prix, String Destination, String Nbplace, String NomAge){
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("heuretrajet", Heure);
        item.put("prix", Prix);
        item.put("destination", Destination);
        item.put("nbplace", Nbplace);
        item.put("NomAge", NomAge);
        return item;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voir_trajet2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}