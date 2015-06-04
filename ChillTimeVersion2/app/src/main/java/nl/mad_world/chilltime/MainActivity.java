package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Login(View view){
        Intent intent = new Intent(this, Group.class);

        startActivity(intent);
    }

    public void Registreer(View view){
        Intent intent = new Intent(this, Registreer.class);

        startActivity(intent);
    }

}
