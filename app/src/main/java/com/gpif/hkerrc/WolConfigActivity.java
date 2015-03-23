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

        final Integer position = wolConfig.getExtras().getInt("position");
        final CommandsCollection values = (CommandsCollection) wolConfig.getSerializableExtra("cmd_list");

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
                send2Main.putExtra("cmd_list", values);
                setResult(RESULT_OK,send2Main);
                finish();
            }
        });

        nameView.setText(values.get(position).getName());
        ipView.setText(((WOLCommand) values.get(position)).getIp());
        macView.setText(((WOLCommand) values.get(position)).getMac());
    }
}
