package com.redrock.learndemo.mviPattern.ui.intent

/**
 * Author by OkAndGreat
 * Date on 2021/11/16 8:32.
 * 包装用户action的意图Intent
 */
sealed class MainIntent {

    object FetchUser : MainIntent()

}