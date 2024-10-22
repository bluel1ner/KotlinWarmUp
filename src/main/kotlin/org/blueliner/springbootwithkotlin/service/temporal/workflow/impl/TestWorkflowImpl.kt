package org.blueliner.springbootwithkotlin.service.temporal.workflow.impl

import org.blueliner.springbootwithkotlin.service.temporal.workflow.NotifyUserAccounts

class NotifyUserAccountsImpl : NotifyUserAccounts {
    override fun testWorkFlowMethod(str: String): Int {
        return 1;
    }
}