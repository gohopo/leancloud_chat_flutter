package com.gohopo.leancloud_chat_flutter;

import com.gohopo.leancloud_chat_flutter.common.LeancloudMessageHandler;

import cn.leancloud.im.v2.AVIMMessageManager;
import cn.leancloud.im.v2.AVIMTypedMessage;
import io.flutter.plugin.common.EventChannel;

public class LcfMessageHandler implements EventChannel.StreamHandler {
    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        LeancloudMessageHandler.getInstance().setFlutterEvents(events);
        AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class, LeancloudMessageHandler.getInstance());
    }

    @Override
    public void onCancel(Object arguments) {
        LeancloudMessageHandler.getInstance().setFlutterEvents(null);
        AVIMMessageManager.unregisterMessageHandler(AVIMTypedMessage.class, LeancloudMessageHandler.getInstance());
    }
}
