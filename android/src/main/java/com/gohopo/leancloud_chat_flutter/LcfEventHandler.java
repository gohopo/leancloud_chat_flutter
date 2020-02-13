package com.gohopo.leancloud_chat_flutter;

import com.gohopo.leancloud_chat_flutter.common.LeancloudClientHandler;
import com.gohopo.leancloud_chat_flutter.common.LeancloudConversationHandler;

import io.flutter.plugin.common.EventChannel;

public class LcfEventHandler implements EventChannel.StreamHandler {
    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        LeancloudConversationHandler.getInstance().setFlutterEvents(events);
        LeancloudClientHandler.getInstance().setFlutterEvents(events);
    }

    @Override
    public void onCancel(Object arguments) {
        LeancloudConversationHandler.getInstance().setFlutterEvents(null);
        LeancloudClientHandler.getInstance().setFlutterEvents(null);
    }
}
