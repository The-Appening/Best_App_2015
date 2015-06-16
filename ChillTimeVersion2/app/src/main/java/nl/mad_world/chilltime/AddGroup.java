package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;


public class AddGroup extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
    }

    public void SaveGroup(View view) {
        ParseObject group = new ParseObject("Groups");

        EditText gNameET = (EditText) findViewById(R.id.groupName);
        String groupName = gNameET.getText().toString();

        group.put("Name", groupName);
        group.saveInBackground();

        Intent intent = new Intent(this, Group.class);

        startActivity(intent);

    }
}
