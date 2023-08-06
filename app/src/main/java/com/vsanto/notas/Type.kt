package com.vsanto.notas

sealed class Type {
    object Text: Type()
    object List: Type()
}