package nl.mad_world.chilltime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AddGroupMember extends ActionBarActivity {

    private static ArrayList<String> Groups = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_member);
        getGroupData();
        ListView listView = (ListView) this.findViewById(R.id.ListView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Groups));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Intent intent = getIntent();

                String toAddMember = intent.getExtras().getString("NewMember");

                addAMember(Groups.get(position), toAddMember);

                Intent intent2 = new Intent(AddGroupMember.this, Group.class);
                startActivity(intent2);


            }
        });
    }

    public void addAMember(String groupName, String toAddMember){

        ParseObject groupMembers = new ParseObject("Group_members");
        groupMembers.put("Username", toAddMember);
        groupMembers.put("GroupName", groupName);
        groupMembers.saveInBackground();
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
