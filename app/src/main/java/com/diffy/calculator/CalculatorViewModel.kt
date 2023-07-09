package com.diffy.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(actions: CalculatorActions) {
        when(actions) {
            is CalculatorActions.Number -> enterNumber(actions.nuimber)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Opertaions -> enterOperations(actions.operations)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {

        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 =  state.number2.dropLast(1)
            )
            state.operations != null -> state = state.copy(
                operations = null
            )
            state.number.isNotBlank() -> state = state.copy(
                number = state.number.dropLast(1)
            )
        }

    }

    private fun performCalculation() {
        val num1 = state.number.toDoubleOrNull()
        val num2 = state.number2.toDoubleOrNull()

        if ( num1 != null && num2 != null ) {
            val result = when (state.operations) {
                is CalculatorOperations.Add -> num1 + num2
                is CalculatorOperations.Substract -> num1 + num2
                is CalculatorOperations.Multiply -> num1 * num2
                is CalculatorOperations.Divide -> num1 / num2
                else -> null
            }

            System.out.println(result)

            state = state.copy(
                number = result.toString().take(12),
                number2 = "",
                operations = null
            )
        }
    }

    private fun enterOperations(operations: CalculatorOperations) {

        if (state.number.isNotBlank()) {
            state= state.copy(operations = operations)
        }
    }

    private fun enterDecimal() {
        if (state.operations == null && !state.number.contains(".")
            && state.number.isNotBlank()
        ){
            state = state.copy( number = state.number + "." )
            return
        }

        if ( !state.number2.contains(".")
            && state.number2.isNotBlank()
        ){
            state = state.copy( number2 = state.number2 + "." )
        }
    }

    private fun enterNumber(nuimber: Int) {
        if (state.operations == null) {
            if (state.number.length >= MAX_NUM_LENG) {
                return
            }
            state = state.copy(
                number = state.number + nuimber
            )
            return
        }

        if (state.number2.length >= MAX_NUM_LENG) {
            return
        }
        state = state.copy(
            number2 = state.number2 + nuimber
        )
    }

    companion object {
        private const val MAX_NUM_LENG = 8
    }
}