package com.test.eliyetyang.testground;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.test.eliyetyang.testground.aidl.AidlDemo;
import com.test.eliyetyang.testground.aidl.IMyRemoteService;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("tag", "tag");
        return mMyBinder;
    }

    private final IMyRemoteService.Stub mMyBinder = new IMyRemoteService.Stub() {
        @Override
        public AidlDemo getAidlDemo(String name, int time) throws RemoteException {
            return new AidlDemo(name, time);
        }
    };
}
