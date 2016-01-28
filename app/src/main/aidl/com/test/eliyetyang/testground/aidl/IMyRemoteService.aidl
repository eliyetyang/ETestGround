// IMyRemoteService.aidl
package com.test.eliyetyang.testground.aidl;

import com.test.eliyetyang.testground.aidl.AidlDemo;

interface IMyRemoteService {
    AidlDemo getAidlDemo(in String name,in int time);
}
