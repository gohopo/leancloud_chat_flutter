package com.gohopo.leancloud_chat_flutter;

import com.alibaba.fastjson.JSON;
import com.gohopo.leancloud_chat_flutter.common.LeancloudFunction;
import com.gohopo.leancloud_chat_flutter.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.im.v2.callback.AVIMConversationCallback;
import cn.leancloud.im.v2.messages.AVIMTextMessage;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class LcfMethodHandler implements MethodChannel.MethodCallHandler {
    @Override
    public void onMethodCall(MethodCall call, final MethodChannel.Result result) {
        switch (call.method){
            case Constants.Method_getPlatformVersion:{
                result.success("Android " +  LeancloudFunction.getPlatformVersion());
                break;
            }
            case Constants.Method_initialize:{
                String appId = call.argument("appId");
                String appKey = call.argument("appKey");
                LeancloudFunction.initialize(appId,appKey);
                break;
            }
            case Constants.Method_login:{
                final String userId = call.argument("userId");
                LeancloudFunction.login(userId, new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient client, AVIMException e) {
                        if(null != e){
                            returnError(result,Constants.Code_error,e.getMessage());
                        }
                        else{
                            returnResult(result,userId);
                        }
                    }
                });
                break;
            }
            case Constants.Method_close:{
                LeancloudFunction.close(new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient client, AVIMException e) {
                        if(null != e){
                            returnError(result,Constants.Code_error,e.getMessage());
                        }
                        else{
                            returnResult(result,Constants.Code_success);
                        }
                    }
                });
                break;
            }
            case Constants.Method_setConversationRead:{
                String conversationId = call.argument("conversationId");
                LeancloudFunction.setConversationRead(conversationId);
                break;
            }
            case Constants.Method_sendMessage:{
                final String conversationId = call.argument("conversationId");
                final String message = call.argument("message");
                LeancloudFunction.sendMessage(conversationId, message, result);
                break;
            }
            default:{
                result.notImplemented();
                break;
            }
        }
    }

    public static void returnResult(MethodChannel.Result result,Object data){
        Map<String, Object> dict = new HashMap<>();
        dict.put("code", Constants.Code_success);
        dict.put("result", data);
        result.success(dict);
    }
    public static void returnError(MethodChannel.Result result, int code, @Nullable String message){
        Map<String, Object> dict = new HashMap<>();
        dict.put("code", code);
        dict.put("message", message!=null ? message : "");
        result.success(dict);
    }
}
