**Permission**
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```


**Installing**

Add repository in root build.gradle

```
repositories {
    maven { url "https://jitpack.io" }
}
```

And add dependency to module build.gradle:

```
dependencies {
    implementation 'com.github.aqitmalinzda:Pick-Library:1.0.0'
}
```

**Sample Code**

```
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
```
