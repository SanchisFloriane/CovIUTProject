package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginActivity extends Activity {

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://coviut.esy.es/index.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button connect = (Button)findViewById(R.id.btn_connect);
        TextView inscrire = (TextView)findViewById(R.id.txt_inscire);








        inscrire.setOnClickListener(MyListener);
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

                    Credential credential = new Credential();
                    ArrayList<String> vars = new ArrayList<String>();
                    vars.add(username.getText().toString());
                    vars.add(password.getText().toString());
                    credential.HttpRequest("login",vars);

                    HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
                    result.execute(credential);
                    Log.d("HttpRequestTaskManager", String.valueOf(result));

                    break;

                case R.id.txt_inscire :
                    Intent inscrire = new Intent(getApplicationContext(),InscrireActivity.class);
                    startActivity(inscrire);
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
