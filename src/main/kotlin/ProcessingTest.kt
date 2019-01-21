import data.mnist.MnistDataFactory
import data.samples.ImageSample
import net.NetBuilder
import net.NeuralNet
import neural.INeuron
import neural.SigmoidNeuron
import processing.core.PApplet
import java.util.concurrent.TimeUnit

class ProcessingTest : PApplet() {
    val samples = MnistDataFactory.getMnistSamples()
    val it = samples.iterator()
    val net = NetBuilder.buildSimpleNetwork(intArrayOf(784, 16, 16, 10), { x : List<INeuron> -> SigmoidNeuron(x) })

    companion object Factory {
        fun run() {
            var art = ProcessingTest()
            art.setSize(784, 784)
            art.runSketch()
        }
    }

    override fun settings() {
        size(784, 784)
    }

    override fun draw() {
        background(0)
        var x = it.next()
        var i = x.renderAsPImage(this)
        i.resize(784, 784)
        image(i, 0f, 0f)
        xyz(x)
        //delay(500)
    }

    fun xyz(x : ImageSample) {
        var builder = StringBuilder().append("[")
        net.getErrorVector(x.inputVector, x.outputVector).forEach { x ->
            builder.append(" $x,")
        }
        builder.append("]")
        kotlin.io.println(builder.toString())
    }
}