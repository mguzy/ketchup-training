package layer

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.api.ops.BaseTransformOp
import org.nd4j.linalg.api.ops.impl.transforms.Sigmoid
import org.nd4j.linalg.factory.Nd4j

class Layer(override val amountOfNeurons : Int,
            val prevLayer : ILayer) : ILayer {

    //val activationFun : (INDArray) -> BaseTransformOp = Sigmoid::new

    var weights = Nd4j.rand(amountOfNeurons, prevLayer.amountOfNeurons)

    var biases = Nd4j.rand(amountOfNeurons, 1)

    fun weightedValue() : INDArray {
        return weights.mmul(prevLayer.value()).add(biases)
    }
    override fun value() : INDArray {
        return Nd4j.getExecutioner().execAndReturn(Sigmoid(weightedValue()))
    }
}