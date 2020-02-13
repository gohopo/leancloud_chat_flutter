package com.gohopo.leancloud_chat_flutter.common;

import com.alibaba.fastjson.JSON;
import com.gohopo.leancloud_chat_flutter.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMConversationEventHandler;
import cn.leancloud.im.v2.AVIMMessage;
import io.flutter.plugin.common.EventChannel;

public class LeancloudConversationHandler extends AVIMConversationEventHandler {
    private static LeancloudConversationHandler _instance;
    private EventChannel.EventSink events;

    public static synchronized LeancloudConversationHandler getInstance() {
        if (null == _instance) {
            _instance = new LeancloudConversationHandler();
        }
        return _instance;
    }
    public void setFlutterEvents(EventChannel.EventSink events) {
        this.events = events;
    }
    private void returnResult(String event, AVIMConversation conversation, AVIMMessage message) {
        Map<String, String> result = new HashMap<>();
        result.put("event", event);
        if (null != conversation) result.put("conversation", JSON.toJSONString(conversation));
        if (null != message) result.put("message", JSON.toJSONString(message));
        if (null != events) events.success(result);
    }

    @Override
    public void onUnreadMessagesCountUpdated(AVIMClient client, AVIMConversation conversation) {
        returnResult(Constants.Event_onUnreadMessagesCountUpdated,conversation,null);
    }
    @Override
    public void onLastDeliveredAtUpdated(AVIMClient client, AVIMConversation conversation) {
        returnResult(Constants.Event_onLastDeliveredAtUpdated,conversation,null);
    }
    @Override
    public void onLastReadAtUpdated(AVIMClient client, AVIMConversation conversation) {
        returnResult(Constants.Event_onLastReadAtUpdated,conversation,null);
    }
    @Override
    public void onMessageRecalled(AVIMClient client, AVIMConversation conversation, AVIMMessage message) {
        returnResult(Constants.Event_onMessageRecalled,conversation,message);
    }
    @Override
    public void onMessageUpdated(AVIMClient client, AVIMConversation conversation, AVIMMessage message) {
        returnResult(Constants.Event_onMessageUpdated, conversation, message);
    }
    @Override
    public void onMemberLeft(AVIMClient client, AVIMConversation conversation, List<String> members, String kickedBy) {

    }
    @Override
    public void onMemberJoined(AVIMClient client, AVIMConversation conversation, List<String> members, String invitedBy) {

    }
    @Override
    public void onKicked(AVIMClient client, AVIMConversation conversation, String kickedBy) {

    }
    @Override
    public void onInvited(AVIMClient client, AVIMConversation conversation, String operator) {

    }
}
