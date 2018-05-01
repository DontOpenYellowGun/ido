## http://proguard.sourceforge.net/index.html#manual/usage.html
#
#-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
#-verbose
#
## Optimization is turned off by default. Dex does not like code run
## through the ProGuard optimize and preverify steps (and performs some
## of these optimizations on its own).
#-dontoptimize
#-dontpreverify
## Note that if you want to enable optimization, you cannot just
## include optimization flags in your own project configuration file;
## instead you will need to point to the
## "proguard-android-optimize.txt" file instead of this one from your
## project.properties file.
#
#-keepattributes *Annotation*
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#
## For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
## keep setters in Views so that animations can still work.
## see http://proguard.sourceforge.net/manual/examples.html#beans
#-keepclassmembers public class * extends android.view.View {
#   void set*(***);
#   *** get*();
#}
#
## We want to keep methods in Activity that could be used in the XML attribute onClick
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
#
## For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keepclassmembers class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator CREATOR;
#}
#
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
#
## The support library contains references to newer platform versions.
## Dont warn about those in case this app is linking against an older
## platform version.  We know about them, and they are safe.
#-dontwarn android.support.**
#
#
#
#
#
#
#
## Retain generic type information for use by reflection by converters and adapters.
#-keepattributes Signature
## Retain service method parameters.
#-keepclassmembernames,allowobfuscation interface * {
#    @retrofit2.http.* <methods>;
#}
## Ignore annotation used for build tooling.
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#
#
#
#-keep class com.chad.library.adapter.** {
#*;
#}
#-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
#-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
#-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
#     <init>(...);
#}
#
#
#
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}
#
## for DexGuard only
#
#
#
#-dontwarn com.squareup.picasso.**
#
#
#
#
#
#-dontwarn com.yalantis.ucrop**
#-keep class com.yalantis.ucrop** { *; }
#-keep interface com.yalantis.ucrop** { *; }
#
#
#
#-keepattributes *Annotation*
#-keepclassmembers class * {
#    @org.greenrobot.eventbus.Subscribe <methods>;
#}
#-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#
## Only required if you use AsyncExecutor
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}
#
#
#
## ProGuard configurations for Bugtags
#  -keepattributes LineNumberTable,SourceFile
#
#  -keep class com.bugtags.library.** {*;}
#  -dontwarn com.bugtags.library.**
#  -keep class io.bugtags.** {*;}
#  -dontwarn io.bugtags.**
#  -dontwarn org.apache.http.**
#  -dontwarn android.net.http.AndroidHttpClient
#
#  # End Bugtags
#
#
#
#  -keepattributes Exceptions,InnerClasses
#
#  -keepattributes Signature
#
#  # RongCloud SDK
#  -keep class io.rong.** {*;}
#  -keep class * implements io.rong.imlib.model.MessageContent {*;}
#  -dontwarn io.rong.push.**
#  -dontnote com.xiaomi.**
#  -dontnote com.google.android.gms.gcm.**
#  -dontnote io.rong.**
#
#  # VoIP
#  -keep class io.agora.rtc.** {*;}
#
#  # Location
#  -keep class com.amap.api.**{*;}
#  -keep class com.amap.api.services.**{*;}
#
#  # 红包
#  -keep class com.google.gson.** { *; }
#  -keep class com.uuhelper.Application.** {*;}
#  -keep class net.sourceforge.zbar.** { *; }
#  -keep class com.google.android.gms.** { *; }
#  -keep class com.alipay.** {*;}
#  -keep class com.jrmf360.rylib.** {*;}
#
#  -ignorewarnings
#
#
#  -keep public class com.fangao.module_main.support.receiver.ImReceiver
#  -keep public class com.fangao.lib_common.util.RSAUtils2
#
#
#  ### greenDAO 3
#  -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
#  public static java.lang.String TABLENAME;
#  }
#  -keep class **$Properties
#
#  # If you do not use SQLCipher:
#  -dontwarn org.greenrobot.greendao.database.**
#  # If you do not use RxJava:
#  -dontwarn rx.**
#
#  ### greenDAO 2
#  -keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
#  public static java.lang.String TABLENAME;
#  }
#  -keep class **$Properties
#
#
#-keep public class com.alibaba.android.arouter.routes.**{*;}
#-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
#
## 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
#-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
#
## 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
#-keep class * implements com.alibaba.android.arouter.facade.template.IProvider