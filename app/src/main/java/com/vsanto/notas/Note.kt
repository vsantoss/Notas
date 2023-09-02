package com.vsanto.notas

import java.io.Serializable
import java.util.Date

data class Note(var title: String, val type: Type, val createDate: Date, var updateDate: Date) :
    Serializable