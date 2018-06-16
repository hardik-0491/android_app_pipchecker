package com.hardiksenghani.pipchecker;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_NAME = "MainActivity";

    TextView infoText, errorReasonText;
    Button pipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        infoText = findViewById(R.id.main_activity_text);
        errorReasonText = findViewById(R.id.main_activity_error_reason);
        pipButton = findViewById(R.id.main_activity_button);

        pipButton.setOnClickListener(v -> {
            Intent pipIntent = new Intent(this, PiPActivity.class);
            startActivity(pipIntent);
        });

        if (!Utils.isPiPModeSupported(this)) {
            pipButton.setVisibility(View.GONE);
            infoText.setText(R.string.pip_error);
        } else {
            infoText.setText(R.string.pip_success);
            errorReasonText.setVisibility(View.GONE);
        }

        Utils.prepareAds(this, R.id.activity_main_banner_adView, ACTIVITY_NAME);

    }

}
