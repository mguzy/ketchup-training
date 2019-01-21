package neural

abstract class CoreNeuron : INeuron {
    abstract var bias : Double
    abstract val activationFun : (Double) -> Double
    abstract val weights : DoubleArray
    abstract val parents : List<INeuron>
}