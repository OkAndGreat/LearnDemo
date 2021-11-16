package com.redrock.learndemo.dialog_InterceptPattern

import android.content.Context

/**
 * Author by OkAndGreat，Date on 2021/8/27.
 *
 */
abstract class DialogInterceptChainNode(val context: Context) {

    var next:DialogInterceptChainNode? = null

    //child dialog will intercept on their own
    open fun intercept() {
        next?.intercept()
    }
}