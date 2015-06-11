package nl.mad_world.chilltime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class addContact extends ActionBarActivity {
    ArrayList<String> ContactAdd = new ArrayList<>();
    private ListView monthsListView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void SearchContact(View view){
        getContactData();
        monthsListView = (ListView) findViewById(R.id.AddContactList);

        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ContactAdd);
        monthsListView.setAdapter(arrayAdapter);
    }

    public void getContactData(){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
       // query.whereEqualTo("username","*");
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        ContactAdd.add(objects.get(i).getString("username"));
                    }
                } else {
                        ContactAdd.add("Er zijn geen gebruikers gevonden");
                }

            }
        });
    }

}




