package perangkaikode.com.pickfileandimage

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Parcelable

class PickData {

    fun getData(context: Context): Intent {
        val allIntents = ArrayList<Intent>()
        val packageManager = context.packageManager

        val intentApp = Intent(Intent.ACTION_GET_CONTENT)
        intentApp.type = "image/*"
        val listIntentApp = packageManager.queryIntentActivities(intentApp, 0)
        for (res in listIntentApp) {
            val intent = Intent(intentApp)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.setPackage(res.activityInfo.packageName)
            allIntents.add(intent)
        }

        var mainIntent = allIntents[allIntents.size - 1]
        for (intent in allIntents) {
            if (intent.component!!.className == "com.android.documentsui.DocumentsActivity") {
                mainIntent = intent
                break
            }
        }

        allIntents.remove(mainIntent)
        val chooserIntent = Intent.createChooser(mainIntent, "Pilih File")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toTypedArray<Parcelable>())
        return chooserIntent
    }
}