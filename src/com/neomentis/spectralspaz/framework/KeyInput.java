package com.neomentis.spectralspaz.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;

import com.neomentis.spectralspaz.window.Handler;

public class KeyInput extends KeyAdapter 
{
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void buttonPressed(MouseEvent e) {
		int button = e.getButton();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Color)
			{
				if(button == MouseEvent.BUTTON1) {
					//TODO: increase score, remove block
				}
			}
		}
	}
	
	public void buttonReleased(MouseEvent e) {
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Color)
			{
				//TODO if needed
			}
		}
	}
	
}
