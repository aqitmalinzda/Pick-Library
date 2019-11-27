package perangkaikode.com.picklibrary

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import perangkaikode.com.pickfile.PickData
import perangkaikode.com.pickfile.UriToFile

class MainActivity : AppCompatActivity() {

    companion object {
        val PICK_FILE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivityForResult(PickData().getData(this), PICK_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE && resultCode == Activity.RESULT_OK) {
            try {
                val uri = data?.data
                val file = UriToFile.from(this, uri)
                val uriFromFile = Uri.fromFile(file)
                tv1.text = uriFromFile.path
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
