package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.tabletop.pkmncc.card.*;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

/** This class must be instantiated before any cards are created. */
public final class RFIDListener extends Thread {

	public static enum Mode {INIT, SILENT, REAL};
    private final BluetoothAdapter mAdapter;
    private final BluetoothDevice pokedex;
    private BluetoothSocket pokeLink;
    private InputStream inStr;
    
	private Mode currMode = Mode.SILENT;
	private int RFIDTag;
	private boolean dataAvailable = false;
	
	public RFIDListener(Context context) {
		Card.setContext(context);
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		mAdapter.cancelDiscovery();
		pokedex = mAdapter.getRemoteDevice("00:06:66:44:E4:99");
		try {
			UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
			pokeLink = pokedex.createInsecureRfcommSocketToServiceRecord(MY_UUID);
			pokeLink.connect();
			inStr = pokeLink.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean cardSwiped() {
		return dataAvailable;
	}

	public void setMode(Mode m) {
		currMode = m;
	}

	public Card getCard() {
		
		Card swipedCard = null;
		
		// Return the swiped card
		switch (RFIDTag) {
		case 22378135:
			swipedCard =  new Charmander();
			break;
		case 22365539:
			swipedCard = new Charmander();
			break;
		case 50:
			swipedCard =  new Charmander();
			break;
		case 22365537:
			swipedCard =  new Charizard();
			break;
		case 22378138:
			swipedCard =  new Energy(Element.LIGHTNING);
			break;
		case 22365530:
			swipedCard = new Energy(Element.COLORLESS);
			break;
		case 22378131:
			swipedCard = new Energy(Element.COLORLESS);
			break;
		case 22365532:
			swipedCard = new Energy(Element.DARKNESS);
			break;
		case 22378133:
			swipedCard = new Energy(Element.FIGHTING);
			break;
		case 22365534:
			swipedCard = new Energy(Element.FIRE);
			break;
		case 22378163:
			swipedCard = new Energy(Element.GRASS);
			break;
		case 22365523:
			swipedCard =  new Trainer(TrainerType.ENERGYREMOVAL);
			break;
		case 223789876:
			swipedCard =  new Trainer(TrainerType.FULLHEAL);
			break;
		case 223655:
			swipedCard =  new Trainer(TrainerType.POTION);
			break;
		}
		
		// Don't allow main thread to get a new card until next swipe
		dataAvailable = false;

		// Reset RFIDtag //TODO synchronize
		RFIDTag = 0;

		// Give the swiped card
		return swipedCard;
	}

	/** Swipe a card w/ specified tag after x seconds */
	private void swipeCard(int s, int seconds) {
		try {
			Thread.sleep(seconds*1000);
			RFIDTag = s; 				// swipe a card
			dataAvailable = true;		// notify of card swipe
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		byte[] bytes = new byte[128];
			while (true)
				switch(currMode) {
				case INIT:
					// swipe a charmander every second
					swipeCard(22378135, 1);
					break;
				case REAL:
					try {
						inStr.read(bytes);
						RFIDTag = byteToInt(bytes);
						dataAvailable = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case SILENT:
					break;
				}
	}
	
	//For now just return the first byte //TODO (how to store 9 bytes???)
	private int byteToInt(byte[] b) {
		int i = 0;
		for (byte group : b) {
			i <<= group;
		}
		return b[0];
	}

}
