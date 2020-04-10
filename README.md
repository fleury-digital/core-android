[![](https://jitpack.io/v/fleury-digital/resultados-de-exames-android.svg)](https://jitpack.io/private#fleury-digital/core)

# Core Android

> Biblioteca core das aplicações

## Requirements

- minSdkVersion **21**
- targetSdkVersion **29**


## Dependencies

Você precisa instalar as seguintes dependências

```groovy
//build.gradle (project level)
buildscript {
    // ...
    ext {
      // ...
        kotlin_version = '1.3.70'
        dagger_version = '2.26'
        retrofit_version = '2.7.2'
        databinding_version = '3.6.1'
        room_version = '2.2.5'
        arch_version = '1.1.1'
        gson_version = '2.8.6'
        okhttp_version = '4.4.0'
        lifecycle_version = '2.2.0'
        daggermock_version = '0.8.4'
        google_material_version = '1.1.0'
        androidx_recyclerview = '1.1.0'
        androidx_preference = '1.1.0'
        androidx_legacy = '1.0.0'
        androidx_fragment = '1.2.2'
        androidx_core = '1.2.0'
        androidx_constraintlayout = '1.1.3'
        androidx_appcompat = '1.1.0'
        timber_version = '4.7.1'
        joda_version = '2.10.2'
        stetho_version = '1.5.0'
        lottie_version = '3.3.1'
        itextpdf_version = '5.5.10'
        chuck_http_inspector = '1.1.0'
        leakcanary_version = '2.1'
    }
    // ...
}
```


## Instalation

```groovy
//build.gradle (project level)
buildscript {
    // ...
    ext {
      // ...
      core_lib_version = '1.x.x'
    }
    // ...
}
```

```groovy
//build.gradle (app level)

implementation 'com.github.fleury-digital:core:$rootProject.ext.core_lib_version'
```

### Proguard rules

```groovy
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
```

## Configurations

Em seu *di/modules*

```kotlin
@Module(
    includes = [
        CoreModule::class,
        SensediaModule::class,
    ]
)
```

## Usage

Essa biblioteca é utilizada em conjunto com as outras bibliotecas. 
Essa lib já contém as seguintes bibliotecas:
[https://github.com/google/dagger](dagger2)
[https://github.com/square/okhttp](okhttp3)
[https://github.com/square/retrofit](retrofit2)
[https://github.com/airbnb/lottie-android](lottie)
[https://github.com/facebook/stetho](stetho-okhttp)
[https://github.com/jgilfelt/chuck](chuck)
[https://github.com/JakeWharton/timber](timber) 
[https://github.com/itext/itextpdf](itextpdf)
[https://github.com/square/leakcanary](leakcanary)
[https://github.com/JodaOrg/joda-time](joda)
[https://github.com/google/gson](gson)

_Para mais informaçoes de uso e exemplos, por favor entre em nossa [Wiki][wiki]._

## Release History

0.0.1

- [x] Estruturação da biblioteca

## Time

Leandro Barral – k2.barral@grupofleury.com.br

[https://github.com/leandrobarral](https://github.com/leandrobarral)
