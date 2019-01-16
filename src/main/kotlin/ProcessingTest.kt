import data.mnist.MnistDataFactory
import processing.core.PApplet

class ProcessingTest : PApplet() {

    val samples = MnistDataFactory.getMnistSamples()
    val it = samples.iterator()

    public companion object Factory {
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
        var x = it.next().renderAsPImage(this)
        x.resize(784,784)
        image(x, 0f, 0f)
        delay(1000)
    }
}