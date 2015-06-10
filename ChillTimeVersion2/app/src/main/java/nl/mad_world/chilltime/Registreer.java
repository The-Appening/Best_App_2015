package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Registreer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "wxutoacSUnKAIN5NxgCm7QvHqmrw2VoVlm7wkMrp", "5cfOao5AJYe6d5H1LpuIFwK4mXev14jbZrNZADZB");
    }


    public void Save(View view) {
        saveRegistrationInDb();
    }

    public void saveRegistrationInDb() {

        String insertion;

        EditText fNameET = (EditText) findViewById(R.id.fName);
        String fName = fNameET.getText().toString();

        EditText insertionET = (EditText) findViewById(R.id.insertion);
        if(insertionET != null) {
            insertion = insertionET.getText().toString();
        }
        else{
            insertion = null;
        }

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
        UserSave.put("Gebruikersnaam", uName);
        UserSave.put("Wachtwoord", password);
        if(isEmailValid(email)==true) {
            UserSave.put("Email", email);
            UserSave.saveInBackground();

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Melding.")
                    .setMessage("De ingevulde e-mail is geen geldig e-mail adres.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(true)
                    .show();
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}
