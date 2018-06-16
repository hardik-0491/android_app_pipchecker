package com.hardiksenghani.pipchecker;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Utils {

    private static final String TAG = "com.hardiksenghani.pipchecker";

    public static AdView prepareAds(AppCompatActivity activity,
                                    int adViewLayoutId, String activityName) {
        AdView bannerAd = activity.findViewById(adViewLayoutId);

        MobileAds.initialize(activity);

        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);

        return bannerAd;
    }

    public static void setUpActionBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
    }

    @SuppressLint("LongLogTag")
    public static boolean isPiPModeSupported(Context context) {
        boolean isPiPModeSupported = false;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return false;
        }

        isPiPModeSupported = true;

        try {
            PackageManager packageManager = context.getPackageManager();
            if(packageManager != null) {

                isPiPModeSupported = packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE);

                if (isPiPModeSupported) {
                    /*
                    //Per App basis permission
                    String value = null;
                    value = AppOpsManagerCompat.permissionToOp(AppOpsManager.OPSTR_PICTURE_IN_PICTURE);
                    if (value != null) {
                        isPiPModeSupported = Boolean.parseBoolean(value);
                    }
                    */
                }

            }
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }

        return isPiPModeSupported;
    }

}
