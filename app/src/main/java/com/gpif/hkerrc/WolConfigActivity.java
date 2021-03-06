package com.gpif.hkerrc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gpif.hkerrc.cmds.CommandsCollection;
import com.gpif.hkerrc.cmds.WOLCommand;


public class WolConfigActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_wol_layout);
        Intent wolConfig = getIntent();

        final Integer position = wolConfig.getExtras().getInt("list_position");
        final String action = wolConfig.getStringExtra("action");
        final CommandsCollection values = CommandsCollection.loadConf(this);

        final EditText nameView = (EditText) findViewById(R.id.nameText);
        final EditText ipView = (EditText) findViewById(R.id.ipText) ;
        final EditText macView = (EditText) findViewById(R.id.macText);
        Button saveButton = (Button) findViewById(R.id.save_button) ;

        if (action.equals("edit")) {
            nameView.setText(values.get(position).getName());
            ipView.setText(((WOLCommand) values.get(position)).getIp());
            macView.setText(((WOLCommand) values.get(position)).getMac());
        }

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameView.getText().toString();
                String ip   = ipView.getText().toString();
                String mac  = macView.getText().toString();

                WOLCommand cmd = new WOLCommand(name,ip,mac);
                if (action.equals("edit")) {
                    values.set(position,cmd);
                } else {
                    values.add(cmd);
                }
                values.saveConf(getApplicationContext());
                finish();
            }
        });
    }
}
