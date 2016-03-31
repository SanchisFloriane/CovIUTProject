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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadPoolExecutor;


public class VoirTrajet2 extends Activity {

    private ListView activityList;
    private String depart = null;
    private String arrivee = null;
    private String date = null;
    private static String datefinal = null;
    private static List<String> month = new ArrayList<String>();
    private int numeroMonth;
    private SimpleAdapter itemsAdapter;

    private ArrayList<HashMap<String, String>> appItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_trajet2);

        activityList = (ListView)findViewById(R.id.ltv_trajet);

        appItemList = new ArrayList<HashMap<String, String>>();

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

        String tr = getIntent().getStringExtra("listeTrajet");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Trajet>>(){}.getType();
        List<Trajet> TrajetListe = gson.fromJson(tr, type);

        int nbTrajet = TrajetListe.size();

        TextView tw_nb_trajet = (TextView)findViewById(R.id.tw_nb_trajet);

        String tr2 = getIntent().getStringExtra("personnetrajet");
        Gson gson2 = new Gson();
        Type type2 = new TypeToken<List<Personne>>(){}.getType();
        List<Personne> PersonneListe = gson2.fromJson(tr2, type2);



        if (nbTrajet >1){
            tw_nb_trajet.setText(nbTrajet + " trajets");
        } else {
            tw_nb_trajet.setText(nbTrajet + " trajet");
        }
        int i=0;
        for (Trajet t : TrajetListe){

            TextView tw_depart = (TextView)findViewById(R.id.tw_nom_trajet);
            tw_depart.setText(t.getDEPART_TRAJET() + " >> " + t.getARRIVEE_TRAJET());


            TextView tw_date = (TextView)findViewById(R.id.tw_date);
            tw_date.setText(t.getDATE_TRAJET());



            for(Personne p : PersonneListe){

                if (PersonneListe.indexOf(p) == i && TrajetListe.indexOf(t) == i) {

                    int nbP = Integer.parseInt(t.getNBPLACE_TRAJET());
                    if (nbP >1){
                        appItemList.add(fillHashMap(t.getHEUREDEPART_TRAJET(), t.getPRIX_TRAJET() + " €/place", t.getDEPART_TRAJET() + " >> " + t.getARRIVEE_TRAJET(), t.getNBPLACE_TRAJET() + " restantes", p.getNOM_PERSONNE() + " " + p.getPRENOM_PERSONNE(), t.getTELELEPHONE_TRAJET()));

                    } else {
                        appItemList.add(fillHashMap(t.getHEUREDEPART_TRAJET(), t.getPRIX_TRAJET() + " €/place", t.getDEPART_TRAJET() + " >> " + t.getARRIVEE_TRAJET(), t.getNBPLACE_TRAJET() + " restante", p.getNOM_PERSONNE() + " " + p.getPRENOM_PERSONNE(), t.getTELELEPHONE_TRAJET()));

                    }
                    i++;
                }
            }

        }

        itemsAdapter = new SimpleAdapter(this.getBaseContext(), appItemList, R.layout.app_item,
                new String[]{"heuretrajet", "prix", "destination", "nbplace", "NomAge", "telephone"}, new int[]{R.id.heuretrajet, R.id.prix, R.id.destination, R.id.nbplace, R.id.NomAge, R.id.telephone});

        activityList.setAdapter(itemsAdapter);

        activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("p", parent.getItemAtPosition(position).toString());
                HashMap obj = (HashMap) parent.getItemAtPosition(position);

                String destination = (String) obj.get("destination");
                String heure = (String) obj.get("heuretrajet");
                String nbplace = (String) obj.get("nbplace");
                String prix = (String) obj.get("prix");
                String NomAge = (String) obj.get("NomAge");
                String telephone = (String) obj.get("telephone");

                TextView tw_date2 = (TextView) findViewById(R.id.tw_date);

                Intent voirtrajet3 = new Intent(getApplicationContext(), VoirTrajet3.class);
                voirtrajet3.putExtra("destination_trajet", destination);
                voirtrajet3.putExtra("nbplaces_trajet", nbplace);
                voirtrajet3.putExtra("prix_place_trajet", prix);
                voirtrajet3.putExtra("nomPrenom_trajet", NomAge);
                voirtrajet3.putExtra("heure_trajet", heure);
                voirtrajet3.putExtra("date_trajet", tw_date2.getText());
                voirtrajet3.putExtra("telephone_trajet", telephone);


                startActivity(voirtrajet3);

            }
        });
    }

    public static char intToChar(int i){
        String s = ""+i;
        return s.charAt(0);
    }


    private HashMap<String, String> fillHashMap(String Heure, String Prix, String Destination, String Nbplace, String NomAge, String telephone){

        HashMap<String, String> item = new HashMap<String, String>();
        item.put("heuretrajet", Heure);
        item.put("prix", Prix);
        item.put("destination", Destination);
        item.put("nbplace", Nbplace);
        item.put("NomAge", NomAge);
        item.put("telephone", telephone);
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