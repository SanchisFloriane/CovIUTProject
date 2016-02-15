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

/**
 * Created by sanchisf on 28/01/2016.
 */
public class HttpRequestTaskManager extends AsyncTask<Credential, String, JSONObject>
{

    protected Context context;

    protected HttpRequestTaskManager(Context context) {
        this.context = context.getApplicationContext();
    }

    TextView connectionStatus;
    ProgressBar bar;
    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
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
            Log.e("JSONException", "Error23");
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


            // check if connection status is OK
            if(loginOK!=0)
            {
                Log.d("context", "" + context);

                Toast.makeText(context, "Connecté", Toast.LENGTH_LONG).show();
                Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
                FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(FormChoix);
            }
            else
            {
                Log.d("context", "" + context);
                Toast.makeText(context, "Échec de la connection,\nmauvais mot de passe ou nom d'utilisateur", Toast.LENGTH_LONG).show();
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
