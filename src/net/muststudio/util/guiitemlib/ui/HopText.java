package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HopText extends HopBase {
	private String toShow;
	protected Paint textPainter;

	public HopText(RelativePoint beginPump, String textGiven) {
		this(beginPump, textGiven, Color.rgb(255, 215, 0));
	}

	public HopText(RelativePoint beginPump, String textGiven, int color) {
		super(beginPump);
		toShow = textGiven;
		textPainter = new Paint();
		textPainter.setColor(color);
		textPainter.setTextSize(40);
		textPainter.setAntiAlias(true);
		dPosition = new Position(-textPainter.measureText(toShow), 20);
	}

	@Override
	protected void move() {
		super.move();
		if (life < 20)
			textPainter.setColor(((255 * life / 20) << 24)
					| (textPainter.getColor() & 0xffffff));
	}

	@Override
	public void draw(Canvas canvas) {
		int paintX = (int) (originalPoint.getScreenX() + dPosition.x);
		int paintY = (int) (originalPoint.getScreenY() + dPosition.y);
		canvas.drawText(toShow, paintX, paintY, textPainter);
	}
}
