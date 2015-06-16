package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Registreer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ViewGroup group = (ViewGroup) findViewById(R.id.rlayout);
        clearForm(group);
    }

    private void clearForm(ViewGroup group)
    {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

    public void Save(View view) {
        saveRegistrationInDb();
    }

    public void saveRegistrationInDb() {
        ParseUser user = new ParseUser();

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

        user.put("Voornaam", fName);
        user.put("Tussen", insertion);
        user.put("Achternaam", sName);
        user.setUsername(uName);
        user.setPassword(password);
        if(isEmailValid(email)==true) {
            user.setEmail(email);
            user.signUpInBackground();

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(this)
                    .setMessage("Het ingevulde e-mail adres is geen geldig e-mail adres.")
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
