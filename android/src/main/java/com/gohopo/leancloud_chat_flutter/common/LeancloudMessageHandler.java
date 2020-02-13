package com.gohopo.leancloud_chat_flutter.common;

import com.alibaba.fastjson.JSON;
import com.gohopo.leancloud_chat_flutter.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMTypedMessage;
import cn.leancloud.im.v2.AVIMTypedMessageHandler;
import io.flutter.plugin.common.EventChannel;

public class LeancloudMessageHandler extends AVIMTypedMessageHandler<AVIMTypedMessage> {
    private static LeancloudMessageHandler _instance;
    private EventChannel.EventSink events;

    public static synchronized LeancloudMessageHandler getInstance() {
        if (null == _instance) {
            _instance = new LeancloudMessageHandler();
        }
        return _instance;
    }
    public void setFlutterEvents(EventChannel.EventSink events) {
        this.events = events;
    }

    @Override
    public void onMessage(AVIMTypedMessage message, AVIMConversation conversation, AVIMClient client) {
        super.onMessage(message, conversation, client);
        Map<String, String> result = new HashMap<>();
        result.put("event", Constants.Event_onMessageReceived);
        result.put("conversation", JSON.toJSONString(conversation));
        result.put("message", JSON.toJSONString(message));
        if (null != events) events.success(result);
    }
}
