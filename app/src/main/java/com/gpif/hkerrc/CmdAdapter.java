package com.gpif.hkerrc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class CmdAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] values;

    static class ViewHolder {
        protected Button action_b;
        protected ImageButton config_b;
        protected ImageButton delete_b;
    }
    public CmdAdapter(Activity context, String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        if  (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowlayout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.action_b = (Button) rowView.findViewById(R.id.action_b);
            viewHolder.config_b = (ImageButton) rowView.findViewById(R.id.config_b);
            viewHolder.delete_b = (ImageButton) rowView.findViewById(R.id.delete_b);

            viewHolder.action_b.setText(values[position]);

            viewHolder.action_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "Hello " + values[position] ;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            rowView.setTag(viewHolder);
        } else {
            rowView = convertView;
            ((ViewHolder) rowView.getTag()).action_b.setText(values[position]);
        }
        return rowView;
    }
}