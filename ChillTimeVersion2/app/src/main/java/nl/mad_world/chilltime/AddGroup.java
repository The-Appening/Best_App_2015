package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class AddGroup extends ActionBarActivity {

    boolean Success = false;

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

        ParseQuery check = new ParseQuery("Groups");

        check.whereEqualTo("Name", groupName);
        check.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                if (e == null) {
                    if (list.size() > 1) {
                        Success = false;
                    }
                    if (list.size() == 0) {
                        Success = true;
                    }
                }
            }

            @Override
            public void done(Object o, Throwable throwable) {

            }
        });

        if(Success == true) {
            group.put("Name", groupName);
            group.put("Creator", username);
            group.saveInBackground();

            Intent intent = new Intent(this, Group.class);

            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(this)
                    .setMessage("De door u ingevulde groepsnaam is al in gebruik.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(true)
                    .show();
        }
    }
}
