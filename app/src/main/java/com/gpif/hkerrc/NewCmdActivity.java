package com.gpif.hkerrc;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gpif.hkerrc.cmds.Command;
import com.gpif.hkerrc.cmds.CommandFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class NewCmdActivity  extends ListActivity {

    private ArrayList<HashMap<String,Objects>> cmdTyps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_cmd);

        cmdTyps = CommandFactory.getHandleCmd();
        final ArrayList<String> list = new ArrayList<String>();
        for (HashMap elt : cmdTyps) {
            list.add((String) elt.get("name"));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        HashMap elt = cmdTyps.get(position);
        String cmd_class = (String) elt.get("class_name");
        Command cmdinst = CommandFactory.getCmd(cmd_class,"new" + cmdTyps.get(position));

        Intent cint = new Intent(this, cmdinst.getConfigActivityClass());
        cint.putExtra("action","new");
        cint.putExtra("list_position",-1);
        startActivity(cint);

        finish();

    }
}
