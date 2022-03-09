/**
 * I, James Pham, 000738873 certify that this material is my original work. No
 * other person's work has been used without due acknowledgement.
 *
 * sorry the video is a bit long
 * https://www.youtube.com/watch?v=SN5496H3mCU
 */

package ca.mohawk.pham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Snackbar snackBar;
    public static String growingString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ask for permissions
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS}, 1);

        //get intent from MyReceiver
        Intent myIntent = getIntent();
        String msg = myIntent.getStringExtra(MyReceiver.MSG);
        TextView tv = findViewById(R.id.textView);

        //get current time and format
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat d = new SimpleDateFormat();

        //display message into textView
        if (msg != null ) {
            growingString += "\n" +  d.format(c) + "\n" + msg;
            tv.setText(growingString += "\n");
        }

    }

    /***
     * handler for the buttons in the snackbar
     * @param b1
     */
    public void snackBarg (View b1){
        if (b1.getId() == R.id.buttonRetry) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        } else if (b1.getId() == R.id.buttonQuit) {
            finish();
            System.exit(0);
        }
    }

    /***
     * checks permissions and displays snackbar for a denied permission
     * @param resultCode
     * @param permissions
     * @param results
     */
    @Override
    public void onRequestPermissionsResult(int resultCode, String permissions[], int[] results ) {
        super.onRequestPermissionsResult(resultCode, permissions, results);
        TextView tv = findViewById(R.id.textView);
        //check if the permissions were denied
        if (results.length > 0 && results[0] != PackageManager.PERMISSION_GRANTED) {
            //snackbar menu
            snackBar = Snackbar.make(tv, "", Snackbar.LENGTH_INDEFINITE);

            snackBar.setBackgroundTint(Color.rgb(200, 0, 100));
            LayoutInflater inflater = getLayoutInflater();
            View customSnackView = inflater.inflate(R.layout.snackbar, null);
            ViewGroup snackbarLayout = (ViewGroup) snackBar.getView();

            snackbarLayout.setPadding(25, 25, 25, 25);
            snackbarLayout.addView(customSnackView, 0);

            Button bsnack1 = customSnackView.findViewById(R.id.buttonRetry);
            bsnack1.setOnClickListener(this::snackBarg);
            Button bsnack2 = customSnackView.findViewById(R.id.buttonQuit);
            bsnack2.setOnClickListener(this::snackBarg);

            snackBar.show();
        }


    }
}
