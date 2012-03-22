package org.tabletop.pkmncc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class DialogBox {
	public String text;
	public Rect button, background;
	public Paint textStyle, buttonStyle, backStyle;
	public boolean done;
	public DialogBox(){
		text = "";
		textStyle = new Paint();
		buttonStyle = new Paint();
		background = new Rect(0, 0, 0, 0);
		button = new Rect(background.centerX()-200, background.centerY() + 100, background.centerX() + 200, background.centerY() + 150);
		backStyle = new Paint();
		done = false;
	}
	
	public DialogBox(String newText, Rect newBackground){
		text = newText;
		background = newBackground;
		button = new Rect(background.centerX()-200, background.centerY() + 100, background.centerX() + 200, background.centerY() + 150);
		done = false;
	}
	
	public DialogBox(String newText, Paint newtextStyle, Rect newBackground, Paint newbackStyle, Paint newbuttonStyle){
		text = newText;
		textStyle = newtextStyle;
		background = newBackground;
		button = new Rect(background.centerX()-200, background.centerY() + 100, background.centerX() + 200, background.centerY() + 150);
		buttonStyle = newbuttonStyle;
		backStyle = newbackStyle;
		done = false;
	}
	
	public void draw(Canvas board){
		board.drawRect(background, backStyle);
		board.drawRect(button, buttonStyle);
		board.drawText(text, background.centerX() -200, background.centerY() - 100, textStyle);
		board.drawText("Done", background.centerX()-75, background.centerY()+130, textStyle);
	}
	
	public void setText(String changeText){
		text = changeText;
	}
}
