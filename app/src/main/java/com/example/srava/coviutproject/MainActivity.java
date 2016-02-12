package com.example.srava.coviutproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://coviut.esy.es/index.php";

    ArrayList<Personne> lp = new ArrayList<Personne>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button connect = (Button)findViewById(R.id.btn_connect);

        connect.setOnClickListener(MyListener);
    }

    public View.OnClickListener MyListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//ok
            switch (v.getId()){

                case R.id.btn_connect :

                    lp = getPersonnes();
                    Log.d("ok","" + lp.size());
                    break;

            }
        }
    };


    public static ArrayList<Personne> getPersonnes() {

        ArrayList<Personne> personnes = new ArrayList<Personne>();

        try {
            String myurl= "http://coviut.esy.es/index.php";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();
            Log.d("test2", connection.getInputStream().toString());
            InputStream inputStream = connection.getInputStream();
            Log.d("test2", "op");
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);

            // On récupère le JSON complet
            JSONObject jsonObject = new JSONObject(result);
            Log.d("k",jsonObject.toString());
            // On récupère le tableau d'objets qui nous concernent
            JSONArray array = new JSONArray(jsonObject.getString("personnes"));
            // Pour tous les objets on récupère les infos
            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien Personne - Objet JSON
                Personne personne = new Personne();
                personne.setNom(obj.getString("nom"));
                Log.d("ook", obj.getString("nom"));
                // On ajoute la personne à la liste
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
