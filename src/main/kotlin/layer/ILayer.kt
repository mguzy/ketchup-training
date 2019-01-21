package layer

import org.nd4j.linalg.api.ndarray.INDArray

interface ILayer {
    val amountOfNeurons : Int
    fun value() : INDArray
}