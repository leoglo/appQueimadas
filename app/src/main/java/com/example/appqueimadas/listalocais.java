package com.example.appqueimadas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class listalocais extends AppCompatActivity {

    ListView listView ;
    localAdaptador LocalAdaptador;
    List<LocalMemoria>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listalocais);


        listView = findViewById(R.id.list_locais);
        list = new ArrayList<LocalMemoria>();
        LocalAdaptador = new localAdaptador(listalocais.this,list);
        listView.setAdapter(LocalAdaptador);
        listar();

    }

    private void listar(){
        Manute manute = new Manute();
        String url = manute.Murl+"listaLocal.php";

        Ion.with(getBaseContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        for(int i = 0; i < result.size();i ++){
                            JsonObject object = result.get(i).getAsJsonObject();

                            LocalMemoria Local = new LocalMemoria();
                            Local.setId(object.get("id").getAsInt());
                            Local.setEndereco(object.get("endereco").getAsString());
                            Local.setLatitude(object.get("latitude").getAsString());
                            Local.setLongitude(object.get("longitude").getAsString());

                            list.add(Local);
                        }
                        LocalAdaptador.notifyDataSetChanged();
                    }
                });
    }

}