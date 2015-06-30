package nl.mad_world.chilltime;

import com.parse.Parse;

/**
 * Created by Johan on 30-6-2015.
 */
public class Parse extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        com.parse.Parse.enableLocalDatastore(this);
        com.parse.Parse.initialize(this, "wxutoacSUnKAIN5NxgCm7QvHqmrw2VoVlm7wkMrp", "5cfOao5AJYe6d5H1LpuIFwK4mXev14jbZrNZADZB");
    }

}