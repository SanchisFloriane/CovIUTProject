package com.example.srava.coviutproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by merciant on 01/03/2016.
 */
public final class OnPostExecuteFunction {

    private OnPostExecuteFunction(){};

    public static void OnPostExecuteLogin(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
            Log.d("context", "" + context);

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


    public static void OnPostExecuteInscription(Integer etat, String message, String Data,Context context){
        if(etat == 1)
        {
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
