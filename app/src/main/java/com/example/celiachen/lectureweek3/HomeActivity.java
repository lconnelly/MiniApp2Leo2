package com.example.celiachen.lectureweek3;

/**
 * Created by leoconnelly on 3/17/18.
 */

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {

    private Button GetStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GetStartedButton = findViewById(R.id.getStartedButton);


        //SETTING UP NOTIFCAITON BUTTON
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel

            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_ID", "channel_name", importance);
            channel.setDescription(description);

            // Register the channel
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        GetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchActivity();
            }
        });
    }

    public void openSearchActivity() {
        Intent MainActivitytoSearchActivity = new Intent(this, SearchActivity.class); // we are going
        // from the context of this page which is reffered to as this to the next activity wich is the search activity
        startActivity(MainActivitytoSearchActivity);
    }
}
