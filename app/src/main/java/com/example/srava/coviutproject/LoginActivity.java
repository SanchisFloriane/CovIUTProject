package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

    private MyShotAdaptater sauvegardeShotsDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sauvegardeShotsDB = new MyShotAdaptater(getBaseContext());
        sauvegardeShotsDB.open();
        Cursor curs = sauvegardeShotsDB.getAllData();

        Log.d("curs", "" + curs);
        if(!((curs != null) && (curs.getCount() > 0))){
            setContentView(R.layout.activity_login);
            Log.d("test","sdfsdfsdf");
            Button connect = (Button)findViewById(R.id.btn_connect);
            TextView inscrire = (TextView)findViewById(R.id.txt_inscire);
            inscrire.setOnClickListener(MyListener);
            connect.setOnClickListener(MyListener);
            sauvegardeShotsDB.close();
        }else{
            Log.d("test","tetststts");
            sauvegardeShotsDB.close();
            Intent FormChoix = new Intent(getApplicationContext(), com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(FormChoix);
        }


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

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Otherwise defer to system default behavior
    }

}
