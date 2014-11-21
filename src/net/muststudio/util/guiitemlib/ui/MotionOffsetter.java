package net.muststudio.util.guiitemlib.ui;

import android.view.MotionEvent;

public class MotionOffsetter {
	private float x;
	private float y;

	public MotionOffsetter() {
		this(0, 0);
	}

	public MotionOffsetter(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void adjustMotion(MotionEvent e) {
		e.offsetLocation(x, y);
	}

	public void resetMotion(MotionEvent e) {
		e.offsetLocation(-x, -y);
	}

	public float getDx() {
		return x;
	}

	public float getDy() {
		return y;
	}

	public MotionOffsetter setDx(float dx) {
		x = dx;
		return this;
	}

	public MotionOffsetter setDy(float dy) {
		y = dy;
		return this;
	}

	public MotionOffsetter moveDx(float dx) {
		x += dx;
		return this;
	}

	public MotionOffsetter moveDy(float dy) {
		y += dy;
		return this;
	}
}