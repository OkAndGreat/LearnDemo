package com.redrock.learndemo.dialog_InterceptPattern.ConcreteDialogNode

import android.content.Context
import com.redrock.learndemo.dialog_InterceptPattern.DialogBuilder
import com.redrock.learndemo.dialog_InterceptPattern.DialogInterceptChainNode
import com.redrock.learndemo.dialog_InterceptPattern.dp
import com.redrock.learndemo.dialog_InterceptPattern.toast
import com.redrock.learndemo.R

/**
 * Author by OkAndGreat，Date on 2021/8/27.
 *
 */
class node0(context: Context) :DialogInterceptChainNode(context) {

    override fun intercept() {
//        val builder = android.app.AlertDialog.Builder(context)
//        val inflater = LayoutInflater.from(context)
//
//        val view = inflater.inflate(R.layout.mine_dialog_goods_detail, null)
//        val sureBtn = view.findViewById(R.id.mine_btn_goods_detail_sure) as Button
//        val cancelBtn = view.findViewById(R.id.mine_btn_goods_detail_cancel) as Button
//
//        val dialog = builder.create()
//        dialog.window?.setWindowAnimations(R.style.mine_dialog_anim)
//        dialog.show()
//        val attributes = dialog.window?.attributes
//        attributes?.width = 300.dp.toInt()
//        attributes?.height = 178.dp.toInt()
//        dialog.window?.attributes = attributes
//        //make sure that the res view background will work right
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window?.setContentView(view)
//
//        sureBtn.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(context,"We can do sth there and then past the event to the next node",Toast.LENGTH_LONG).show()
//            super.intercept()
//        }
//        cancelBtn.setOnClickListener {
//            //if cancel,the node need not give the event to the next
//            dialog.dismiss()
//        }

        DialogBuilder(context)
            .buildDialog(R.layout.mine_dialog_goods_detail,300.dp.toInt(),178.dp.toInt())
            .setWindowAnim(R.style.mine_dialog_anim)
            .setCancelEvent(R.id.mine_btn_goods_detail_cancel)
            .setClickEvent(R.id.mine_btn_goods_detail_sure) { "123".toast(context) }


    }
}