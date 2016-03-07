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

import java.util.ArrayList;


public class InscrireActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);


        Button inscrire = (Button)findViewById(R.id.btn_inscrire);

        inscrire.setOnClickListener(MyListener);


    }

    public View.OnClickListener MyListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.btn_inscrire :

                    EditText nom = (EditText)findViewById(R.id.EdTxt_Nom);
                    EditText Prenom = (EditText)findViewById(R.id.EdTxt_Prenom);
                    EditText mail  = (EditText)findViewById(R.id.EdTxt_Mail);
                    EditText mdp = (EditText)findViewById(R.id.EdTxt_Mdp);
                    EditText mdp2= (EditText)findViewById(R.id.EdTxt_Mdp2);
                    EditText birth= (EditText)findViewById(R.id.EdTxt_Birth);


                    Credential credential = new Credential();
                    ArrayList<String> vars = new ArrayList<String>();
                    vars.add(nom.getText().toString());
                    vars.add(Prenom.getText().toString());
                    vars.add(mail.getText().toString());
                    vars.add(mdp.getText().toString());
                    vars.add(birth.getText().toString());

                    credential.HttpRequest("inscription", vars);
                    HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
                    result.execute(credential);

                    Log.d("HttpRequestTaskManager", String.valueOf(result));
                    Log.d("Inscription", "Inscription Button Pressed !");

                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inscrire, menu);
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
