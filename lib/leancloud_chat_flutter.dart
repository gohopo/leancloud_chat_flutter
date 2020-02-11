import 'dart:async';

import 'package:flutter/services.dart';

class LeancloudChatFlutter {
  static const MethodChannel _channel =
      const MethodChannel('leancloud_chat_flutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
