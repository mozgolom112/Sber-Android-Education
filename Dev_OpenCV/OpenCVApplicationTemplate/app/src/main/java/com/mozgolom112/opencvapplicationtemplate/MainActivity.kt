package com.mozgolom112.opencvapplicationtemplate

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.mozgolom112.opencvapplicationtemplate.CvCamera.CvCameraViewListener2
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val CAMERA_PERMISSION_REQUEST = 1
    private val mOpenCvCameraView: CameraBridgeViewBase
            by lazy { findViewById<CameraBridgeViewBase>(R.id.jvcamvMainSurface) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "called onCreate")
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST
        )

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_main)

        mOpenCvCameraView.apply {
            visibility = SurfaceView.VISIBLE

            setCvCameraViewListener(CvCameraViewListener2)
        }
    }

    override fun onPause() {
        super.onPause()
        mOpenCvCameraView.disableView()
    }

    override fun onResume() {
        super.onResume()
        mOpenCvCameraView.enableView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mOpenCvCameraView.disableView()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST -> {
                val isPermissionGranted = grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (isPermissionGranted)
                    mOpenCvCameraView.setCameraPermissionGranted()
                else {
                    val message = "Camera permission was not granted"
                    Log.e(TAG, message)
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
            else -> {
                Log.e(TAG, "Unexpected permission request")
            }
        }
    }

    init {
        initOpenCv()
    }

    private fun initOpenCv() {
        val isLoadOpenCVSuccess = OpenCVLoader.initDebug()
        if (isLoadOpenCVSuccess) initMyNativeLib()
        Log.d(TAG, "isLoadOpenCVSuccess: $isLoadOpenCVSuccess")
    }

    private fun initMyNativeLib() {
        System.loadLibrary("native-lib")
    }
}