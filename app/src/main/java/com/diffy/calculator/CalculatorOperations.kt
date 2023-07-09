package com.diffy.calculator

sealed class CalculatorOperations (val symbol: String){
    object Add: CalculatorOperations(symbol = "+")
    object Substract: CalculatorOperations(symbol = "-")
    object Divide: CalculatorOperations(symbol = "/")
    object Multiply: CalculatorOperations(symbol = "*")
}
