package com.example.srava.coviutproject;

import android.content.Context;
import android.content.Intent;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import java.io.StringReader;


public final class OnPostExecuteFunction {

    private OnPostExecuteFunction(){};
    private static List<Personne> lp = new ArrayList<Personne>();
    private static List<Trajet> lt = new ArrayList<Trajet>();
    private static String jsonCars;
    private static String jsonCars2;
    private static int j = 0;

    public static void OnPostExecuteLogin(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            Log.d("data", "" + Data);
            Log.d("context", "" + context);
            try {
                JSONObject jsonResponse = new JSONObject(Data);
                Log.d("json?",""+jsonResponse.toString());
                MyShotAdaptater sauvegardeShotsDB;
                sauvegardeShotsDB = new MyShotAdaptater(context);
                sauvegardeShotsDB.open();
                sauvegardeShotsDB.insertShot(Integer.parseInt(jsonResponse.getString("ID_PERSONNE")),
                                             jsonResponse.getString("MAIL_PERSONNE"),
                                             jsonResponse.getString("NOM_PERSONNE"),
                                             jsonResponse.getString("PRENOM_PERSONNE"),
                                             jsonResponse.getString("TEL_PERSONNE"));
                sauvegardeShotsDB.close();
            }catch (org.json.JSONException e){
                Log.d("exception", "something went fucking wrong with the string to json" + e.getMessage());
            }
            Toast.makeText(context, "Connecté", Toast.LENGTH_LONG).show();

            Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(FormChoix);
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Échec de la connection,\nmauvais mot de passe ou nom d'utilisateur", Toast.LENGTH_LONG).show();
        }
    }

    public static void OnPostExecuteProposer(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            Log.d("context", "" + context);

            Toast.makeText(context, "Trajet propos�", Toast.LENGTH_LONG).show();
            Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(FormChoix);
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "�chec de l'ajout,\nun probl�me s'est produit", Toast.LENGTH_LONG).show();
        }
    }


    public static void OnPostExecuteTrajet(Integer etat, String message, String Data,Context context){

        if(etat == 1)
        {

            Toast.makeText(context, "Trajet trouvés", Toast.LENGTH_LONG).show();
            Log.d("Data", "" + Data);

            int debS = 0;
            int finS = 0;
            int finObj =0;
            List<String> listeT = new ArrayList<String>();
            //  List<Trajet> lt = new ArrayList<Trajet>();

            for(char s : Data.toCharArray()){


                if (s == '{'){

                    debS = Data.indexOf(s)+1+finObj;

                }

                else if (s == '}'){

                    finS = Data.indexOf(s)+finObj;

                }

                if (debS != 0 && finS !=0){


                    finObj = finS;

                    String obj = Data.substring(debS, finS);
                    listeT.add(obj);


                    String chaine = "ID_TRAJET\"";
                    int debut = obj.indexOf(chaine);
                    int longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    int fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String IDTRAJET  = obj.substring(longDebut, fin);

                    chaine = "HEUREDEPART_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String HEURETRAJET  = obj.substring(longDebut, fin);

                    chaine = "DATE_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String DATETRAJET  = obj.substring(longDebut, fin);

                    chaine = "LIEUARRIVE_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String ARRIVEETRAJET  = obj.substring(longDebut, fin);

                    chaine = "TELEPHONEPRO_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String TELTRAJET  = obj.substring(longDebut, fin);

                    chaine = "LIEUDEPART_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String DEPARTTRAJET  = obj.substring(longDebut, fin);

                    chaine = "PRIX_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String PRIXTRAJET  = obj.substring(longDebut, fin);

                    chaine = "NBPLACE_TRAJET\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String NBPLACETRAJET  = obj.substring(longDebut, fin);

                    chaine = "ID_PERSONNE\"";
                    debut = obj.indexOf(chaine);
                    longDebut = obj.indexOf(":", debut);
                    longDebut = longDebut +2;
                    fin = obj.indexOf(",", longDebut);
                    fin = fin -1;
                    String IDPERSONNE  = obj.substring(longDebut, fin);

                    Trajet newTrajet = new Trajet(IDTRAJET, HEURETRAJET, PRIXTRAJET, TELTRAJET, NBPLACETRAJET, DEPARTTRAJET, ARRIVEETRAJET, DATETRAJET, IDPERSONNE);

                    lt.add(newTrajet);

                    debS = 0;
                    finS = 0;

                }
            }

            Gson gson = new Gson();

            jsonCars = gson.toJson(lt);

            for(Trajet tr  : lt){
                Credential c = new Credential();
                ArrayList<String> vars = new ArrayList<String>();
                vars.add(tr.getIDPERSONNE_TRAJET());

                c.HttpRequest("searchpersonne", vars);

                HttpRequestTaskManager result = new HttpRequestTaskManager(context);
                result.execute(c);

            }
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Aucun trajet trouve.", Toast.LENGTH_LONG).show();

        }

    }

    public static void OnPostExecuteSearchpersonne(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            Log.d("Data", Data);
            String chaine = "NOM_PERSONNE\"";
            int debut = Data.indexOf(chaine);
            int longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            int fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String NOMPERSONNE  = Data.substring(longDebut, fin);

            chaine = "PRENOM_PERSONNE\"";
            debut = Data.indexOf(chaine);
            longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String PRENOMPERSONNE  = Data.substring(longDebut, fin);

            Log.d("PRENOMPERSONNE", PRENOMPERSONNE);
            Log.d("NOMPERSONNE", NOMPERSONNE);

            Personne p = new Personne(NOMPERSONNE, PRENOMPERSONNE);
            lp.add(p);

            Gson gson2 = new Gson();

            jsonCars2 = gson2.toJson(lp);
            j++;
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "échec de l'ajout,\nun problème c'est produit", Toast.LENGTH_LONG).show();
        }

        if (lt.size() == j){

            Intent v = new Intent(context, com.example.srava.coviutproject.VoirTrajet2.class);
            v.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.putExtra("listeTrajet", jsonCars);
            v.putExtra("personnetrajet", jsonCars2);
            context.startActivity(v);

            lt.clear();
            lp.clear();
            jsonCars = "";
            jsonCars2 = "";
            j = 0;
        }
    }

    public static void OnPostExecuteInscription(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            try {
                JSONObject jsonResponse = new JSONObject(Data);
                Log.d("json?",""+jsonResponse.toString());
                MyShotAdaptater sauvegardeShotsDB;
                sauvegardeShotsDB = new MyShotAdaptater(context);
                sauvegardeShotsDB.open();
                sauvegardeShotsDB.removeAllShot();
                sauvegardeShotsDB.insertShot(Integer.parseInt(jsonResponse.getString("ID_PERSONNE")),
                        jsonResponse.getString("MAIL_PERSONNE"),
                        jsonResponse.getString("NOM_PERSONNE"),
                        jsonResponse.getString("PRENOM_PERSONNE"),
                        jsonResponse.getString("TEL_PERSONNE"));
            }catch (org.json.JSONException e){
                Log.d("exception", "something went fucking wrong with the string to json" + e.getMessage());
            }
            Log.d("context", "" + context);
            Toast.makeText(context, "Vous êtes inscrit!", Toast.LENGTH_LONG).show();
            Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(FormChoix);
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Échec de l'inscription,\nveuillez corriger vos erreurs", Toast.LENGTH_LONG).show();
        }
    }








}
