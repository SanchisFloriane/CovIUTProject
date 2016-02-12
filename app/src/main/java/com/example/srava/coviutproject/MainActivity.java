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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
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
                    ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);



                    // permet de reinitialiser le bouton et la TextView
                    connectionStatus.setText("Non Connecté");
                    progressBar.setProgress(0);

                    Log.d("Connexion", "Connect Button Pressed !");

                    Credential credential = new Credential();
                    credential.userName=username.getText().toString();
                    credential.password=password.getText().toString();


                    HttpRequestTaskManager result = new HttpRequestTaskManager();
                    result.setProgressBar(progressBar);
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
}
