package com.redrock.learndemo.Dialog_InterceptPattern

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.redrock.learndemo.Dialog_InterceptPattern.ConcreteDialogNode.node0
import com.redrock.learndemo.Dialog_InterceptPattern.ConcreteDialogNode.node1
import com.redrock.learndemo.Dialog_InterceptPattern.ConcreteDialogNode.node2
import com.redrock.learndemo.R

/**
 * using Intercept Pattern to optimize code in many-dialogs situation
 */
class DialogInterceptPatternActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_intercept_pattern)
    }

    fun startDialog(v: View){
        val handler = DialogInterceptChainHandler()
        handler.add(node0(this))
        handler.add(node1(this))
        handler.add(node2(this))
        handler.intercept()


    }
}