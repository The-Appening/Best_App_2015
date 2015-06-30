package nl.mad_world.chilltime;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Login(View view) {
        EditText pWord = (EditText) findViewById(R.id.password);
        String pWordString = pWord.getText().toString();

        EditText uName = (EditText) findViewById(R.id.username);
        String uNameString = uName.getText().toString();

        final Intent intent = new Intent(this, Group.class);

        if (isInternetOn()) {
            ParseUser.logInInBackground(uNameString, pWordString, new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        startActivity(intent);
                    } else {
                        showAlert("De door u ingevulde gebruikersnaam en/of wachtwoord zijn incorrect.");
                    }
                }
            });
        } else {
            showAlert("U heeft mogelijk geen internetverbinding of uw internetverbinding is te zwak.");
        }
    }

    public void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(true)
                .show();
    }

    public void Registreer(View view) {
        Intent intent = new Intent(this, Registreer.class);

        startActivity(intent);
    }

    public final boolean isInternetOn() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }

}
