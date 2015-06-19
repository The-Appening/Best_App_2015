package nl.mad_world.chilltime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
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
        Parse.initialize(this, "wxutoacSUnKAIN5NxgCm7QvHqmrw2VoVlm7wkMrp", "5cfOao5AJYe6d5H1LpuIFwK4mXev14jbZrNZADZB");
    }

    public void SearchContact(View view) {

        monthsListView = (ListView) findViewById(R.id.AddContactList);

        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ContactAdd);
        getContactData();
        registerForContextMenu(monthsListView);
        monthsListView.setAdapter(arrayAdapter);

    }

    public void getContactData() {
        ContactAdd.clear();
        EditText sNameET = (EditText) findViewById(R.id.ContactName);
        String ContactNames = sNameET.getText().toString();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", ContactNames);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        ContactAdd.add(objects.get(i).getString("username"));
                        arrayAdapter.notifyDataSetChanged();
                    }
                } else {
                    ContactAdd.add("Er zijn geen gebruikers gevonden");
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Contact");
        menu.add(0, v.getId(), 0, "Toevoegen");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle() == "Toevoegen") {
            //TODO maken functie die contact verwijdert.
            editContactList(ContactAdd.get((int) info.id));
        } else {
            return false;
        }
        return true;
    }

    public void editContactList(String toAddContact) {
        ParseUser currentUserObject = ParseUser.getCurrentUser();
        String currentUser = currentUserObject.getUsername();
        ParseObject contacts = new ParseObject("ContactList");
        contacts.put("UserOne", currentUser);
        contacts.put("UserTwo", toAddContact);
        contacts.saveInBackground();
    }
}




