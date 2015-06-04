package nl.mad_world.chilltime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

    public class Agenda extends Activity {
        /**
         * Called when the activity is first created.
         */

        static final int DATE_DIALOG_ID = 0;
        static final int PICK_DATE_REQUEST = 1;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_DATE_REQUEST) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getApplicationContext(), data.getStringExtra("date"), Toast.LENGTH_SHORT).show();
                    String[] dateArr = data.getStringExtra("date").split("-");

                }
            }
        }
    }

