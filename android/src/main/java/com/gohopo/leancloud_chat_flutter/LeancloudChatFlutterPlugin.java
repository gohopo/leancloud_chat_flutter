package com.gohopo.leancloud_chat_flutter;

import android.app.Activity;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** LeancloudChatFlutterPlugin */
public class LeancloudChatFlutterPlugin implements FlutterPlugin, ActivityAware {
  public static LeancloudChatFlutterPlugin Instance = new LeancloudChatFlutterPlugin();
  public Activity Activity;
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "leancloud_chat_flutter");
    channel.setMethodCallHandler(new LcfMethodHandler());

    final EventChannel eventChannel = new EventChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "leancloud_chat_flutter/events");
    eventChannel.setStreamHandler(new LcfEventHandler());

    final EventChannel messageChannel = new EventChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "leancloud_chat_flutter/messages");
    messageChannel.setStreamHandler(new LcfMessageHandler());
  }
  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    this.Activity = binding.getActivity();
  }
  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }
  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {

  }
  @Override
  public void onDetachedFromActivity() {

  }
}
