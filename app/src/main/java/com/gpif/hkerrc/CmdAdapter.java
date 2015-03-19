package com.gpif.hkerrc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gpif.hkerrc.cmds.Commands;

import java.util.List;


public class CmdAdapter extends ArrayAdapter<Commands> {
    private final Activity context;
    private final List<Commands> values;

    static class ViewHolder {
        protected Button action_b;
        protected ImageButton config_b;
        protected ImageButton delete_b;
    }
    public CmdAdapter(Activity context, List<Commands> values) {
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

            viewHolder.action_b.setText(values.get(position).getName());

            viewHolder.action_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "Hello " + values.get(position).getName() ;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            viewHolder.delete_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder adb=new AlertDialog.Builder(context);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete " +
                            values.get(position).getName());
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            values.remove(position);
                            notifyDataSetChanged();
                        }});
                    adb.show();
                }
            });

            rowView.setTag(viewHolder);
        } else {
            rowView = convertView;
            ((ViewHolder) rowView.getTag()).action_b.setText(values.get(position).getName());
        }
        return rowView;
    }
}