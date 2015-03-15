package com.gpif.hkerrc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class CmdAdapter extends ArrayAdapter<String> {
    private final Context context;

    public CmdAdapter(Context context, String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        return rowView;
    }
}