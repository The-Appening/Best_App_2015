package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.Arrays;


public class Contact extends ActionBarActivity {

    private ListView tweetListView;
    private String[] stringArray ;
    private ArrayAdapter tweetItemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        stringArray = new String[10];
        stringArray[0] = "Oscar Veldman";
        stringArray[1] = "Roy van den Heuvel";
        stringArray[2] = "Johan Bos";
        for(int i=3; i < stringArray.length; i++){
            stringArray[i] = "String " + i;
        }
        Arrays.sort(stringArray);
        tweetItemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        tweetListView = (ListView) findViewById(R.id.listViewID);
        tweetListView.setAdapter(tweetItemArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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

    public void Groep(View view){
        Intent intent = new Intent(this, Groupen.class);

        startActivity(intent);
    }

    public void Contact(View view){
        Intent intent = new Intent(this, Contact.class);

        startActivity(intent);
    }


}
