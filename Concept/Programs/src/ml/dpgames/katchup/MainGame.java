package ml.dpgames.katchup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MainGame implements Runnable, MouseListener, MouseMotionListener {

	// Window
	private JFrame frame;
	private Canvas canvas;
	public static int mouseX = 0, mouseY = 0;
	public static boolean mouseDown = false;
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
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		frame.add(canvas);
		// Start game
		frame.setVisible(true);
		start();
	}

	public static void main(String[] args) {
		new MainGame();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
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
				Thread.sleep(15);
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

		if (mouseDown) {
			g.fillRect(mouseX - 16, mouseY - 16, 32, 32);
		}

		g.translate(-camX, -camY);
		g.dispose();
		canvas.getBufferStrategy().show();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		mouseDown = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseDown = false;
	}

}
