package com.urve.data

import com.urve.remote.listener.RegisterListener
import com.urve.remote.task.TaskRegister

class VerifyRepository {

    private var taskRegister: TaskRegister? = null

    init {
        taskRegister = TaskRegister()
    }

    fun getRegister(registerListener: RegisterListener) {
        taskRegister?.getRegister(registerListener)
    }

    fun removeListener() {
        taskRegister?.removeListener()
    }
}