package com.example.appqueimadas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class localAdaptador extends BaseAdapter {

    private Context ctx;
    private List<LocalMemoria> list;

    public localAdaptador(Context ctx2, List<LocalMemoria> list2){
        ctx =ctx2;
        list = list2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public LocalMemoria getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
       View view1 = null;

        if(view==null ){
            LayoutInflater inflater = ((Activity)ctx).getLayoutInflater();
            view1 = inflater.inflate(R.layout.iten_lista, null);
        }
        else{
            view1 = view;
        }

        LocalMemoria l= getItem(position);

        TextView itemEndereco = (TextView) view1.findViewById(R.id.itemEndereco);
        TextView itemLatLong = (TextView) view1.findViewById(R.id.itemLatLong);

        itemEndereco.setText(l.getEndereco());
        itemLatLong.setText(l.getLatitude());

        return view1;
    }
}
