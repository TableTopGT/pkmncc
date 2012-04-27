package org.tabletop.pkmncc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static enum Mode {INIT, SILENT, REAL, EXIT};
    private final BluetoothAdapter mAdapter;
    private BluetoothDevice pokedex;
    private BluetoothSocket pokeLink;
    private BufferedReader inStr;
    
	private Mode currMode = Mode.SILENT;
	private String RFIDTag;
	private boolean dataAvailable = false;
	
	public RFIDListener(Context context) {
		Card.setContext(context);
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mAdapter != null) {
			mAdapter.cancelDiscovery();
			pokedex = mAdapter.getRemoteDevice("00:06:66:44:E4:99");
			try {
				UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
				pokeLink = pokedex.createInsecureRfcommSocketToServiceRecord(MY_UUID);
				pokeLink.connect();
				inStr =  new BufferedReader(new InputStreamReader(pokeLink.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		// Pokemon
		if (RFIDTag.equals("1217281")){
			swipedCard = new Charizard();
		}
		else if (RFIDTag.equals("22378135")){
			swipedCard = new Blastoise();
		}
		else if (RFIDTag.equals("1184336") || RFIDTag.equals("1216852")){
			swipedCard = new Bulbasaur();
		}
		else if (RFIDTag.equals("1274227") || RFIDTag.equals("1185077") || RFIDTag.equals("1262402")){
			swipedCard = new Charmander();
		}
		else if (RFIDTag.equals("1267527")){
			swipedCard = new Charmeleon();
		}
		else if (RFIDTag.equals("23487270")){
			swipedCard = new Combee();
		}
		else if (RFIDTag.equals("23491345")){
			swipedCard = new Dusclops();
		}
		else if (RFIDTag.equals("23478800")){
			swipedCard = new Dusknoir();
		}
		else if (RFIDTag.equals("23490625") || RFIDTag.equals("1258322")){
			swipedCard = new Duskull();
		}
		else if (RFIDTag.equals("23499558")){
			swipedCard = new Ivysaur();
		}
		else if (RFIDTag.equals("23500401")){
			swipedCard = new Machamp();
		}
		else if (RFIDTag.equals("23497080")){
			swipedCard = new Machoke();
		}
		else if (RFIDTag.equals("23479810")){
			swipedCard = new Machop();
		}
		else if (RFIDTag.equals("9834802")){
			swipedCard = new Magnemite();
		}
		else if (RFIDTag.equals("22365539")){
			swipedCard = new Magneton();
		}
		else if (RFIDTag.equals("1123666")){
			swipedCard = new Magnezone();
		}
		else if (RFIDTag.equals("1140019")){
			swipedCard = new Pichu();
		}
		else if (RFIDTag.equals("1189140")){
			swipedCard = new Pikachu();
		}
		else if (RFIDTag.equals("23532368")){
			swipedCard = new Raichu();
		}
		else if (RFIDTag.equals("1395027") || RFIDTag.equals("1127744")){
			swipedCard = new Squirtle();
		}
		else if (RFIDTag.equals("22378135")){
			swipedCard = new Vespiquen();
		}
		else if (RFIDTag.equals("23482945")){
			swipedCard = new Venusaur();
		}
		else if (RFIDTag.equals("283703")){
			swipedCard = new Wartortle();
		}
		// Energy
		else if (RFIDTag.equals("0444444440")){
//			swipedCard = new Energy(Element.COLORLESS);
		}
		else if (RFIDTag.equals("0555555550")){
//			swipedCard = new Energy(Element.DARKNESS);
		}
		else if (RFIDTag.equals("292201") || RFIDTag.equals("8803862") || RFIDTag.equals("289105") || RFIDTag.equals("1398372")){
			swipedCard = new Energy(Element.FIGHTING);
		}
		else if (RFIDTag.equals("1143107") || RFIDTag.equals("23500088") || RFIDTag.equals("1265928")){
			swipedCard = new Energy(Element.FIRE);
		}
		else if (RFIDTag.equals("1250133") || RFIDTag.equals("287794") || RFIDTag.equals("272721") || RFIDTag.equals("282919")){
			swipedCard = new Energy(Element.GRASS);
		}
		else if (RFIDTag.equals("9917750") || RFIDTag.equals("283990") || RFIDTag.equals("291109")){
			swipedCard = new Energy(Element.LIGHTNING);
		}
		else if (RFIDTag.equals("1126994") || RFIDTag.equals("23479333") || RFIDTag.equals("291639")){
			swipedCard = new Energy(Element.METAL);
		}
		else if (RFIDTag.equals("294934") || RFIDTag.equals("23496499") || RFIDTag.equals("274772")){
			swipedCard = new Energy(Element.WATER);
		}
		else if (RFIDTag.equals("23500593") || RFIDTag.equals("23500117") || RFIDTag.equals("23487590") || RFIDTag.equals("23483912")){
			swipedCard = new Energy(Element.PSYCHIC);
		}
		
		// Trainer
		else if (RFIDTag.equals("23499091")){
			swipedCard = new Trainer(TrainerType.ENERGYREMOVAL);
		}
		else if (RFIDTag.equals("9671958") || RFIDTag.equals("23491633")){
			swipedCard = new Trainer(TrainerType.FULLHEAL);
		}
		else if (RFIDTag.equals("9671953") || RFIDTag.equals("1250901") || RFIDTag.equals("1216870")){
			swipedCard = new Trainer(TrainerType.POTION);
		}
		else {
			swipedCard = null;
		}
		
		// Don't allow main thread to get a new card until next swipe
		dataAvailable = false;

		// Reset RFIDtag //TODO synchronize
		RFIDTag = null;

		// Give the swiped card
		return swipedCard;
	}

	/** Swipe a card w/ specified tag after x seconds */
	private void swipeCard(String s, int seconds) {
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
			while (currMode != Mode.EXIT)
				switch(currMode) {
				case INIT:
					// swipe a charmander every x seconds
					swipeCard("1185077", 1);
					break;
				case REAL:
					try {
						if (inStr.ready()) {
							RFIDTag = inStr.readLine();
							dataAvailable = true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case SILENT:
					break;
				}
	}
	
	public void pause() {
		setMode(Mode.EXIT);
		while (true)
		try {
			join();
			break;
		} catch (InterruptedException e) {
			// try again
		}
	}
}
