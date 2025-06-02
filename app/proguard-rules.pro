# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class packagename.*

-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

# Keep your data models
-keep class in.pawan.dogsapp.data.model.** { *; }
-keepclassmembers class in.pawan.dogsapp.data.model.** { *; }

# Keep Retrofit services
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep Gson specific rules
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }

# Keep Enum values
-keepclassmembers enum * { *; }

# Retrofit does reflection on generic parameters
-keepattributes Signature
-keepattributes Exceptions


# Retrofit
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn retrofit2.**

# Generic types handling for Retrofit
-keepattributes Signature
-keepattributes InnerClasses
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Keep generic type information
-keep class * extends java.lang.annotation.Annotation { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep response classes
-keep class in.pawan.dogsapp.data.model.** { *; }
-keepclassmembers class in.pawan.dogsapp.data.model.** {
    <init>(...);
    <fields>;
}

# Keep type information for generic types
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation


# Your API Interface
-keep interface in.pawan.dogsapp.data.network.ApiService { *; }

# Your Models
-keep class com.yourcompany.model.** { *; }
-keepnames class com.yourcompany.model.** { *; }
#Gson
-keep class com.google.gson.** { *; }