package com.vsanto.notas

import java.io.Serializable

sealed class Type : Serializable {
    object Text : Type()
    object List : Type()
}