package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import net.muststudio.util.guiitemlib.util.ColorHolder;

public class PressableColorfulButton extends PressableButton {
	protected ColorHolder color;

	public PressableColorfulButton(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, "", Color.WHITE);
	}

	public PressableColorfulButton(RelativePoint left_up, RelativePoint right_bottom,
			String string) {
		this(left_up, right_bottom, string, Color.WHITE);
	}

	public PressableColorfulButton(RelativePoint left_up, RelativePoint right_bottom,
			int color) {
		this(left_up, right_bottom, "", color);
	}

	public PressableColorfulButton(RelativePoint left_up, RelativePoint right_bottom,
			String string, int color) {
		super(left_up, right_bottom, string, color);
		this.color = new ColorHolder(color);
	}

	public ColorHolder getColorHolder() {
		return color;
	}

	@Override
	public void draw(Canvas canvas) {
		backgroundPaint.setColor(color.getColor());
		super.draw(canvas);
	}
}
