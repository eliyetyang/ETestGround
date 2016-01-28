package com.test.eliyetyang.testground.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eliyetyang on 15-8-26.
 */
public class AidlDemo implements Parcelable {
    private String name;
    private int time;

    public AidlDemo(String name, int time) {
        this.name = name;
        this.time = time;
    }

    protected AidlDemo(Parcel in) {
        name = in.readString();
        time = in.readInt();
    }

    public static final Creator<AidlDemo> CREATOR = new Creator<AidlDemo>() {
        @Override
        public AidlDemo createFromParcel(Parcel in) {
            return new AidlDemo(in);
        }

        @Override
        public AidlDemo[] newArray(int size) {
            return new AidlDemo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AidlDemo{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(time);
    }
}
