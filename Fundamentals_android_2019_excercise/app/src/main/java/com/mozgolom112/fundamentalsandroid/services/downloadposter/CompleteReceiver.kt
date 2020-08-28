package com.mozgolom112.fundamentalsandroid.services.downloadposter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.mozgolom112.fundamentalsandroid.ui.PosterActivity

class CompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("CompleteReceiver", "#onReceive")
        Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show()
        val posterPath = intent.getStringExtra(DownloadPosterService.POSTER_PATH) ?: return
        Log.d("CompleteReceiver", "#onReceive, posterPath: $posterPath")
        val trailerIntent = PosterActivity
            .newIntent(context, posterPath)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(trailerIntent)
    }
}

//TODO("Code-Review: № - ")