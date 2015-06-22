package nl.mad_world.chilltime;

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
import java.util.Locale;


public class Group extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    public void AddContact(View view){
        Intent intent = new Intent(this, addContact.class);

        startActivity(intent);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0) {
                return GroupFragment.newInstance(position);
            }
            if(position == 1) {
                return ContactFragment.newInstance(position);
            }
            if(position == 2) {
                return SettingsFragment.newInstance(position);
            }
            else {
                return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    public void AddAGroup(View view){

        Intent intent = new Intent(this, AddGroup.class);

        startActivity(intent);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class GroupFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GroupFragment newInstance(int sectionNumber) {
            GroupFragment fragment = new GroupFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public GroupFragment() {
        }
        private static ArrayList<String> Groups = new ArrayList<String>();

        public void getContactData(){
            //Groups.clear();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Groups");
            ParseUser currentUser = ParseUser.getCurrentUser();

            String username = currentUser.getUsername();
            query.whereEqualTo("Creator", username);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        for (int i = 0; i < objects.size(); i++) {
                            Groups.add(objects.get(i).getString("Name"));
                        }
                    } else {
                        Groups.add("Er zijn geen gebruikers gevonden");
                    }

                }
            });
        }



        public static GroupFragment newInstance() {
            Bundle args = new Bundle();
            GroupFragment fragment = new GroupFragment();
            fragment.setArguments(args);
            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_group, container, false);
            getContactData();
            ListView listView = (ListView) rootView.findViewById(R.id.GlistView);
            listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Groups));
            registerForContextMenu(listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {


                    Intent intent = new Intent(getActivity(), WeekViewer.class);
                    startActivity(intent);


                }
            });

            return rootView;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("Context Menu");
            menu.add(0, v.getId(), 0, "Verwijderen");
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            if(item.getTitle()=="Verwijderen"){
                //TODO maken functie die contact verwijdert.
                //
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                delete((int)info.id);
            }
            else {
                return false;
            }
            return true;
        }

        public void delete(int iditem){
            System.out.println(Groups.get(iditem));
            String GroupName = Groups.get(iditem);

            ParseQuery<ParseObject> query = new ParseQuery("Groups");
            query.whereEqualTo("Name", GroupName);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> Group, ParseException e) {
                    if (e == null) {
                        // Now let's update it with some new data. In this case, only cheatMode and score
                        // will get sent to the Parse Cloud. playerName hasn't changed.
                        int i = 0;
                        Group.get(i).deleteInBackground();
                    }
                }
            });
        }

    }

    /**
     * Settings fragment
     */
    public static class SettingsFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SettingsFragment newInstance(int sectionNumber) {
            SettingsFragment fragment = new SettingsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public SettingsFragment() {
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

            return rootView;
        }
    }

    /**
     * Contactfragment
     */
    public static class ContactFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        ArrayList<String> contacts = new ArrayList<>();;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.ClistView);
            contactList();
            listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contacts));
            registerForContextMenu(listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {

                    Intent intent = new Intent(getActivity(), Contact.class);
                    startActivity(intent);

                }
            });

            return rootView;
        }

        public void contactList(){
            ParseUser cUser = ParseUser.getCurrentUser();
            String cUserName = cUser.getUsername();
            ParseQuery<ParseObject> query = new ParseQuery("ContactList");
            query.whereEqualTo("UserOne", cUserName);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        for (int i = 0; i < objects.size(); i++) {
                            contacts.add(objects.get(i).getString("UserTwo"));
                        }
                    } else {
                        contacts.add("Geen contacten gevonden.");
                    }
                }
            });
            ParseQuery<ParseObject> query2 = new ParseQuery("ContactList");
            query2.whereEqualTo("UserTwo", cUserName);
            query2.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects2, ParseException e) {
                    if (e == null) {
                        for (int i = 0; i < objects2.size(); i++) {
                            contacts.add(objects2.get(i).getString("UserOne"));
                        }
                    } else {
                        contacts.add("Geen contacten gevonden.");
                    }
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("Context Menu");
            menu.add(0, v.getId(), 0, "Verwijderen");
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            if(item.getTitle()=="Verwijderen"){
                //TODO maken functie die contact verwijdert.
               //
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                delete((int)info.id);
            }
            else {
                return false;
            }
            return true;
        }

        public void delete(int iditem){
            System.out.println(contacts.get(iditem));
            String Friend = contacts.get(iditem);

            ParseQuery<ParseObject> query = new ParseQuery("ContactList");
            query.whereEqualTo("UserTwo", Friend);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> Contact, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    int i = 0;
                    Contact.get(i).deleteInBackground();
                }
            }
        });
        }

        private static final String ARG_SECTION_NUMBER = "section_number";
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ContactFragment newInstance(int sectionNumber) {
            ContactFragment fragment = new ContactFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ContactFragment() {
        }

        public static ContactFragment newInstance() {
            Bundle args = new Bundle();
            ContactFragment fragment = new ContactFragment();
            fragment.setArguments(args);
            return fragment;

        }

    }

}


