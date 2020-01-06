//////////////////////////////////////////
// Activity.js
// Copyright (C) 2020 Selerix Systems Inc.
//
//////////////////////////////////////////
var exec = require('cordova/exec');

var Activity =
{
    getExtras : function(success, error )
    {
        exec(success, error, "Activity", "getExtras", [])
    }
}

module.exports = Activity;
