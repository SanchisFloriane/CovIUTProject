package com.example.srava.coviutproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class VoirTrajetActivity extends Activity {

    private int numeroM = 0;
    private TextView departT;
    private TextView arriveeT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_trajet);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        numeroM = month+1;
        Log.d("y", year + " ");
        Log.d("m", month+1 + "");
        Log.d("d", day + "");

        departT = (TextView)findViewById(R.id.edt_de);
        arriveeT = (TextView)findViewById(R.id.edt_a);

        departT.setOnFocusChangeListener(FocusListener);
        arriveeT.setOnFocusChangeListener(FocusListener);

        TextView date = (TextView)findViewById(R.id.edt_date);

        if (month + 1 < 10) {
            date.setText(day + "/0" + (month + 1) + "/" + year);
        } else {
            date.setText(day + "/" + (month + 1) + "/" + year);
        }

        DatePicker dateP = (DatePicker)findViewById(R.id.calendrier);

        dateP.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                TextView date = (TextView) findViewById(R.id.edt_date);
                if (month + 1 < 10) {

                    date.setText(dayOfMonth + "/0" + (month + 1) + "/" + year);
                } else {
                    date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }


            }
        });
        date.setOnFocusChangeListener(FocusListener);
        date.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                DatePicker dateP = (DatePicker)findViewById(R.id.calendrier);

                String day = s.charAt(0) + "" + s.charAt(1);
                String month;
                if(s.charAt(3) == 0){
                    month = s.charAt(4) + "";
                }
                else {

                    month = s.charAt(3) + "" + s.charAt(4);
                }
                String year = s.charAt(6) + "" + s.charAt(7) + "" + s.charAt(8) + "" + s.charAt(9);

                int dayI = Integer.parseInt(day);
                int monthI = Integer.parseInt(month);
                int yearI = Integer.parseInt(year);

                // dateP.updateDate(yearI, monthI, dayI);

            }


        });

        Button rechercher = (Button) findViewById(R.id.btn_rechercherTrajet);
        rechercher.setOnClickListener(MyListener);




    }


    public View.OnFocusChangeListener FocusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            TextView dateFocus = (TextView) findViewById(R.id.edt_date);

            LinearLayout linearLayoudDate = (LinearLayout)findViewById(R.id.llDate);
            LinearLayout.LayoutParams paramsDate = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);


            if (hasFocus && v.getId() == Integer.parseInt("2131296299")) {

                paramsDate.height = 450;
                linearLayoudDate.setVisibility(View.VISIBLE);
                linearLayoudDate.setLayoutParams(paramsDate);

            } else if (hasFocus && v.getId() == Integer.parseInt("2131296290")) {

                if (departT.getText().toString() != "" && departT.getText().toString() != "IUT Annecy le Vieux"){
                    arriveeT.setText("IUT Annecy le Vieux");
                    departT.setText("");
                } else {
                    departT.setText("IUT Annecy le Vieux");
                }

            } else if (hasFocus && v.getId() == Integer.parseInt("2131296294")) {

                if (arriveeT.getText().toString() != "" && arriveeT.getText().toString() != "IUT Annecy le Vieux"){
                    departT.setText("IUT Annecy le Vieux");
                    arriveeT.setText("");
                } else {
                    arriveeT.setText("IUT Annecy le Vieux");

                }


            } else {

                paramsDate.height = 0;
                linearLayoudDate.setVisibility(View.INVISIBLE);
                linearLayoudDate.setLayoutParams(paramsDate);
                v.clearFocus();
                Log.d("d", "oki");


            }







        }
    };


    public View.OnClickListener MyListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            DatePicker datePi = (DatePicker)findViewById(R.id.calendrier);

            TextView date2 = (TextView)findViewById(R.id.edt_date);
            TextView a = (TextView)findViewById(R.id.edt_a);
            TextView de = (TextView)findViewById(R.id.edt_de);

            switch (v.getId()){

                case R.id.btn_rechercherTrajet :

                    if(de.getText().toString().isEmpty())
                    {

                        Context c = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        String texte = "Veuillez saisir une ville de départ.";
                        Toast t = Toast.makeText(c, texte, duration);
                        t.show();

                    }
                    else if(a.getText().toString().isEmpty())
                    {

                        Context c = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        String texte = "Veuillez saisir une ville d'arrivée.";
                        Toast t = Toast.makeText(c, texte, duration);
                        t.show();

                    }
                    else if(date2.getText().toString().isEmpty())
                    {

                        Context c = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        String texte = "Veuillez saisir une date de départ.";
                        Toast t = Toast.makeText(c, texte, duration);
                        t.show();

                    }
                    else if(!isThisDateValid(date2.getText().toString(), "dd/MM/yyyy"))
                    {

                        Context c = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        String texte = "Veuillez saisir une date de départ valide 'dd/mm/yyyy'.";
                        Toast t = Toast.makeText(c, texte, duration);
                        t.show();

                    }
                    else
                    {
                        Credential credential = new Credential();
                        ArrayList<String> vars = new ArrayList<String>();
                        vars.add(de.getText().toString());
                        vars.add(a.getText().toString());

                        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat output = new SimpleDateFormat("yyyy/MM/dd");
                        try {
                            Date oneWayTripDate = input.parse(date2.getText().toString());                 // parse input
                            String formats = output.format(oneWayTripDate);        // format output

                            vars.add(formats);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        credential.HttpRequest("searchtrajet", vars);

                        HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
                        result.execute(credential);



                    }

                    break;


            }
        }
    };



    public boolean isThisDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

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
