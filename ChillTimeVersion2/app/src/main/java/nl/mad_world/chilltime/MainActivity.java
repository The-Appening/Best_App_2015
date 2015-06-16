package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "wxutoacSUnKAIN5NxgCm7QvHqmrw2VoVlm7wkMrp", "5cfOao5AJYe6d5H1LpuIFwK4mXev14jbZrNZADZB");
    }


    public void Login(View view){
        EditText pWord = (EditText) findViewById(R.id.password);
        String pWordString = pWord.getText().toString();

        EditText uName = (EditText) findViewById(R.id.username);
        String uNameString = uName.getText().toString();

        final Intent intent = new Intent(this, Group.class);

        ParseUser.logInInBackground(uNameString, pWordString, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    startActivity(intent);
                } else {
                    showAlert("De door u ingevulde gebruikersnaam en/of wachtwoord zijn incorrect.");
                }
            }
        });
    }

    public void showAlert(String message){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(true)
                .show();
    }

    public void Registreer(View view){
        Intent intent = new Intent(this, Registreer.class);

        startActivity(intent);
    }

}
