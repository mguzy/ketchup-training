package neural

import kotlin.random.Random

class SigmoidNeuron(override val parents : List<INeuron>) : CoreNeuron() {

    override var bias : Double = Random.nextDouble()
    override val activationFun : (Double) -> Double = { x -> (1 / (1 + Math.pow(Math.E, -x))) }
    override val weights = DoubleArray(parents.size, {Random.nextDouble()})

    override fun getValue() : Double {
        if (weights.size != parents.size) {
            throw IllegalStateException("FUck!")
        }
        var result = 0.0
        for ((index, weight) in weights.withIndex()) {
            result += weight * parents[index].getValue()
        }
        return activationFun(result)
    }
}