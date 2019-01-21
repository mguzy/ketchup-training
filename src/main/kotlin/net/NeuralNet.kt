package net

import neural.CoreNeuron
import neural.INeuron
import neural.InputNeuron
import java.lang.IllegalArgumentException

class NeuralNet(private val inputLayer : List<InputNeuron>,
                private val outputLayer : List<INeuron>) {

    init {
        if (outputLayer == inputLayer) {
            throw IllegalArgumentException("OutputLayer cannot be at the same time InputLayer")
        }
        if (inputLayer.isEmpty()) {
            throw IllegalArgumentException("InputLayer cannot be empty.")
        }
        if (outputLayer.isEmpty()) {
            throw IllegalArgumentException("OutputLayer cannot be empty.")
        }
        inputLayer.forEach { neuron ->
            if (neuron !is InputNeuron) {
                throw IllegalArgumentException("InputLayer must contain only InputNeurons")
            }
        }
        outputLayer.forEach { neuron ->
            if (neuron !is CoreNeuron) {
                throw IllegalArgumentException("OutputLayer must contain only CoreNeurons")
            }
        }
    }

    fun process(inputVector : DoubleArray) : DoubleArray {
        validate(inputVector)
        for (i in inputVector.indices) {
            inputLayer[i].input(inputVector[i])
        }
        return outputLayer.map { n -> n.getValue() }.toDoubleArray()
    }

    fun getErrorVector(inputVector : DoubleArray, expectedOutputVector : DoubleArray) : DoubleArray {
        validate2(expectedOutputVector)
        val result = process(inputVector)
        val errorVector = DoubleArray(result.size)
        for (i in 0 until result.size) {
            errorVector[i] = Math.pow((result[i] - expectedOutputVector[i]), 2.0)
        }
        return errorVector
    }

    private fun validate(inputVector : DoubleArray) {
        if (inputVector.size != inputLayer.size) {
            throw IllegalArgumentException(
                """Size of inputVector is not compatible with this NeuralNet.
                                              inputLayer is of size: ${inputLayer.size},
                                              but inputVector is of size: ${inputVector.size}"""
            )
        }
    }

    private fun validate2(expectedOutputVector : DoubleArray) {
        if (expectedOutputVector.size != outputLayer.size) {
            throw IllegalArgumentException(
                """Size of expectedOutputVector is not compatible with this NeuralNet.
                                              outputLayer is of size: ${inputLayer.size},
                                              but expectedOutputVector is of size: ${expectedOutputVector.size}"""
            )
        }
    }
}
