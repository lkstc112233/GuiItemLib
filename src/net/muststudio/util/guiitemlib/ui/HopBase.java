package net.muststudio.util.guiitemlib.ui;

import java.util.Random;

public abstract class HopBase extends GuiItem {
	protected class Velocity {
		private static final double ENERGY_ABSORB_FACTOR = 0.8;
		public double x;
		public double y;

		public Velocity(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public void flipX() {
			x = -x;
		}

		public void flipY() {
			y = -y;
		}

		public void absorbXEnergy() {
			x = x * ENERGY_ABSORB_FACTOR;
		}

		public void absorbYEnergy() {
			y = y * ENERGY_ABSORB_FACTOR;
		}
	}

	protected static class Position {
		public double x;
		public double y;

		public Position(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public void move(Velocity velocity) {
			x += velocity.x;
			y += velocity.y;
		}
	}

	private static final double GRAVITY = 1;
	protected RelativePoint originalPoint;
	protected int life;
	protected Velocity velocity;
	protected Position dPosition;

	public HopBase(RelativePoint beginPump) {
		Random rand = new Random();
		originalPoint = beginPump;
		life = 50 + rand.nextInt(20);
		double abs = 15 + rand.nextDouble() * 5;
		double angle = -Math.PI * rand.nextDouble() / 2 - Math.PI / 4;
		double vx = abs * Math.cos(angle);
		double vy = abs * Math.sin(angle);
		velocity = new Velocity(vx, vy);
		dPosition = new Position(0, 0);
	}

	protected void move() {
		dPosition.move(velocity);
		velocity.y += GRAVITY;
		life -= 1;
		if (life < 0)
			removeThis();
	}

	@Override
	public boolean checkState() {
		move();
		return true;
	}

	protected boolean fixX() {
		double tempX = originalPoint.getScreenX() + dPosition.x;
		if (tempX < 0) {
			tempX = -tempX;
			dPosition.x = tempX - originalPoint.getScreenX();
			return true;
		}
		if (tempX > ScreenInfo.getScreenInfo().getScreenWidth()) {
			tempX = 2 * ScreenInfo.getScreenInfo().getScreenWidth() - tempX;
			dPosition.x = tempX - originalPoint.getScreenX();
			return true;
		}
		return false;
	}

	protected boolean fixY() {
		double tempY = originalPoint.getScreenY() + dPosition.y;
		if (tempY < 0) {
			tempY = -tempY;
			dPosition.y = tempY - originalPoint.getScreenY();
			return true;
		}
		if (tempY > ScreenInfo.getScreenInfo().getScreenHeight()) {
			tempY = 2 * ScreenInfo.getScreenInfo().getScreenHeight() - tempY;
			dPosition.y = tempY - originalPoint.getScreenY();
			return true;
		}
		return false;
	}
}
