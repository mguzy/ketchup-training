package neural

interface INeuron {

    var bias: Double
    val activationFun: (Double) -> Double
    val weights: ArrayList<Double>
    val parents: ArrayList<INeuron>

    fun getValue(): Double = activationFun(getWeightedParentValue() + bias)

    fun getWeightedParentValue(): Double {
        if (weights.size != parents.size) {
            throw IllegalStateException("FUck!")
        }
        var result = 0.0
        for ((index, weight) in weights.withIndex()) {
            result += weight * parents[index].getValue()
        }
        return result
    }
}