package ml.dpgames.katchup;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameObject {

	public int x = 0, y = 0;
	public int width = 1, height = 1;
	public boolean collide = false;
	public boolean kill = false;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update(){};
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
