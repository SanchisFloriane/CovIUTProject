package com.example.srava.coviutproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class VoirTrajet3 extends Activity {

    private String destination_trajet = null;
    private String nbplaces_trajet = null;
    private String prix_place_trajet = null;
    private String nomPrenom_trajet = null;
    private String heure_trajet = null;
    private String date_trajet = null;
    private String telephone_trajet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_trajet3);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            destination_trajet = extras.getString("destination_trajet");
            nbplaces_trajet = extras.getString("nbplaces_trajet");
            prix_place_trajet = extras.getString("prix_place_trajet");
            nomPrenom_trajet = extras.getString("nomPrenom_trajet");
            heure_trajet = extras.getString("heure_trajet");
            date_trajet = extras.getString("date_trajet");
            telephone_trajet = extras .getString("telephone_trajet");

        }


        TextView dest = (TextView)findViewById(R.id.tw_nom_trajet);
        dest.setText(destination_trajet);

        TextView nbPlace = (TextView)findViewById(R.id.tw_nbplaces);
        nbPlace.setText(nbplaces_trajet);

        TextView prix = (TextView)findViewById(R.id.tw_prix_place);
        prix.setText(prix_place_trajet);

        TextView dateHeure = (TextView)findViewById(R.id.tw_date_heure);
        dateHeure.setText(date_trajet + " - " + heure_trajet);

        TextView nomPre = (TextView)findViewById(R.id.nomPrenom);
        nomPre.setText("Commentaire de " + nomPrenom_trajet);

        TextView tel = (TextView)findViewById(R.id.telephone);
        tel.setText("Téléphone : " + telephone_trajet);

        int position = destination_trajet.indexOf(">>");
        String villeDep = destination_trajet.substring(0, position - 1);
        String villeArr = destination_trajet.substring(position+2);

        TextView dep = (TextView)findViewById(R.id.dep);
        dep.setText(villeDep);

        TextView arr = (TextView)findViewById(R.id.arr);
        arr.setText(villeArr);

        TextView heureDep = (TextView)findViewById(R.id.heureDep);
        heureDep.setText(heure_trajet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voir_trajet3, menu);
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
