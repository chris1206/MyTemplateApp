package com.yto.template.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 2018/1/15.
 */

public class MyParcelable implements Parcelable{
    private int mData;

    public static final Parcelable.Creator<MyParcelable> CREATOR =
            new Parcelable.Creator<MyParcelable>() {

                @Override
                public MyParcelable createFromParcel(Parcel in) {
                    return new MyParcelable(in);
                }

                @Override
                public MyParcelable[] newArray(int size) {
                    return new MyParcelable[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
    }

    private MyParcelable(Parcel in) {
        this.mData = in.readInt();
    }
}
