package com.redrock.learndemo.mviPattern.ui.state

import com.redrock.learndemo.mviPattern.bean.User

/**
 * Author by OkAndGreat
 * Date on 2021/11/16 8:36.
 * UI状态 用户行为改变UI状态并对UI状态的改变进行感知从而更新UI状态
 * Sealed class（密封类） 是一个有特定数量子类的类
 * 在密封类中，同一个类可以拥有几个对象
 * Sealed class（密封类）的所有子类都必须与密封类在同一文件中
 * Sealed class（密封类）没有构造函数，不可以直接实例化，只能实例化内部的子类
 */
sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}
