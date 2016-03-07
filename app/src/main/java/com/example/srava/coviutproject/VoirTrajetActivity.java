package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class VoirTrajetActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_trajet);


        TextView date = (TextView)findViewById(R.id.edt_date);
        TextView a = (TextView)findViewById(R.id.edt_a);
        TextView de = (TextView)findViewById(R.id.edt_de);

        date.setOnClickListener(MyListener);
        a.setOnClickListener(MyListener);
        de.setOnClickListener(MyListener);


    }

    public View.OnClickListener MyListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.btn_rechercherTrajet :
                    Intent voirTrajet2 = new Intent(getApplicationContext(), VoirTrajet2.class);
                    startActivity(voirTrajet2);
                    break;

                case R.id.edt_a :

                    break;

                case R.id.edt_de :

                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voir_trajet, menu);
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
