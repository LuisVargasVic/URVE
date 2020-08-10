package com.urve.remote.listener

import com.urve.domain.Register


interface RegisterListener {
    fun preExecute()
    fun postExecute(register: Register)
    fun errorMessage(message: String?)
    fun session()
}