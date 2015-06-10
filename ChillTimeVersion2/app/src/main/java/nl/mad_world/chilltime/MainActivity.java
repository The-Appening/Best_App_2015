package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Login(View view){
        EditText pWord = (EditText) findViewById(R.id.password);
        String pWordString = pWord.getText().toString();

        EditText uName = (EditText) findViewById(R.id.username);
        String uNameString = uName.getText().toString();
        if(pWordString.equals("admin") && uNameString.equals("admin")) {
            Intent intent = new Intent(this, Group.class);

            startActivity(intent);
        }
    }

    public void Registreer(View view){
        Intent intent = new Intent(this, Registreer.class);

        startActivity(intent);
    }

}
