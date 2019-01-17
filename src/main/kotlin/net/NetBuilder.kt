package net

import neural.CoreNeuron
import neural.INeuron
import neural.Perceptor
import neural.SigmoidNeuron
import java.lang.IllegalArgumentException

object NetBuilder {
    val layer1 = 784
    val layer2 = 16
    val layer3 = 10

    fun getTest(): NeuralNet {
        val perceptionLayer = ArrayList<INeuron>(784)
        for (i in 0 until layer1) {
            perceptionLayer.add(Perceptor())
        }
        val hiddenLayer = ArrayList<INeuron>(16)
        for (i in 0 until layer2) {
            hiddenLayer.add(SigmoidNeuron(perceptionLayer))
        }
        val outputLayer = ArrayList<INeuron>(10)
        for (i in 0 until layer3) {
            outputLayer.add(SigmoidNeuron(hiddenLayer))
        }
        return NeuralNet(perceptionLayer as List<Perceptor>, outputLayer)
    }

    fun buildSimpleNetwork(template: IntArray): NeuralNet {
        var perceptionLayer= ArrayList<INeuron>()

        var prevLayer = ArrayList<INeuron>()
        if (template.isEmpty()) {
            throw IllegalArgumentException("Cannot be empty")
        }
        for (layerNr in 0 until template.size) {
            if (layerNr == 0) {
                perceptionLayer = ArrayList(template[layerNr])
                prevLayer = perceptionLayer
                for (i in 0 until template[layerNr]) {
                    perceptionLayer.add(Perceptor())
                }
            } else {
                val hiddenLayer = ArrayList<INeuron>(template[layerNr])
                for (i in 0 until template[layerNr]) {
                    hiddenLayer.add(SigmoidNeuron(prevLayer))
                }
                prevLayer = hiddenLayer
            }
        }
        return NeuralNet(perceptionLayer as List<Perceptor>, prevLayer)
    }
}