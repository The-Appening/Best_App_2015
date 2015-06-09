package nl.mad_world.chilltime;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class maakEvent extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Hello User",
                Toast.LENGTH_SHORT);

        toast.show();

    }

}


