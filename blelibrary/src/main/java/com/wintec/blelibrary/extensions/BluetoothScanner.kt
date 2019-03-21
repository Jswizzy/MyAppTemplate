package com.wintec.blelibrary.extensions

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import com.wintec.blelibrary.types.Result


fun AppCompatActivity.bluetoothLESupported(): Boolean =
    packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

fun getBluetoothAdapter(): Result<BluetoothAdapter> {
    val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    return if (adapter != null) {
        Result.Success(adapter)
    } else {
        Result.Error("Bluetooth not supported")
    }
}

fun AppCompatActivity.enableBluetooth(
    bluetoothAdapter: BluetoothAdapter,
    requestCode: Int = REQUEST_ENABLE_BLUETOOTH
): OnResult {
    if (!bluetoothAdapter.isEnabled) {
        startResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), requestCode)
    }
    return OnResult(requestCode)
}


