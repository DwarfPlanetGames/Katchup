package ml.dpgames.katchup;

public class Player extends GameObject {

	public int velX = 0, velY = 0;
	public int moveSpeed = 5;

	public Player() {
		super(0, 0, 16, 16);
	}

	@Override
	public void update() {
		if (MainGame.mouseDown) {
			float direction = (float) Math.atan2(MainGame.mouseWorldY - y, MainGame.mouseWorldX - x);
			velX = (int)(Math.cos(direction) * moveSpeed);
			velY = (int)(Math.sin(direction) * moveSpeed);
		}
		x += velX;
		y += velY;
		velX *= 0.9;
		velY *= 0.9;
	}
}