package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class Registreer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registreer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Aanmelden(View view){
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

        System.out.println(fName);
        System.out.println(insertion);
        System.out.println(sName);
        System.out.println(uName);
        System.out.println(password);
        System.out.println(email);

        /*
        Intent intent = new Intent(this, Group.class);

        startActivity(intent);
        */
    }


}
