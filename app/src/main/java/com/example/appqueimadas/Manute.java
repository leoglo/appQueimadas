package com.example.appqueimadas;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class Manute {

    static public String Murl = "http://queimadas.000webhostapp.com/";

    public void cadastrarUsuario(final Context context, String nome, String senha){

        String url = Murl + "CadastrarUsuario.php";

        Ion.with(context)
                .load(url)
                .setBodyParameter("nome", nome)
                .setBodyParameter("senha", senha)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {

                            String RETORNO = result.get("CADASTRO").getAsString();
                            if(RETORNO.equals("SUCESSO")){
                                Toast.makeText(context,"cadastro efetuado com Sucesso", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(context, "Erro ao Cadastrar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
    }

    public void validarlogin(final Context context, String nome, String senha){

      String url = Murl + "pegaUsuario.php";

        Ion.with(context)
                .load(url)
                .setBodyParameter("nome", nome)
                .setBodyParameter("senha", senha)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        try {

                            String RETORNO = result.get("LOGIN").getAsString();

                            if(RETORNO.equals("SUCESSO")){

                                context.startActivity( new Intent(context, tela2.class));
                            }
                            else{

                                Toast.makeText(context, "Usuario Não Cadastrado", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                });
    }

    public void cadastrarLocal(final Context context, String latitude, String longitude){

        String url = Murl + "cadastrarLocal.php";

        Ion.with(context)
                .load(url)
                .setBodyParameter("latitude", latitude)
                .setBodyParameter("longitude", longitude)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        try {
                            String RETORNO = result.get("CADASTRO").getAsString();

                            if(RETORNO.equals("SUCESSO")){
                                Toast.makeText(context,"local cadastrado com sucesso", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(context, "Erro ao Cadastrar", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
    }

}


