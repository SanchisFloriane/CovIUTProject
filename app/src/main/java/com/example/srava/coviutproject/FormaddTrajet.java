package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;


public class FormaddTrajet extends Activity {

    TimePicker heuredep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formadd_trajet);

        Button proposer = (Button) findViewById(R.id.prop);
        proposer.setOnClickListener(listenerprop);
        heuredep = (TimePicker)findViewById(R.id.Hdep);
        heuredep.setIs24HourView(true);
    }

        public View.OnClickListener listenerprop = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //ok
                switch (v.getId()){

                    case R.id.prop :
                        // preparation de la connexion
                        Log.d("Connexion", "bouton proposer appuyé!");


                        DatePicker date = (DatePicker)findViewById(R.id.d);
                        EditText LieuArr= (EditText)findViewById(R.id.LieuArriv);
                        EditText telephoneprop = (EditText)findViewById(R.id.TelProp);
                        heuredep = (TimePicker)findViewById(R.id.Hdep);
                        Credential credprop = new Credential();
                        ArrayList<String> proposer = new ArrayList<String>();
                        Log.i("Date",date.getYear()+"-"+date.getMonth()+"-"+date.getDayOfMonth());
                        proposer.add(date.getYear()+"-"+date.getMonth()+"-"+date.getDayOfMonth());
                        Log.i("heure", heuredep.getCurrentHour().toString() + ":" + heuredep.getCurrentMinute().toString());
                        proposer.add(heuredep.getCurrentHour().toString() + ":" + heuredep.getCurrentMinute().toString()+":00");
                        proposer.add(LieuArr.getText().toString());
                        proposer.add(telephoneprop.getText().toString());
                        credprop.HttpRequest("proposer",proposer);

                        HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
                        result.execute(credprop);
                        Log.d("HttpRequestTaskManager", String.valueOf(result));
                        break;

                }
            }
        };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formadd_trajet, menu);
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
