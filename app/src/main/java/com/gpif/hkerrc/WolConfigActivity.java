package com.gpif.hkerrc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gpif.hkerrc.cmds.ComCollDeserializer;
import com.gpif.hkerrc.cmds.ComCollSerializer;
import com.gpif.hkerrc.cmds.CommandsCollection;
import com.gpif.hkerrc.cmds.WOLCommand;


public class WolConfigActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_wol_layout);
        Intent wolConfig = getIntent();

        final Integer position = wolConfig.getExtras().getInt("position");
        String json = wolConfig.getExtras().getString("cmd_list");

        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(CommandsCollection.class, new ComCollSerializer());
        gson.registerTypeAdapter(CommandsCollection.class, new ComCollDeserializer());

        final Gson g = gson.create();
        final CommandsCollection values = g.fromJson(json,CommandsCollection.class);

        final EditText nameView = (EditText) findViewById(R.id.nameText);
        final EditText ipView = (EditText) findViewById(R.id.ipText) ;
        final EditText macView = (EditText) findViewById(R.id.macText);
        Button saveButton = (Button) findViewById(R.id.save_button) ;

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameView.getText().toString();
                String ip   = ipView.getText().toString();
                String mac  = macView.getText().toString();

                WOLCommand cmd = new WOLCommand(name,ip,mac);
                values.set(position,cmd);

                Intent send2Main = new Intent();
                String json = g.toJson(values);
                send2Main.putExtra("cmd_list", json);
                setResult(RESULT_OK,send2Main);
                finish();
            }
        });

        nameView.setText(values.get(position).getName());
        ipView.setText(((WOLCommand) values.get(position)).getIp());
        macView.setText(((WOLCommand) values.get(position)).getMac());
    }
}