package com.yto.template.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by Zc on 2018/3/8.
 */

public class SMSContentObserver extends ContentObserver {
    private Context mContext  ;
    private Handler mHandler ;
    private int a = 0;
    public SMSContentObserver(Context context,Handler handler) {
        super(handler);
        mContext = context ;
        mHandler = handler ;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        ContentValues contentValues = new ContentValues();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            contentValues.put("person", cursor.getString(cursor.getColumnIndex("person")));
            contentValues.put("address", cursor.getString(cursor.getColumnIndex("address")));
            contentValues.put("body", cursor.getString(cursor.getColumnIndex("body")));
            contentValues.put("date", cursor.getString(cursor.getColumnIndex("date")));
            contentValues.put("type", cursor.getString(cursor.getColumnIndex("type")));
        }
        cursor.close();
        mHandler.obtainMessage(a, contentValues).sendToTarget();
    }
}

