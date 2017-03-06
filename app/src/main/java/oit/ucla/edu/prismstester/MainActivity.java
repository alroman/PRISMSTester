package oit.ucla.edu.prismstester;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button heartTrigger = (Button) findViewById(R.id.button);
        heartTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// heart trigger.

                Intent intent = trigger("heart_trigger");
                startActivity(intent);
            }
        });

        Button spirometer = (Button) findViewById(R.id.button2);
        spirometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = trigger("spirometer_trigger");
                startActivity(intent);
            }
        });

        Button airbeam = (Button) findViewById(R.id.button3);
        airbeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = trigger("airbeam_trigger");
                startActivity(intent);
            }
        });

        Button energy = (Button) findViewById(R.id.button4);
        energy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = trigger("energy_trigger");
                startActivity(intent);
            }
        });
    }

    private Intent trigger(String type) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("org.ohmage.x.prisms");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            final long timestamp = System.currentTimeMillis();

            intent.putExtra("trigger_type", type);
            intent.putExtra("trigger_time", timestamp);
            intent.putExtra("trigger_location", "-1");

            Log.d(TAG, "Launching Questionnaire: " + type + "," +
                    timestamp + "," + "-1");
        } else {
            // Load from app store..?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=org.ohmage.x.prisms"));

        }

        return intent;

    }
}
