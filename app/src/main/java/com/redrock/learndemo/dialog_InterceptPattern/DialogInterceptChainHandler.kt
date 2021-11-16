package com.redrock.learndemo.dialog_InterceptPattern

/**
 * Author by OkAndGreat，Date on 2021/8/27.
 *
 */
class DialogInterceptChainHandler {
    var firstNode: DialogInterceptChainNode? = null

    //once invoked,start intercept
    fun intercept() {
        firstNode?.intercept()
    }

    //Every child node represents a dialog
    fun add(node: DialogInterceptChainNode) {
        if (firstNode == null) {
            firstNode = node
            return
        }

        var tempNode = firstNode

        //quit when the node is the last node in the chain
        while (tempNode!!.next != null) {
            tempNode = tempNode.next
        }

        tempNode.next = node
    }

}