package com.basdoor.mlpcamera.camera

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import com.basdoor.mlpcamera.R

class ScamActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_scam)
    }
}