package com.redrock.learndemo.Dialog_InterceptPattern

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

//这里也可以使用扩展函数
//dp2px
val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp

fun String.show(context: Context, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, time).show()
}

fun Float.sp2px(context: Context) = (this * (context.resources.displayMetrics.scaledDensity) + 0.5f)

fun String.toast(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_LONG).show()
}
