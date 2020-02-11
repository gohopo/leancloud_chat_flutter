#import "LeancloudChatFlutterPlugin.h"
#if __has_include(<leancloud_chat_flutter/leancloud_chat_flutter-Swift.h>)
#import <leancloud_chat_flutter/leancloud_chat_flutter-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "leancloud_chat_flutter-Swift.h"
#endif

@implementation LeancloudChatFlutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftLeancloudChatFlutterPlugin registerWithRegistrar:registrar];
}
@end
