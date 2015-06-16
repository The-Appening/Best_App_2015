package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
        ParseUser currentUser = ParseUser.getCurrentUser();

        String username = currentUser.getUsername();

        EditText gNameET = (EditText) findViewById(R.id.groupName);
        String groupName = gNameET.getText().toString();

        group.put("Name", groupName);
        group.put("Creator", username);
        group.saveInBackground();

        Intent intent = new Intent(this, Group.class);

        startActivity(intent);

    }
}
