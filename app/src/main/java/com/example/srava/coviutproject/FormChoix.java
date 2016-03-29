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
import android.widget.TextView;


public class FormChoix extends Activity {

    private MyShotAdaptater sauvegardeShotsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_choix);


        Button addTrajet = (Button)findViewById(R.id.btn_addTrajet);
        addTrajet.setOnClickListener(MyListener);

        Button voirTrajet = (Button)findViewById(R.id.btn_voirTrajet);
        voirTrajet.setOnClickListener(MyListener);
    }

    public View.OnClickListener MyListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.btn_addTrajet :
                    Intent addTrajet = new Intent(getApplicationContext(), FormaddTrajet.class);
                    startActivity(addTrajet);
                    break;

                case R.id.btn_voirTrajet :
                    Intent voirTrajet = new Intent(getApplicationContext(), VoirTrajetActivity.class);
                    startActivity(voirTrajet);
                    break;

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_choix, menu);
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
        sauvegardeShotsDB = new MyShotAdaptater(getBaseContext());
        sauvegardeShotsDB.open();
        Cursor curs = sauvegardeShotsDB.getAllData();

        Log.d("curs", "" + curs);
        if((curs != null) && (curs.getCount() > 0)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            super.onBackPressed();
        }
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
    public void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_form_choix);

    }

}
