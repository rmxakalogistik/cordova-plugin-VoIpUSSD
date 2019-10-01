# cordova-plugin-VoIpUSSD
A cordova plugin version of VoIpUSSD: https://github.com/romellfudi/VoIpUSSD

## Installation

```
cordova plugin add https://github.com/rmxakalogistik/cordova-plugin-VoIpUSSD.git
```


## Configuration
### On line 26 of *src/android/VoIpUSSD.java* file
* Replace `io.sybox.easyshare` by the name of your Main Package
```
import io.sybox.easyshare.MainActivity; //(io.sybox.easyshare: this must be replaced by the name of your main package)
```
### On *AndroidManifest.xml* file
* Add service:
```
<service android:name="com.ramymokako.plugin.ussd.android.USSDService" android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
    <intent-filter>
           <action android:name="android.accessibilityservice.AccessibilityService" />
    </intent-filter>
    <meta-data android:name="android.accessibilityservice" android:resource="@xml/ussd_service" />
</service>
```
* Add bellow dependencies:
```
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```
### On *res/xml/* folder
* Create empty **ussd_service.xml** file and insert bellow:
```
<?xml version="1.0" encoding="utf-8"?>
<accessibility-service xmlns:android="http://schemas.android.com/apk/res/android"
    android:accessibilityEventTypes
        ="typeWindowStateChanged"
    android:packageNames="com.android.phone"
    android:accessibilityFeedbackType="feedbackGeneric"
    android:accessibilityFlags="flagDefault"
    android:canRetrieveWindowContent="true"
    android:notificationTimeout="0"/>
<!--|typeViewTextChanged-->
```
## Usage
```
window.plugins.voIpUSSD.show('*105#', function (data) {
   console.log('USSD Success: ' + data);
}, function (err) {
   console.log('USSD Erreur: ' + err);
});
```
## Authors

* **Ramy Mokako** - *Initial work* - [Romell Domínguez](https://github.com/romellfudi/VoIpUSSD/#by-romell-dominguez)
