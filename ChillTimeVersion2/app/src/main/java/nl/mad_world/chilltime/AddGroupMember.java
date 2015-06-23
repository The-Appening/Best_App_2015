package nl.mad_world.chilltime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class AddGroupMember extends ActionBarActivity {

    private static ArrayList<String> Groups = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_member);
        getGroupData();
        ListView listView = (ListView) this.findViewById(R.id.ListView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Groups));
    }

    public void getGroupData() {
        //Groups.clear();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Group_members");
        ParseUser currentUser = ParseUser.getCurrentUser();

        String username = currentUser.getUsername();
        query.whereEqualTo("Username", username);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        Groups.add(objects.get(i).getString("GroupName"));
                    }
                } else {
                    Groups.add("Er zijn geen groepen gevonden");
                }
            }
        });
    }
}
