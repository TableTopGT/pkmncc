package org.tabletop.pkmncc;


/**This class communicated with a Bluetooth device to bring RFID data into the game**/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.content.*;



public class BluetoothListener {
	
	// Member fields
    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    //private AcceptThread mSecureAcceptThread;
    //private AcceptThread mInsecureAcceptThread;
    //private ConnectThread mConnectThread;
    //private ConnectedThread mConnectedThread;
    private int mState;

    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device

    /**
     * Constructor. Prepares a new BluetoothChat session.
     * @param context  The UI Activity Context
     * @param handler  A Handler to send messages back to the UI Activity
     */
	public BluetoothListener(Context context, Handler handler){
		 mAdapter = BluetoothAdapter.getDefaultAdapter();
		 if (mAdapter == null) {
			    // Device does not support Bluetooth
			}
		 /**if (!mAdapter.isEnabled()) {
			    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}**/
	     mState = STATE_NONE;
	     mHandler = handler;		
	}
	


}
