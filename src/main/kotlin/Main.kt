import layer.InputLayer
import layer.Layer

fun main(args: Array<String>) {
    //ProcessingTest.run()
val input = InputLayer(784)
    var output = Layer(10, input)
    print(output.weightedValue())
}

