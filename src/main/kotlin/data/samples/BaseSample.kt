package data.samples

open class BaseSample(val inputVector : DoubleArray,
                      val outputVector : DoubleArray) {

    val inputDimensions = inputVector.size
    val outputDimensions = outputVector.size
}