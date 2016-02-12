package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://coviut.esy.es/index.php";




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
// preparation de la connexion
                    Log.d("Connexion", "Connect Button Pressed !");


                    EditText username = (EditText)findViewById(R.id.username);
                    EditText password = (EditText)findViewById(R.id.password);
                    TextView connectionStatus = (TextView)findViewById(R.id.connectionStatus);



                    // permet de reinitialiser le bouton et la TextView
                    connectionStatus.setText("Non Connecté");


                    Log.d("Connexion", "Connect Button Pressed !");

                    Credential credential = new Credential();
                    credential.userName=username.getText().toString();
                    credential.password=password.getText().toString();


                    HttpRequestTaskManager result = new HttpRequestTaskManager();

                    result.setConnectionStatus(connectionStatus);
                    result.execute(credential);
                    Log.d("HttpRequestTaskManager", String.valueOf(result));

                    break;

            }
        }
    };




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

    public class HttpRequestTaskManager extends AsyncTask<Credential, String, JSONObject>
    {
        TextView connectionStatus;
        ProgressBar bar;
        private static final String FLAG_SUCCESS = "success";
        private static final String FLAG_MESSAGE = "message";
        private static final String LOGIN_URL = "http://coviut.esy.es/index.php";

        // def du setter pour faire le lien avec le TextView depuis la MainActivity
        public void setConnectionStatus(TextView textView) {
            this.connectionStatus = textView;
        }

        // pareil mais pour la progressBar
        public void setProgressBar(ProgressBar bar) {
            this.bar = bar;
        }

        @Override
        // on va maintenant parler du JSON  !
        protected JSONObject doInBackground(Credential... params) {
            JSONObject jsonResponse= new JSONObject();

            try{

                URL url = new URL(LOGIN_URL);
                HttpURLConnection connection = (HttpURLConnection )url.openConnection();

                //recupere la premiere ligne du tableau
                Credential credential = params[0];

                // regle la connection et tous les parametres requis
                connection.setRequestMethod("POST");
                String urlParameters  = "username="+credential.userName+"&password="+credential.password;
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                connection.setRequestProperty("Content-Length", "" + postData.length);

                try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                    wr.write( postData );
                }



                // envoie des donnees
                Log.d("doInBackground", "ready to send request...");
                connection.connect();



                // decode response
                InputStream in = new BufferedInputStream(connection.getInputStream());
                jsonResponse = new JSONObject(convertStreamToString(in));

            }  catch (IOException e) {
                Log.e("IOException", "Error1");
            }  catch(JSONException e){
                Log.e("JSONException", "Error2");
            }  catch (NetworkOnMainThreadException e){
                Log.e("ThreadException", "android > 3.0!!");
            }
            Log.i("doInBackground", jsonResponse.toString());


            return jsonResponse;

        }

        @Override
        protected void onPostExecute( JSONObject result){

            //oblige de mettre un TryAndCatch pour une conversion de jSON
            try{
                Log.d("result",result.getString(FLAG_SUCCESS));
                int loginOK = result.getInt(FLAG_SUCCESS);
                connectionStatus.setText(result.getString(FLAG_MESSAGE));

                // check if connection status is OK
                if(loginOK!=0)
                {
                    connectionStatus.setText("Connecté !");
                    Intent addTrajet = new Intent(getApplicationContext(), FormaddTrajet.class);
                    startActivity(addTrajet);
                }
                else
                {
                    connectionStatus.setText("Mauvais mot de passe ou login.");
                }

            }  catch(JSONException e){
                Log.e("JSONException", "Error3");
            }  catch (NetworkOnMainThreadException e){
                Log.e("ThreadException", "android > 3.0!!");
            }
        }



        //methode pour convertir la reponse du serveur
        public String convertStreamToString(java.io.InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
    }

}
