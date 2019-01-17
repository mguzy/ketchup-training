package neural

class Perceptor : INeuron {

    private var observedValue : Double

    init {
        observedValue = 0.0
    }

    fun update(value_ : Double) {
        observedValue = value_
    }

    override fun getValue(): Double {
        return observedValue
    }
}