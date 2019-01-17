package mnist

import java.io.ByteArrayOutputStream
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.util.*

//https://github.com/andyfangdz/mnist-reader/blob/master/src/main/java/mnist/MnistReader.java
object MnistReader {
    val LABEL_FILE_MAGIC_NUMBER = 2049
    val IMAGE_FILE_MAGIC_NUMBER = 2051

    fun getImagesAsVector(infile: String): List<DoubleArray> {
        val bb = loadFileToByteBuffer(infile)
        assertMagicNumber(IMAGE_FILE_MAGIC_NUMBER, bb.getInt())
        val numImages = bb.int
        val numRows = bb.int
        val numColumns = bb.int
        val images = LinkedList<DoubleArray>()
        for (i in 0 until numImages) {
            images.add(readImage(numRows, numColumns, bb).toDoubleArray())
        }
        return images
    }

    fun getLabelsAsVector(infile: String): List<DoubleArray> {
        val bb = loadFileToByteBuffer(infile)
        assertMagicNumber(LABEL_FILE_MAGIC_NUMBER, bb.int)
        val numLabels = bb.int
        val labels = LinkedList<DoubleArray>()
        for (i in 0 until numLabels-1) {
            var vector = DoubleArray(10, {i -> 0.0})
            vector[bb.get().toInt() and 0xFF] = 1.0
            labels.add(vector)
        }
        return labels
    }

    private fun readImage(numRows: Int, numCols: Int, bb: ByteBuffer): List<Double> {
        val image = LinkedList<Double>()
        for (row in 0 until numRows)
            image.addAll(readRow(numCols, bb))
        return image
    }

    private fun readRow(numCols: Int, bb: ByteBuffer): List<Double> {
        val row = LinkedList<Double>()
        for (col in 0 until numCols) {
            row.add((bb.get().toInt() and 0xFF).toDouble()) // To unsigned
        }
        return row
    }

    private fun loadFileToByteBuffer(infile: String): ByteBuffer {
        return ByteBuffer.wrap(loadFile(infile))
    }

    private fun loadFile(infile: String): ByteArray {
        RandomAccessFile(infile, "r").use { file ->
            file.channel.use { channel ->
                try {
                    val fileSize = channel.size()
                    val bb = ByteBuffer.allocate(fileSize.toInt())
                    channel.read(bb)
                    bb.flip()
                    val baos = ByteArrayOutputStream()
                    for (i in 0 until fileSize)
                        baos.write(bb.get().toInt())
                    return baos.toByteArray()
                } catch (e: Exception) {
                    throw RuntimeException("Exception occured while loading the file", e)
                }
            }
        }
    }

    private fun assertMagicNumber(expectedMagicNumber: Int, magicNumber: Int) {
        if (expectedMagicNumber != magicNumber) {
            when (expectedMagicNumber) {
                LABEL_FILE_MAGIC_NUMBER -> throw RuntimeException("This is not a label file.")
                IMAGE_FILE_MAGIC_NUMBER -> throw RuntimeException("This is not an image file.")
                else                    -> throw RuntimeException("Expected magic number $expectedMagicNumber found $magicNumber")
            }
        }
    }

}
