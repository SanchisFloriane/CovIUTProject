package com.example.srava.coviutproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import org.json.JSONObject;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.example.srava.coviutproject.OnPostExecuteFunction.OnPostExecuteInscription;
import static com.example.srava.coviutproject.OnPostExecuteFunction.OnPostExecuteLogin;
import static com.example.srava.coviutproject.OnPostExecuteFunction.OnPostExecuteProposer;
import static com.example.srava.coviutproject.OnPostExecuteFunction.OnPostExecuteTrajet;


public class HttpRequestTaskManager extends AsyncTask<Credential, String, JSONObject>
{

    protected Context context;

    protected HttpRequestTaskManager(Context context) {
        this.context = context.getApplicationContext();
    }

    private String action = "noAction";
    private static final String FLAG_SUCCESS = "etat";
    private static final String FLAG_IDENTIFIANT = "identifiant";
    private static final String FLAG_MESSAGE = "message";
    private static final String FLAG_DATA = "data";
    private static final String LOGIN_URL = "http://coviut.esy.es/index.php";


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

            String urlParameters  = credential.HttpRequest;

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
            Log.e("JSONException", "Error23"+e.getMessage());
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
            Log.d("result success",result.getString(FLAG_SUCCESS));
            Log.d("result identifiant",result.getString(FLAG_IDENTIFIANT));
            Log.d("result message",result.getString(FLAG_MESSAGE));
            Log.d("result data",result.getString(FLAG_DATA));

            int etat = result.getInt(FLAG_SUCCESS);
            String identifiant = result.getString(FLAG_IDENTIFIANT);
            String message = result.getString(FLAG_MESSAGE);
            String data = result.getString(FLAG_DATA);

            // check if connection status is OK
            if(identifiant.equals("login")){
                Log.d("if id", "login");
                OnPostExecuteLogin(etat, message, data, context);
            }else if(identifiant.equals("inscription")){
                Log.d("if id", "inscription");
                OnPostExecuteInscription(etat, message, data, context);
            }else if(identifiant.equals("proposer")){
                Log.d("if id", "poposer");
                OnPostExecuteProposer(etat, message, data, context);

            } else if(identifiant.equals("searchtrajet")){
                Log.d("if id", "searchtrajet");
                OnPostExecuteTrajet(etat, message, data, context);

        }


        }  catch(JSONException e){
            Log.e("JSONException", "Error3"+e.getMessage());
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
