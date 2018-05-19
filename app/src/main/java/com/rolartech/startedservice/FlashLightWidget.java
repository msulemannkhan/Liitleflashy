package com.rolartech.startedservice;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SulemanKhan on 27/09/2017.
 */

public class FlashLightWidget extends AppWidgetProvider {
    RemoteViews remoteViews;
Activity activity;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent intent = new Intent(context, MyStartedService.class);
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.flash_light_widget);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.light_deactive_45);
        remoteViews.setImageViewBitmap(R.id.imageView2, bitmap);
        CharSequence text="Flashy";
        remoteViews.setTextViewText(R.id.appName,text);
        PendingIntent pendingIntent = PendingIntent.getService(context.getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ComponentName myWidget = new ComponentName( context, FlashLightWidget.class );
        remoteViews.setOnClickPendingIntent(R.id.imageView2,pendingIntent);
        appWidgetManager.updateAppWidget( myWidget, remoteViews);
        Log.e("TAG", "onUpdate");
    }

}
