package com.gpif.hkerrc;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gpif.hkerrc.cmds.CommandsCollection;
import com.gpif.hkerrc.cmds.WOLCommand;


public class MainActivity extends ListActivity {
    private CommandsCollection values;
    private CmdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        values = new CommandsCollection();
        values.add(new WOLCommand("Wake Server","127.0.0.1","AA:BB:CC:DD:EE"));

        adapter = new CmdAdapter(this, values);
        setListAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            this.values = (CommandsCollection) data.getSerializableExtra("cmd_list");
            adapter = new CmdAdapter(this, values);
            setListAdapter(adapter);
            adapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(this, "Update succes : " + values.get(0).getName(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
