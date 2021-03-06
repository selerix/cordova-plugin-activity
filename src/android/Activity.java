/*
 Copyright 2020 Selerix Systems Inc.

 Licensed under MIT.

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

package com.selerix.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.content.Intent;

@TargetApi(19)
public class Activity extends CordovaPlugin {

    private static final String LOG_TAG = "Activity";
    private static final String MESSAGE_TASK = "Cordova Android Activity.getExtras() called.";

    private Bundle extras;

    @Override
    public void pluginInitialize() {
        extras = this.cordova.getActivity().getIntent().getExtras();
    }

    @Override
    public void onPause(boolean multitasking) {
        extras = null;
        
        super.onPause(multitasking);
    }

    @Override
    public void onNewIntent(Intent intent){
        Bundle options = intent.getExtras();
        if(options != extras)
            extras = options;
        else
            extras = null;

        super.onNewIntent(intent);
    }

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
        if (action.equals("getExtras")) {

            LOG.d(LOG_TAG, MESSAGE_TASK);
            
            if(extras != null) {
                callbackContext.success(getJsonExtras(extras));
            }
            else {
                callbackContext.error("Application started without options, or something going wrong.");
            }                        
        } else {
            return false;
        }
        return true;
    }

    private JSONObject getJsonExtras(Bundle options) {
        try {
            if (options != null) {
                JSONObject r = new JSONObject();
                for (String key : options.keySet()) {
                    Object value = options.get(key);
                    r.put(key, value);
                }
                return r;
            }
        } catch (JSONException ex){
            LOG.d(LOG_TAG, ex.toString());
            return null;
        }
        return null;
    }
}
