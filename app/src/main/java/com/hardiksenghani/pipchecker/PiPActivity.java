package com.hardiksenghani.pipchecker;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

public class PiPActivity extends AppCompatActivity {

    public static final String ACTIVITY_NAME = "PiPActivity";

    TextView pipHintText;
    AdView bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi_p);

        pipHintText = findViewById(R.id.activity_pip_text_hint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (isInPictureInPictureMode()) {
                pipHintText.setVisibility(View.GONE);
            } else {
                pipHintText.setVisibility(View.VISIBLE);
            }
        }

        bannerAd = Utils.prepareAds(this, R.id.activity_pip_banner_adView, ACTIVITY_NAME);
    }

    private void preparePiPMode(boolean inPiPMode) {
        ActionBar actionBar = getSupportActionBar();
        if (inPiPMode) {
            pipHintText.setVisibility(View.GONE);
            bannerAd.setVisibility(View.GONE);
            if (actionBar != null) {
                actionBar.hide();
            }
        } else {
            pipHintText.setVisibility(View.VISIBLE);
            bannerAd.setVisibility(View.VISIBLE);
            if (actionBar != null) {
                actionBar.show();
            }
        }
    }

    @Override
    protected void onUserLeaveHint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                PictureInPictureParams.Builder params = new PictureInPictureParams.Builder();
                enterPictureInPictureMode(params.build());
            } catch (Exception e) {
                Toast.makeText(this, R.string.pip_exception, Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        preparePiPMode(isInPictureInPictureMode);
    }
}
