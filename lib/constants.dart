class ChatMethod {
  static const String GetPlatformVersion = "getPlatformVersion";
  static const String Initialize = "initialize";
  static const String Login = "login";
  static const String Close = "close";
  static const String SendMessage = "sendMessage";
  static const String SetConversationRead = "setConversationRead";
}

class ChatEvent {
  static const String OnMessageReceived = "onMessageReceived";
  static const String OnUnreadMessagesCountUpdated = "onUnreadMessagesCountUpdated";
  static const String OnLastDeliveredAtUpdated = "onLastDeliveredAtUpdated";
  static const String OnLastReadAtUpdated = "onLastReadAtUpdated";
  static const String OnMessageUpdated = "onMessageUpdated";
  static const String OnMessageRecalled = "onMessageRecalled";
}

class ChatCode {
  static const int Success = 0;
  static const int Error = 1;
}

class MessageStatus {
  static const String None = "None"; //未知
  static const String Sending = "Sending"; //发送中
  static const String Sent = "Sent"; //发送成功
  static const String Receipt = "Receipt"; //被接收
  static const String Failed = "Failed"; //失败
}