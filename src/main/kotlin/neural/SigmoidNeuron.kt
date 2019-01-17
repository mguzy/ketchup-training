package neural

import kotlin.random.Random

class SigmoidNeuron(override val parents: ArrayList<INeuron>) : CoreNeuron() {

    override fun getValue(): Double {
        if (weights.size != parents.size) {
            throw IllegalStateException("FUck!")
        }
        var result = 0.0
        for ((index, weight) in weights.withIndex()) {
            result += weight * parents[index].getValue()
        }
        return activationFun(result)
    }

    override var bias: Double = (Random.nextDouble() - 0.5) * 100
    override val activationFun: (Double) -> Double = { x -> (1 / (1 + Math.pow(Math.E, -x))) }
    override val weights = DoubleArray(parents.size, { (Random.nextDouble() - 0.5) * 100 })
}