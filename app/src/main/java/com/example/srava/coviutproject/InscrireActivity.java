package com.example.srava.coviutproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class InscrireActivity extends Activity {

    public static TextView SelectedDateView;
    private static List<String> month = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);

        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(locale);
        Button inscrire = (Button)findViewById(R.id.btn_inscrire);
        SelectedDateView = (TextView) findViewById(R.id.EdTxt_Birth);
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
                    RadioGroup rad = (RadioGroup)findViewById(R.id.RadGroup);
                    Integer id = rad.getCheckedRadioButtonId();

                    if(!(mdp.getText().toString().equals(mdp2.getText().toString()))) {
                        Toast.makeText(getApplicationContext(), "Les mots de passe sont différents", Toast.LENGTH_LONG).show();
                    }else if(nom.getText().toString().isEmpty() || Prenom.getText().toString().isEmpty()|| mail.getText().toString().isEmpty()|| mdp.getText().toString().isEmpty()|| mdp2.getText().toString().isEmpty()|| birth.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_LONG).show();
                    }else {

                        Credential credential = new Credential();
                        ArrayList<String> vars = new ArrayList<String>();
                        vars.add(nom.getText().toString());
                        vars.add(Prenom.getText().toString());
                        vars.add(mail.getText().toString());
                        vars.add(mdp.getText().toString());

                        vars.add(birth.getText().toString());
                        Log.d("id", "" + id);
                        if (id.equals("r1")) {
                            vars.add("h");
                        } else {
                            vars.add("f");
                        }

                        credential.HttpRequest("inscription", vars);
                        HttpRequestTaskManager result = new HttpRequestTaskManager(getApplicationContext());
                        result.execute(credential);

                        Log.d("HttpRequestTaskManager", String.valueOf(result));
                        Log.d("Inscription", "Inscription Button Pressed !");
                    }
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
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        Log.d("date","datepls");
    }




    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Otherwise defer to system default behavior
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dp = new DatePickerDialog(getActivity(), this, year, month, day);
            dp.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", dp);
            dp.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Annuler", dp);
            // Create a new instance of DatePickerDialog and return it
            return dp;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            SelectedDateView.setText(month  + "/" + day + "/" + year);
        }
    }
}
