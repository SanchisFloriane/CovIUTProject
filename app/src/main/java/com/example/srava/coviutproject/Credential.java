package com.example.srava.coviutproject;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sanchisf on 12/02/2016.
 */
public class Credential {

    public String HttpRequest;
    private Integer i = 0;
    private Integer j = 0;

    public Credential() {
    }

    public void HttpRequest(String type, ArrayList<String> vars){
        String PostRequest = "type="+type;
        for( i = 0; i< vars.size();i++){
            j = i+1;
            PostRequest += "&var"+j+"="+vars.get(i);
        }
        Log.d("Request ",""+PostRequest);
        this.HttpRequest = PostRequest;
    }

    public void HttpRequest(String request){
        String PostRequest = "type=custom";
            PostRequest += "&request=request";

        Log.d("Request ",""+PostRequest);
        this.HttpRequest = PostRequest;
    }

}
