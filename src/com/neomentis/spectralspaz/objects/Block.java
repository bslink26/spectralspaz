package com.neomentis.spectralspaz.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.neomentis.spectralspaz.framework.GameObject;
import com.neomentis.spectralspaz.framework.ObjectId;
import com.neomentis.spectralspaz.framework.Texture;
import com.neomentis.spectralspaz.window.Game;

public class Block extends GameObject {
	
	Texture tex = Game.getInstance();

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) 
	{
		
	}
	public void render(Graphics g) 
	{
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
