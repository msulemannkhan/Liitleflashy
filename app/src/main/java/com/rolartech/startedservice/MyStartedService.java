package com.rolartech.startedservice;

import android.app.Activity;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by SulemanKhan on 27/09/2017.
 */

public class MyStartedService extends Service {

    private static String TAG = MyStartedService.class.getSimpleName();
    public static FlashLight mFlashLight = new FlashLight();
    RemoteViews remoteViews;
    AppWidgetManager mgr;
    ComponentName myWidget;
    Activity activity;
    public static boolean turnFlash=false;

     static boolean isOpenFromMain= false;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate, ThreadName" + Thread.currentThread().getName());

        mFlashLight.getCamera();

        MainActivity.buttonOffOn=activity.findViewById(R.id.switch_btn);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mFlashLight.isFlashLightOn())
        {
            mFlashLight.turnOffFlash();
            mgr = AppWidgetManager.getInstance(this);
            remoteViews = new RemoteViews(getPackageName(), R.layout.flash_light_widget);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light_deactive_45);
            remoteViews.setImageViewBitmap(R.id.imageView2, bitmap);
            if(MainActivity.buttonOffOn != null){
                MainActivity.buttonOffOn.setImageResource(R.drawable.poweroff);
            }
            myWidget = new ComponentName( this, FlashLightWidget.class );
            mgr.updateAppWidget( myWidget, remoteViews);
            stopSelf();
        }
        else
        {
            mFlashLight.turnOnFlash();
            mgr = AppWidgetManager.getInstance(this);
            remoteViews = new RemoteViews(getPackageName(), R.layout.flash_light_widget);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light_active_45);
            if(MainActivity.buttonOffOn != null) {
                MainActivity.buttonOffOn.setImageResource(R.drawable.power);
            }
            remoteViews.setImageViewBitmap(R.id.imageView2, bitmap);
            myWidget = new ComponentName( this, FlashLightWidget.class );
            mgr.updateAppWidget( myWidget, remoteViews);
        }
        Log.i(TAG, "onStartCommand, ThreadName" + Thread.currentThread().getName());

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind, ThreadName" + Thread.currentThread().getName());
        return null;//this method must be overrided and for started service we have to return null from this method.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mFlashLight.turnOffFlash();
        Log.i(TAG, "onDestroy, ThreadName" + Thread.currentThread().getName());

        mgr = AppWidgetManager.getInstance(this);
        remoteViews = new RemoteViews(getPackageName(), R.layout.flash_light_widget);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light_deactive_45);
        remoteViews.setImageViewBitmap(R.id.imageView2, bitmap);
        myWidget = new ComponentName( this, FlashLightWidget.class );
        mgr.updateAppWidget( myWidget, remoteViews);
    }
}
