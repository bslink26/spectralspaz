package com.neomentis.spectralspaz.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.neomentis.spectralspaz.framework.KeyInput;
import com.neomentis.spectralspaz.framework.Texture;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 3905480252076443641L;

	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	Handler handler;
	static Texture tex;
	
	private void init() 
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()
	{
		handler.tick();
	}
	/**
	 * setting the amount of buffers we want prepared behind first buffer
	 * 
	 * if computer is fast enough if can buffer more than one image at a time,
	 * this extra buffer can increase FPS and increase optimization
	 * the reason we don't do more than 3 is because no computer can run up to
	 * 100 buffers at a time, 3 is about what the average gaming super computer 
	 * in 2013 was capping out at.
	 * 
	 *  3 is about the most you can get loaded per say
	 * - Brandon
	 */
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//////////////////////////////////
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		
		//////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){ 
		new Window(800, 600, "Spectral Spaz", new Game());
	}
	
	public static Texture getInstance(){
		return tex;
	}
	

}
