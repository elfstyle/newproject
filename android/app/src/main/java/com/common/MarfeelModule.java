package com.common;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.marfeel.compass.core.model.compass.UserType;
import com.marfeel.compass.tracker.CompassTracking;

class MarfeelModule extends ReactContextBaseJavaModule {
    private Context context;

    public MarfeelModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "MarfeelModule";
    }

    @ReactMethod
    public void initialize(String accountId) {
        CompassTracking.Companion.initialize(this.context, accountId,4);
    }

    @ReactMethod
    public void trackNewPage(String url) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                var tracker = CompassTracking.Companion.getInstance();
                tracker.trackNewPage(url);
            }
        });
    }

    @ReactMethod
    public void setSiteUserId(String userId) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                var tracker = CompassTracking.Companion.getInstance();
                tracker.setSiteUserId(userId);
            }
        });
    }

    @ReactMethod
    public void setUserType(String type) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                var tracker = CompassTracking.Companion.getInstance();
                switch(type) {
                    case "logged":
                        tracker.setUserType(UserType.Logged);
                        break;
                    case "paid":
                        tracker.setUserType(UserType.Paid);
                        break;
                    default:
                        tracker.setUserType(UserType.Anonymous);
                }
            }
        });
    }

    @ReactMethod
    public void trackConversion(String conversion) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                var tracker = CompassTracking.Companion.getInstance();
                tracker.trackConversion(conversion);
            }
        });
    }

    @ReactMethod
    public void setPageVar(String name, String value) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                var tracker = CompassTracking.Companion.getInstance();
                tracker.setPageVar(name, value);
            }
        });
    }

    @ReactMethod
    public void setSessionVar(String name, String value) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                var tracker = CompassTracking.Companion.getInstance();
                tracker.setSessionVar(name, value);
            }
        });
    }
}
