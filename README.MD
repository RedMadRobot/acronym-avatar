<img src="https://github.com/RedMadRobot/acronym-avatar/blob/assets/assets/logo.png" alt="Logo" />

[![Bintray](https://api.bintray.com/packages/redmadrobot-opensource/android/acronym-avatar/images/download.svg)](https://bintray.com/redmadrobot-opensource/android/acronym-avatar/_latestVersion)

<img src="https://github.com/RedMadRobot/acronym-avatar/blob/assets/assets/sample.png" alt="UI sample" width="310"/>

# Description

AcronymAvatar is a dumb simple library to show avatars with acronyms in your application. Should you like to show user's list in your messenger (yet another one...) or just create an unorthodox user profile? This library is the best choice for you.

# Getting started

Just include the library into your project as a Gradle dependency: 
```Groovy
implementation 'com.redmadrobot:acronym-avatar:1.0'
``` 

Then add `AvatarView` into your XML layout:
```XML
    <com.redmadrobot.acronymavatar.AvatarView
        android:id="@+id/user_avatar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```        

And pass full username to the `setText` method:
```Kotlin
user_avatar.setText("Captain Nemo")
```

That's all!


# Capabilities

* Setting text via XML and programmatically
* Using custom placeholders (Drawables only!)
* Configuring zoom out for the acronym

## Setting text via XML and programmatically
Composing acronyms from text works like this: when you pass a single word through the `setText` method or through the `app:text` XML field, the library takes only first two symbols, and makes them an uppercased acronym. In case there are two or more words, the library takes first symbols from first and second words only, and makes them an uppercased acronym. For instance:
* Captain -> CA
* Captain Nemo -> CN
* Nemo, my name forever more -> NM

## Using custom placeholders (Drawables only!)
We like our default placeholder very much, but you won't probably want to use it in real-world applications ;) For this case we augmented our library with a custom placeholder feature. You can set it via XML as following:
```XML
    <com.redmadrobot.acronymavatar.AvatarView
        android:id="@+id/user_avatar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" 
        app:placeholder="@drawable/custom_placeholder" />
```

## Configuring zoom out for the acronym
The library is preconfigured to use optimal text zoom factor, but you can set your own value this way:
```XML
    <com.redmadrobot.acronymavatar.AvatarView
        android:id="@+id/user_avatar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" 
        app:zoom_out="5" />
```
