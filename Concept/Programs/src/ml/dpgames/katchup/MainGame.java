package ml.dpgames.katchup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class MainGame implements Runnable {

	// Window
	private JFrame frame;
	private Canvas canvas;
	public static int camX = 0, camY = 0;

	// System
	private Thread thread;
	public static boolean running = false;

	public MainGame() {
		// Setup frame
		frame = new JFrame("Katchup");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 400);
		// Setup canvas
		canvas = new Canvas();
		frame.add(canvas);
		// Start game
	}

	public synchronized void start() {
		if (running)
			return;
		thread = new Thread(this, "Katchup");
		thread.start();
	}

	public void run() {
		long oldTime = System.nanoTime();
		long newTime = System.nanoTime();
		while (running) {
			newTime = System.nanoTime();
			if (newTime - oldTime > 1000000000 / 60) {
				oldTime = 1000000000 / 60;
				update();
			}
			render();
			try {
				Thread.sleep(1000 / 120);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {

	}

	public void render() {
		if (canvas.getBufferStrategy() == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		g.setColor(Color.WHITE);
		g.translate(camX, camY);
		
		
		
		g.translate(-camX, -camY);
		g.dispose();
		canvas.getBufferStrategy().show();
	}

}
