package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class VoirTrajet2 extends Activity {

    private ListView activityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_trajet2);

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