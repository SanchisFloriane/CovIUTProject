package com.example.srava.coviutproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by merciant on 15/02/2016.
 */
public class DatabaseRequest
{

    protected Context context;

    public DatabaseRequest(Context context){
        this.context = context.getApplicationContext();

    }


   public String insertDataBase(String table, String[] values){
       HttpRequestTaskManager result = new HttpRequestTaskManager(this.context);

       //result.execute(credential);

       String response = null;
       return response;
   }

    public String deleteDataBase(String table, String[] selectValues){



        String response = null;
        return response;
    }
    public String updateDataBase( String table, String[] selectValues, String[] values){



        String response = null;
        return response;
    }
    public String selectDataBase( String table, String[] selectValues){



        String response = null;
        return response;
    }














}
