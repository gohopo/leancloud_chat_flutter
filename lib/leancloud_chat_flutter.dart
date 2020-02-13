import 'dart:async';

import 'package:flutter/services.dart';
import './constants.dart';

export './constants.dart';

class LeancloudChatFlutter {
  static final LeancloudChatFlutter instance = new LeancloudChatFlutter();
  static const MethodChannel _channel = const MethodChannel('leancloud_chat_flutter');
  static const EventChannel _messageChannel = const EventChannel('leancloud_chat_flutter/messages');
  static const EventChannel _eventChannel = const EventChannel("leancloud_chat_flutter/events");
  static bool _isInitialized = false;
  StreamController _messageCtrl;
  StreamController _eventCtrl;
  static String _clientId;

  void _initialize(){
    _messageCtrl = StreamController.broadcast();
    _eventCtrl = StreamController.broadcast();

    _messageChannel.receiveBroadcastStream().listen((data){
      String event = data['event'];
      var conversation = data['conversation'];
      var message = data['message'];
      _messageCtrl.add({'event':event,'conversation':conversation,'message':message});
    });
    _eventChannel.receiveBroadcastStream().listen((data){
      String event = data['event'];
      var conversation = data['conversation'];
      var message = data['message'];
      _eventCtrl.add({'event':event,'conversation':conversation,'message':message});
    });
    _isInitialized = true;
  }
  Stream handleMessages() {
    return _messageCtrl.stream;
  }
  Stream handleEvents() {
    return _eventCtrl.stream;
  }
  static Future<String> getClientId() async {
    return _clientId;
  }
  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod(ChatMethod.GetPlatformVersion);
    return version;
  }
  static Future<Null> initialize(String appId, String appKey) async {
    if (_isInitialized) return;
    _channel.invokeMethod(ChatMethod.Initialize, {'appId': appId, 'appKey': appKey});
    LeancloudChatFlutter.instance._initialize();
  }
  static Future<ChatResponse> login(String userId) async {
    var result = await _channel.invokeMethod(ChatMethod.Login, {'userId': userId});
    var response = new ChatResponse.fromJson(result);
    if(ChatCode.Success == response.code){
      _clientId = response.result;
    }
    return response;
  }
  static Future<ChatResponse> close() async {
    var result = await _channel.invokeMethod(ChatMethod.Close);
    return new ChatResponse.fromJson(result);
  }
  static Future<ChatResponse> sendMessage(String conversationId,String message) async {
    var result = await _channel.invokeMethod(ChatMethod.SendMessage,{'conversationId':conversationId,'message':message});
    return new ChatResponse.fromJson(result);
  }
  static Future<ChatResponse> setConversationRead(String conversationId) async {
    var result = await _channel.invokeMethod(ChatMethod.SetConversationRead);
    return new ChatResponse.fromJson(result);
  }
}

class ChatResponse {
  int code;
  dynamic result;
  String message;
  ChatResponse(this.code, this.result, this.message);
  ChatResponse.fromJson(Map data) : this(data['code'],data['result'],data['message']);
}