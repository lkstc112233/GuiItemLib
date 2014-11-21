package net.muststudio.util.guiitemlib.util;

import android.graphics.Color;

public class ColorHolder {
	private int color;

	public ColorHolder() {
		this(Color.WHITE);
	}

	public ColorHolder(int color) {
		this.color = color;
	}

	public ColorHolder setColor(int color) {
		this.color = color;
		return this;
	}

	public int getColor() {
		return color;
	}
}
