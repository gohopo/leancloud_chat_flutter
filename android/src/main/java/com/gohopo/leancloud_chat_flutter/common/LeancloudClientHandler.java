package com.gohopo.leancloud_chat_flutter.common;

import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMClientEventHandler;
import io.flutter.plugin.common.EventChannel;

public class LeancloudClientHandler extends AVIMClientEventHandler {
    private static LeancloudClientHandler _instance;
    private EventChannel.EventSink events;

    public static synchronized LeancloudClientHandler getInstance() {
        if (null == _instance) {
            _instance = new LeancloudClientHandler();
        }
        return _instance;
    }
    public void setFlutterEvents(EventChannel.EventSink events) {
        this.events = events;
    }

    @Override
    public void onConnectionPaused(AVIMClient client) {

    }
    @Override
    public void onConnectionResume(AVIMClient client) {

    }
    @Override
    public void onClientOffline(AVIMClient client, int code) {

    }
}
