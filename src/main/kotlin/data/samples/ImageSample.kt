package data.samples

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage

class ImageSample(val width: Int,
                  val height: Int,
                  inputVector : DoubleArray,
                  outputVector: DoubleArray) : BaseSample(inputVector, outputVector) {


/*    fun renderAsString(): String {
        val sb = StringBuilder().appendln("Label: $outputVector")
        for (i in inputVector.indices) {

        }
        for (row in image.indices) {
            sb.append("|")
            for (col in 0 until image[row].size) {
                    when (image[row][col]) {
                        0          -> sb.append(" ")
                        in 1..85   -> sb.append(".")
                        in 86..171 -> sb.append("x")
                        else       -> sb.append("X")
                    }
            }
            sb.append("|\n")
        }
        return sb.toString()
    }*/

    fun renderAsPImage(app: PApplet): PImage {
        val image = app.createImage(width, height, PConstants.RGB)
        image.loadPixels()
        for (i in image.pixels.indices) {
            println(image.pixels.count())
            println(inputVector.count())
            image.pixels[i] = app.color(inputVector[i].toFloat())
        }
        image.updatePixels()
        return image
    }
}