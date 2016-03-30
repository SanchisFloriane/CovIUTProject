package com.example.srava.coviutproject;

import android.content.Context;
import android.content.Intent;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONObject;
import org.json.JSONStringer;



import java.io.StringReader;


public final class OnPostExecuteFunction {

    private OnPostExecuteFunction(){};

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
            Toast.makeText(context, "ConnectÃ©", Toast.LENGTH_LONG).show();

            Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(FormChoix);
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Ã‰chec de la connection,\nmauvais mot de passe ou nom d'utilisateur", Toast.LENGTH_LONG).show();
        }
    }

    public static void OnPostExecuteProposer(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            Log.d("context", "" + context);

            Toast.makeText(context, "Trajet proposé", Toast.LENGTH_LONG).show();
            Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(FormChoix);
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Échec de l'ajout,\nun problême s'est produit", Toast.LENGTH_LONG).show();
        }
    }

    public static void OnPostExecuteTrajet(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            Log.d("context", "" + context);

            Toast.makeText(context, "Trajet trouvés", Toast.LENGTH_LONG).show();

            String chaine = "ID_TRAJET\"";
            int debut = Data.indexOf(chaine);
            int longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            int fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String IDTRAJET  = Data.substring(longDebut, fin);

            chaine = "HEUREDEPART_TRAJET\"";
            debut = Data.indexOf(chaine);
            longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String HEURETRAJET  = Data.substring(longDebut, fin);

            chaine = "TELEPHONEPRO_TRAJET\"";
            debut = Data.indexOf(chaine);
            longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String TELTRAJET  = Data.substring(longDebut, fin);

            chaine = "PRIX_TRAJET\"";
            debut = Data.indexOf(chaine);
            longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String PRIXTRAJET  = Data.substring(longDebut, fin);

            chaine = "NBPLACE_TRAJET\"";
            debut = Data.indexOf(chaine);
            longDebut = Data.indexOf(":", debut);
            longDebut = longDebut +2;
            fin = Data.indexOf(",", longDebut);
            fin = fin -1;
            String NBPLACETRAJET  = Data.substring(longDebut, fin);

           //Trajet newTrajet = new Trajet(IDTRAJET, HEURETRAJET, TELTRAJET, PRIXTRAJET, NBPLACETRAJET);
            VoirTrajet2 vt = new VoirTrajet2();

            //vt.retournerTrajet(newTrajet);


        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Aucun trajet trouve.", Toast.LENGTH_LONG).show();
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
            Toast.makeText(context, "Vous Ãªtes inscrit!", Toast.LENGTH_LONG).show();
            Intent FormChoix = new Intent(context, com.example.srava.coviutproject.FormChoix.class);
            FormChoix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(FormChoix);
        }
        else
        {
            Log.d("context", "" + context);
            Toast.makeText(context, "Ã‰chec de l'inscription,\nveuillez corriger vos erreurs", Toast.LENGTH_LONG).show();
        }
    }



}
