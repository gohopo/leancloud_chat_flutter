package com.gohopo.leancloud_chat_flutter.utils;

public class Constants {
    public static final String Method_getPlatformVersion = "getPlatformVersion";
    public static final String Method_initialize = "initialize";
    public static final String Method_login = "login";
    public static final String Method_close = "close";
    public static final String Method_sendMessage = "sendMessage";
    public static final String Method_setConversationRead = "setConversationRead";

    public static final String Event_onMessageReceived = "onMessageReceived";
    public static final String Event_onUnreadMessagesCountUpdated = "onUnreadMessagesCountUpdated";
    public static final String Event_onLastDeliveredAtUpdated = "onLastDeliveredAtUpdated";
    public static final String Event_onLastReadAtUpdated = "onLastReadAtUpdated";
    public static final String Event_onMessageUpdated = "onMessageUpdated";
    public static final String Event_onMessageRecalled = "onMessageRecalled";

    public static final int Code_success = 0;
    public static final int Code_error = 1;
}
