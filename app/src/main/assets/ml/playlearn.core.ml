package org.playlearn.core.ml

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder

class PlayLearnModel(context: Context) {
    private val interpreter: Interpreter

    init {
        val modelStream = context.assets.open("ml/playlearn_model.tflite")
        val modelBytes = modelStream.readBytes()
        val buffer = ByteBuffer.allocateDirect(modelBytes.size)
        buffer.order(ByteOrder.nativeOrder())
        buffer.put(modelBytes)
        interpreter = Interpreter(buffer)
    }

    fun predict(input: FloatArray): FloatArray {
        val output = Array(1) { FloatArray(3) } // exemplo: 3 classes
        interpreter.run(arrayOf(input), output)
        return output[0]
    }
}
