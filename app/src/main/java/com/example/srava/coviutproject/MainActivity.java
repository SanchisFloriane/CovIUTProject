package com.example.srava.coviutproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://coviut.esy.es/index.php";
    private ArrayList<Personne> ListePersonne = new ArrayList<Personne>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView)findViewById(R.id.tv_test);
        Button test = (Button)findViewById(R.id.button_test);
        test.setOnClickListener(MyListener);

    }

    public View.OnClickListener MyListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.button_test :
                    Log.d("Button test", "ok");
                    ListePersonne = getPersonnes();
               //     final TextView txt = (TextView)findViewById(R.id.tv_test);
                    for (final Personne p: ListePersonne) {
                        Log.d("test" , p.getNom());
                      //  txt.setText(p.getNom());
                    }
                    break;

            }
        }
    };

    public static ArrayList<Personne> getPersonnes() {

        ArrayList<Personne> personnes = new ArrayList<Personne>();

        try {


            URL url = new URL(LOGIN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complementaire:
             * Elle contient une methode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);

            // On recupere le JSON complet
            JSONObject jsonObject = new JSONObject(result);
            // On recupere le tableau d'objets qui nous concernent
            JSONArray array = new JSONArray(jsonObject.getString("personne"));
            // Pour tous les objets on recupere les infos
            for (int i = 0; i < array.length(); i++) {
                // On recupere un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien Personne - Objet JSON
                Personne personne = new Personne(obj.getString("nom"));
                // On ajoute la personne a la liste
                personnes.add(personne);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // On retourne la liste des personnes
        return personnes;
    }
















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
