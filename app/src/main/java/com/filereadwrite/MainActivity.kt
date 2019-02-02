package com.filereadwrite

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createExternalStoragePrivateFile()
    }


    fun createExternalStoragePrivateFile() {
        // Create a path where we will place our private file on external
        // storage.
        val file = File(getExternalFilesDir(null), "test.png")

        try {
            Log.e("Path",file.path)

            val `is` = resources.openRawResource(R.raw.test)
            val os = FileOutputStream(file)
            val data = ByteArray(`is`.available())
            `is`.read(data)
            os.write(data)
            `is`.close()
            os.close()
            if(hasExternalStoragePrivateFile()) {
                icon.setImageURI(Uri.fromFile(file))
            }
        } catch (e: IOException) {

            Log.e("ExternalStorage", "Error writing $file", e)
        }

    }

    fun deleteExternalStoragePrivateFile() {
        val file = File(getExternalFilesDir(null), "test.png")
        if (file != null) {
            file.delete()
        }
    }

    fun hasExternalStoragePrivateFile(): Boolean {
        val file = File(getExternalFilesDir(null), "test.png")
        return if (file != null) {
            file.exists()
        } else false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        deleteExternalStoragePrivateFile()
    }
}
