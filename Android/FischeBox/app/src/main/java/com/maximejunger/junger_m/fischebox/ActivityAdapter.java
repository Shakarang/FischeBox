package com.maximejunger.junger_m.fischebox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by junger_m on 03/05/15.
 */
public class ActivityAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater inflater;

    public ActivityAdapter(Context ctx) {
        this.context = ctx;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        System.out.println("lolooo");
        return 18;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View theView = null;
        TextView    textView;

        theView = inflater.inflate(R.layout.speak_cell, null);
        textView = (TextView) theView.findViewById(R.id.textView5);
        textView.setText("Coucoucoucocuocu");
        return theView;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
