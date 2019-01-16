package data.mnist

import data.samples.ImageSample
import mnist.MnistReader
import java.util.*

object MnistDataFactory {
    val LABEL_FILE = "/Users/Mateusz/IdeaProjects/ketchup-training/files/train-labels.idx1-ubyte"
    val IMAGE_FILE = "/Users/Mateusz/IdeaProjects/ketchup-training/files/train-images.idx3-ubyte"

    fun getMnistSamples() : List<ImageSample> {
        val list = LinkedList<ImageSample>()
        val images = MnistReader.getImagesAsVector(IMAGE_FILE)
        val labels = MnistReader.getLabelsAsVector(LABEL_FILE)
        for (i in 0 until labels.size) {
            list.add(ImageSample(28, 28, images[i], labels[i]))
        }
        return list
    }
}