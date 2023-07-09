package com.diffy.calculator

sealed class CalculatorActions {
    data class Number (val nuimber: Int): CalculatorActions()
    object Clear: CalculatorActions()
    object Delete: CalculatorActions()
    object Decimal: CalculatorActions()
    object Calculate: CalculatorActions()
    data class Opertaions(val operations: CalculatorOperations): CalculatorActions()
}