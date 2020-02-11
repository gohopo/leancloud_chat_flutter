import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:leancloud_chat_flutter/leancloud_chat_flutter.dart';

void main() {
  const MethodChannel channel = MethodChannel('leancloud_chat_flutter');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await LeancloudChatFlutter.platformVersion, '42');
  });
}
