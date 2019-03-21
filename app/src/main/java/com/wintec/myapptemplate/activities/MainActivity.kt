package com.wintec.myapptemplate.activities

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import com.wintec.blelibrary.extensions.*
import com.wintec.blelibrary.types.Result
import com.wintec.blelibrary.types.exhaustive
import com.wintec.myapptemplate.R

class MainActivity : ScopedActivity() {

    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var onBluetoothRequestResult: OnResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!bluetoothLESupported()) {
            toast("BLE is not supported")
            finish()
        }

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        when (val result = getBluetoothAdapter()) {
            // Checks if Bluetooth is supported on the device.
            is Result.Success -> bluetoothAdapter = result.data
            is Result.Error -> {
                // Device doesn't support Bluetooth
                toast(result.message)
                finish()
            }
        }.exhaustive

    }

    override fun onResume() {
        super.onResume()
        onBluetoothRequestResult = enableBluetooth(bluetoothAdapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onBluetoothRequestResult { request ->
            if (request == requestCode && resultCode == Activity.RESULT_CANCELED) {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
