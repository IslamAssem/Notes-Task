package com.ibnsina.utils.recyclerview

interface OnSelectedItemChange <T>{
    fun onSelectedItemChange(oldIndex: Int, oldSelectedItem: T?, newIndex: Int, newSelectedItem: T?)

}