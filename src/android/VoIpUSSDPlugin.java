package com.ramymokako.cordova.plugin.VoIpUSSDPlugin;
// The native Toast API
// import android.widget.Toast;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import com.romellfudi.ussdlibrary.USSDApi;
import com.romellfudi.ussdlibrary.USSDController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class VoIpUSSDPlugin extends CordovaPlugin {
  //private static final String DURATION_LONG = "long";

private HashMap<String, HashSet<String>> map;
    private USSDApi ussdApi;

    private Context context;

    String result;

private VoIpUSSDPlugin(){}

  @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("show")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
      }


        map = new HashMap<>();
        map.put("KEY_LOGIN", new HashSet<>(Arrays.asList("espere", "waiting", "loading", "esperando")));
        map.put("KEY_ERROR", new HashSet<>(Arrays.asList("problema", "problem", "error", "null")));
        context = this.cordova.getActivity().getApplicationContext();
        //ussdApi = USSDController.getInstance(context);

        String ussdCode;
        try {
            JSONObject options = args.getJSONObject(0);
            ussdCode = options.getString("ussdCode");
        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }

        String returnresponse = executeSimpleUssd(ussdCode, context);

        // Send a positive result to the callbackContext
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnresponse );
        callbackContext.sendPluginResult(pluginResult);
        return true;


      //String message;
      //String duration;
      //try {
      //  JSONObject options = args.getJSONObject(0);
      //  message = options.getString("message");
      //  duration = options.getString("duration");
      //} catch (JSONException e) {
      //  callbackContext.error("Error encountered: " + e.getMessage());
      //  return false;
     // }
      //// Create the toast
      //Toast toast = Toast.makeText(cordova.getActivity(), message,
      //  DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
      // Display toast
      //toast.show();
      // Send a positive result to the callbackContext
      //PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
      //callbackContext.sendPluginResult(pluginResult);
      //return true;
  }

private String executeSimpleUssd(String phone, Context _context){
        String phoneNumber = phone;
        ussdApi = USSDController.getInstance(_context);
        result = "";
        ussdApi.callUSSDInvoke(phoneNumber, map, new USSDController.CallbackInvoke() {
            @Override
            public void responseInvoke(String message) {
                Log.d("APP", message);
                result += "\n-\n" + message;
                // first option list - select option 1
                ussdApi.send("1", new USSDController.CallbackMessage() {
                    @Override
                    public void responseMessage(String message) {
                        Log.d("APP", message);
                        result += "\n-\n" + message;
                        // second option list - select option 1
                        ussdApi.send("1", new USSDController.CallbackMessage() {
                            @Override
                            public void responseMessage(String message) {
                                Log.d("APP", message);
                                result += "\n-\n" + message;
                            }
                        });
                    }
                });
            }

            @Override
            public void over(String message) {
                Log.d("APP", message);
                result += "\n-\n" + message;
            }
        });
        return result;
    }

}