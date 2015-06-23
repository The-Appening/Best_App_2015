package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class AddGroup extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
    }

    public void SaveGroup(View view) {

        EditText gNameET = (EditText) findViewById(R.id.groupName);
        String groupName = gNameET.getText().toString();

        ParseQuery<ParseObject> check = new ParseQuery("Groups");

        check.whereEqualTo("Name", groupName);
        check.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() == 0) {
                        register();
                    }
                }
            }
        });
    }

    public void register() {
        EditText gNameET = (EditText) findViewById(R.id.groupName);
        String groupName = gNameET.getText().toString();

        ParseUser currentUser = ParseUser.getCurrentUser();
        String username = currentUser.getUsername();

        ParseObject group = new ParseObject("Groups");
        ParseObject groupMembers = new ParseObject("Group_members");

        group.put("Name", groupName);
        group.put("Creator", username);
        group.saveInBackground();

        groupMembers.put("Username", username);
        groupMembers.put("GroupName", groupName);
        groupMembers.saveInBackground();

        Intent intent = new Intent(this, Group.class);
        startActivity(intent);
    }
}