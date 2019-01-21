package layer

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j

class InputLayer(override val amountOfNeurons : Int) : ILayer {
    override fun value() : INDArray {
        return Nd4j.rand(amountOfNeurons, 1)
    }
}