package net

import neural.INeuron
import neural.InputNeuron
import java.lang.IllegalArgumentException

object NetBuilder {

    fun buildSimpleNetwork(template : IntArray, neuron : (List<INeuron>) -> INeuron) : NeuralNet {
        validateTemplate(template)
        val perceptionLayer = ArrayList<INeuron>(template[0])
        var currentLayer = perceptionLayer
        for (i in 0 until template[0]) {
            perceptionLayer.add(InputNeuron())
        }
        for (layerNr in 1 until template.size) {
            val hiddenLayer = ArrayList<INeuron>(template[layerNr])
            for (i in 0 until template[layerNr]) {
                hiddenLayer.add(neuron(currentLayer))
            }
            currentLayer = hiddenLayer
        }
        return NeuralNet(perceptionLayer as List<InputNeuron>, currentLayer)
    }

    private fun validateTemplate(template : IntArray) {
        if (template.isEmpty()) {
            throw IllegalArgumentException("Cannot be empty")
        }
        if (template.size == 1) {
            throw IllegalArgumentException("cannot be of size 1")
        }
    }
}