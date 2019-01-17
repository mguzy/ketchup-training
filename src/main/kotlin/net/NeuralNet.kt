package net

import neural.INeuron
import neural.Perceptor
import java.lang.IllegalArgumentException

class NeuralNet(private val inputLayer : List<Perceptor>,
                private val outputLayer : List<INeuron>) {

    fun process(inputVector : DoubleArray) : DoubleArray {
        if(inputVector.size != inputVector.size) {
            throw IllegalArgumentException("size not correct")
        }
        for (i in inputVector.indices) {
            inputLayer[i].update(inputVector[i])
        }
        return outputLayer.map { n -> n.getValue() }.toDoubleArray()
    }
}
