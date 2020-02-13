package com.gohopo.leancloud_chat_flutter.common;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.gohopo.leancloud_chat_flutter.LcfMethodHandler;
import com.gohopo.leancloud_chat_flutter.LeancloudChatFlutterPlugin;
import com.gohopo.leancloud_chat_flutter.utils.Constants;

import cn.leancloud.AVOSCloud;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.AVIMMessageManager;
import cn.leancloud.im.v2.AVIMMessageOption;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.im.v2.callback.AVIMConversationCallback;
import cn.leancloud.im.v2.messages.AVIMTextMessage;
import io.flutter.plugin.common.MethodChannel;

public class LeancloudFunction {
    private static String curUserId;
    public static String getPlatformVersion(){
        return android.os.Build.VERSION.RELEASE;
    }
    public static void initialize(String appId,String appKey){
        AVOSCloud.initialize(LeancloudChatFlutterPlugin.Instance.Activity.getApplicationContext(), appId, appKey);
        AVIMOptions.getGlobalOptions().setUnreadNotificationEnabled(true);
        AVIMMessageManager.setConversationEventHandler(LeancloudConversationHandler.getInstance());
        AVIMClient.setClientEventHandler(LeancloudClientHandler.getInstance());
    }
    public static AVIMClient getClient(){
        if(!TextUtils.isEmpty(curUserId)){
            return AVIMClient.getInstance(curUserId);
        }
        return null;
    }
    public static void login(final String userId, final AVIMClientCallback callback){
        AVIMClient.getInstance(userId).open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if(null != e){
                    callback.internalDone(client,e);
                }
                else{
                    curUserId = userId;
                    callback.internalDone(client,e);
                }
            }
        });
    }
    public static void close(final AVIMClientCallback callback){
        AVIMClient.getInstance(curUserId).close(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                curUserId = null;
                callback.internalDone(client,e);
            }
        });
    }
    public static AVIMConversation getConversation(String conversationId){
        return getClient().getConversation(conversationId);
    }
    public static void setConversationRead(String conversationId){
        AVIMConversation conversation = getConversation(conversationId);
        conversation.read();
    }
    public static void sendMessage(String conversationId, String message, final MethodChannel.Result result){
        AVIMConversation conversation = getConversation(conversationId);
        final AVIMTextMessage msg = new AVIMTextMessage();
        msg.setText(message);
        AVIMMessageOption option = new AVIMMessageOption();
        option.setReceipt(true);
        conversation.sendMessage(msg, option, new AVIMConversationCallback() {
            @Override
            public void done(AVIMException e) {
                if(null != e){
                    LcfMethodHandler.returnError(result, Constants.Code_error,e.getMessage());
                }
                else{
                    LcfMethodHandler.returnResult(result, JSON.parse(JSON.toJSONString(msg)));
                }
            }
        });
    }
}
