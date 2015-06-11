package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class addContact extends ActionBarActivity {

    private ListView monthsListView;
    private ArrayAdapter arrayAdapter;
    private ArrayList ContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void SearchContact(View view){
        monthsListView = (ListView) findViewById(R.id.AddContactList);
        getContactData();

        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ContactList);
        monthsListView.setAdapter(arrayAdapter);
    }

    public void getContactData(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("Voornaam", "roy");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }

                for(int i = 0; i > scoreList.size(); i++){
                    ContactList.add(scoreList.get(i));
                }
            }
        });
    }

}




