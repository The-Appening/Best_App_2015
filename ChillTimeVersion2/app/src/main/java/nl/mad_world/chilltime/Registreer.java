package nl.mad_world.chilltime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;


public class Registreer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "wxutoacSUnKAIN5NxgCm7QvHqmrw2VoVlm7wkMrp", "5cfOao5AJYe6d5H1LpuIFwK4mXev14jbZrNZADZB");
    }


    public void Save(View view) {

        Runnable r = new Runnable() {
            public void run() {
                saveRegistrationInDb();
            }
        };

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

    public void saveRegistrationInDb() {

        EditText fNameET = (EditText) findViewById(R.id.fName);
        String fName = fNameET.getText().toString();

        EditText insertionET = (EditText) findViewById(R.id.insertion);
        String insertion = insertionET.getText().toString();

        EditText sNameET = (EditText) findViewById(R.id.sName);
        String sName = sNameET.getText().toString();

        EditText uNameET = (EditText) findViewById(R.id.uName);
        String uName = uNameET.getText().toString();

        EditText passwordET = (EditText) findViewById(R.id.password);
        String password = passwordET.getText().toString();

        EditText emailET = (EditText) findViewById(R.id.email);
        String email = emailET.getText().toString();

        ParseObject UserSave = new ParseObject("User");
        UserSave.put("Voornaam", fName);
        UserSave.put("Tussen", insertion);
        UserSave.put("Achternaam", sName);
        UserSave.put("Email", password);
        UserSave.put("Gebruikersnaam", uName);
        UserSave.put("Wachtwoord", email);
        UserSave.saveInBackground();
    }

}
