package nl.mad_world.chilltime;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Johan on 28-5-2015.
 */
public class Tab3Fragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View V = inflater.inflate(R.layout.tab1, container, false);

            return V;
        }
}
