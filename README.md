[![](https://jitpack.io/v/fleury-digital/resultados-de-exames-android.svg)](https://jitpack.io/private#fleury-digital/core)

# Core Android

> Biblioteca de core das aplicações

## Requirements

- minSdkVersion **21**
- targetSdkVersion **29**


## Dependencies

Você precisa instalar as seguintes dependências


## Instalation

```groovy
//build.gradle (project level)
buildscript {
    // ...
    ext {
      // ...
      lib_version = '1.x.x'
    }
    // ...
}
```

```groovy
//build.gradle (app level)

implementation 'com.github.fleury-digital:core:$rootProject.ext.common_lib_version'
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


Em seu *Component*

```kotlin
@Component(
  modules = [
    //...
    ResultadosDeExamesModule::class
  ]
)
interface AppComponent {
	//... 
}
```

## Usage

A maneira mais fácil de se utilizar é com o *autoFetch*, segue exemplo de implementação básica.

Activity.kt

```kotlin
package exemplo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity)

      val idPaciente = "6165961"
      ListaFichasActivity.start(this, idPaciente)
    }
}
```

_Para mais informaçoes de uso e exemplos, por favor entre em nossa [Wiki][wiki]._

## Release History

0.0.1

- [x] Estruturação da biblioteca

## Time

Leandro Barral – k2.barral@grupofleury.com.br

[https://github.com/leandrobarral](https://github.com/leandrobarral)
