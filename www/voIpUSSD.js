

// Empty constructor
function ussd() { }

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
ussd.prototype.show = function (ussdCode, successCallback, errorCallback) {
    var options = {};
    options.ussdCode = ussdCode;
    //options.duration = duration;
    cordova.exec(successCallback, errorCallback, 'VoIpUSSD', 'show', [options]);
}

// Installation constructor that binds voIpUSSD to window
ussd.install = function () {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.voIpUSSD = new ussd();
    return window.plugins.voIpUSSD;
};
cordova.addConstructor(ussd.install);