package neural

class InputNeuron : INeuron {

    private var inputValue : Double

    init {
        inputValue = 0.0
    }

    fun input(value_ : Double) {
        inputValue = value_
    }

    override fun getValue() : Double {
        return inputValue
    }
}