package com.diffy.calculator

data class CalculatorState(
    val number: String = "",
    val number2: String = "",
    val operations: CalculatorOperations? = null
)
