package com.example.pokdex.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileInputStream

class ImageStorageManager {
    companion object {
        fun saveToInternalStorage(context: Context, bitmapImage: Bitmap, imageFileName: String): String {
            context.openFileOutput(imageFileName, Context.MODE_PRIVATE).use { fos ->
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 25, fos)
            }
            Log.i("saver", context.filesDir.absolutePath)

            return context.filesDir.absolutePath
        }

        fun getImageFromInternalStorage(context: Context, imageFileName: String): Bitmap? {
            val directory = context.filesDir
            val file = File(directory, imageFileName)
            val inputStream = FileInputStream(file)
            val image = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            return image
        }

//        fun deleteImageFromInternalStorage(context: Context, imageFileName: String): Boolean {
//            val dir = context.filesDir
//            val file = File(dir, imageFileName)
//            return file.delete()
//        }
    }
}
