Core Android [![](https://jitpack.io/v/fleury-digital/core-android.svg)](https://jitpack.io/#fleury-digital/core-android) [![Build Status](https://app.bitrise.io/app/4cc96a0325722225/status.svg?token=dwvSHydeoEaj-oLKS5fXlA&branch=dev)](https://app.bitrise.io/app/4cc96a0325722225)
==
Helpers for everyone 

Requirements
==
- minSdkVersion **21**
- targetSdkVersion **29**

Getting started
==
Into your top level build.gradle:

    allprojects {
        repositories {
            mavenLocal()
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }

Into your project level build.gradle:

    dependencies {
        implementation 'com.github.fleury-digital:core-android:v1.0.0'
    }
    
ref: https://jitpack.io/#fleury-digital/core-android

Usage
==

### Connectivity

Connection verification:

    ConnectivityHelper.on(context)

### String

Encode to Base64:

    myString.encodeToBase64String()
    
### Date

Changelog
==
## 1.0.0

### Major

- [x] Estruturação da biblioteca

### Minor

### Bugfix

Questions
==
Questions regarding core-android can be asked on [issues](https://github.com/fleury-digital/core-android/issues).

Developed By
==

- [Leandro Barral](https://github.com/leandrobarral)
- [Pierry Borges](https://github.com/pierry)
- [Ricardo Sousa](https://github.com/ricardorsdev)

License
==

[MIT](https://opensource.org/licenses/MIT)
