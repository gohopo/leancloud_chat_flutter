package com.gohopo.leancloud_chat_flutter;

import com.gohopo.leancloud_chat_flutter.utils.Constants;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class LcfMethodHandler implements MethodChannel.MethodCallHandler {
    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        switch (call.method){
            case Constants.Method_getPlatformVersion:{
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            }
            case Constants.Method_initialize:{
                break;
            }
            default:{
                result.notImplemented();
                break;
            }
        }
    }
}
