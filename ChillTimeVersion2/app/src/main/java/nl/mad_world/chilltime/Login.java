package nl.mad_world.chilltime;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Oscar Veldman on 09/06/2015.
 */
public class Login {


    public static boolean getLogin(final String User, final String Password) {
       Boolean go = false;
        final String[] playerPass = new String[1];
        final String[] playerUser = new String[1];
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.whereEqualTo("Gebruikersnaam", User);
        query.findInBackground(new FindCallback<ParseObject>() {
    public void done(List<ParseObject> inloggen, ParseException e) {
        if (e == null) {
            Log.d("score", "Retrieved " + inloggen.size() + " scores");
            playerPass[0] = inloggen.get(0).getString("Wachtwoord");
            playerUser[0] = inloggen.get(0).getString("Gebruikersnaam");
        } else {
            Log.d("score", "Error: " + e.getMessage());
        }
    }
});

        if(playerUser[0].equals(User) && playerPass[0].equals(Password)){
            go = true;
        }


        return go;
    }
}
