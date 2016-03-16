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

import java.util.ArrayList;
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

        Credential credential = new Credential();
        ArrayList<String> vars = new ArrayList<String>();
        vars.add(depart);
        vars.add(arrivee);
        vars.add(date);
        credential.HttpRequest("searchTrajet",vars);

        HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
        result.execute(credential);
        Log.d("HttpRequestTaskManager", String.valueOf(result));

        TextView tw_depart = (TextView)findViewById(R.id.tw_nom_trajet);
        tw_depart.setText(depart + " >> " + arrivee);

        TextView tw_date = (TextView)findViewById(R.id.tw_date);

       for(String m : month){

           int value = month.indexOf(m);

           if(numeroMonth == value){

               String day = date.charAt(0) + "" + date.charAt(1);

               String year = date.charAt(5) + "" + date.charAt(6) + "" + date.charAt(7) + "" + date.charAt(8);

               date = day + " " + m + " " + year;


           }
       }

       tw_date.setText(date);

        activityList = (ListView) findViewById(R.id.ltv_trajet);

        ArrayList<HashMap<String, String>> appItemList = new ArrayList<HashMap<String, String>>();

        //création des items et ajout à la liste
        appItemList.add(fillHashMap("11h50", "8 € / place", "... >> Paris >> Lyon", "2 places restantes", "Antoine M. 19 ans"));
        appItemList.add(fillHashMap("10h50", "10 € / place", "... >> Paris >> Lyon", "5 places restantes", "Yohan.Granert 19 ans"));
        // l'adapter ajoute les items de la liste dans la view app_item.xml
        SimpleAdapter itemsAdapter = new SimpleAdapter(this.getBaseContext(), appItemList, R.layout.app_item,
                new String[]{"heuretrajet", "place", "destination", "place", "NomAge"}, new int[]{R.id.heuretrajet, R.id.place,
                R.id.destination, R.id.nbplace, R.id.NomAge});

        activityList.setAdapter(itemsAdapter);

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


    private HashMap<String, String> fillHashMap(String Heure, String Place, String Destination, String Nbplace, String NomAge){
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("heuretrajet", Heure);
        item.put("place", Place);
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