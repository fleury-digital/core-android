-keepattributes Signature
-keepattributes *Annotation*
-keepattributes Annotation
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.shockwave.**
-keep class okhttp3.** { *; }
-dontwarn javax.annotation.**
-keepclassmembers enum * { *; }
-keep class org.jsoup.**
-keep public class org.jsoup.** { public *; }
-keepnames class org.jsoup.nodes.Entities
-keeppackagenames org.jsoup.nodes
-keep class org.spongycastle.** { *; }
-dontwarn org.spongycastle.**
-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**
-keep class javax.xml.crypto.XMLStructure
-dontwarn javax.xml.crypto.XMLStructure
-keepclassmembers class * {
    @com.livinglifetechway.quickpermissions.annotations.* <methods>;
}
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
-keep class com.github.mikephil.charting.** { *; }
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Exceptions
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontnote okhttp3.**

# Okio
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }