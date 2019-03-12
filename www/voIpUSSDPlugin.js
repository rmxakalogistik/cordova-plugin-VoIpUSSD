// Empty constructor
function VoIpUSSDPlugin() { }

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
VoIpUSSDPlugin.prototype.show = function (ussdCode, successCallback, errorCallback) {
    var options = {};
    options.ussdCode = ussdCode;
    //options.duration = duration;
    cordova.exec(successCallback, errorCallback, 'VoIpUSSDPlugin', 'show', [options]);
}

// Installation constructor that binds VoIpUSSDPlugin to window
VoIpUSSDPlugin.install = function () {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.VoIpUSSDPlugin = new VoIpUSSDPlugin();
    return window.plugins.VoIpUSSDPlugin;
};
cordova.addConstructor(VoIpUSSDPlugin.install);