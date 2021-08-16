package com.irfanirawansukirman.core.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics

object AppLogger {

    fun breadCrumbLog(
        className: String,
        functionName: String,
        email: String,
        param: String
    ) {
        FirebaseCrashlytics.getInstance()
            .log("Class: $className * Function: $functionName * User: $email * Param: $param")
    }
}