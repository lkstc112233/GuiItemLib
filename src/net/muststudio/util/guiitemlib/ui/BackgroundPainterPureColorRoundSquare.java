package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

public class BackgroundPainterPureColorRoundSquare extends BackgroundPainterBase {
	protected Paint strokePainter;
	protected Paint fillPainter;
	protected RectF location;

	public BackgroundPainterPureColorRoundSquare(RelativePoint mainPosition,
			RelativePoint subPosition) {
		super(mainPosition, subPosition);
		location = new RectF(super.location);
		strokePainter = new Paint();
		fillPainter = new Paint();
		strokePainter.setStyle(Style.STROKE);
		strokePainter.setColor(Color.BLACK);
		fillPainter.setColor(Color.WHITE);
		fillPainter.setStyle(Style.FILL);
	}

	public BackgroundPainterPureColorRoundSquare setColor(int color) {
		fillPainter.setColor(color);
		return this;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRoundRect(location, 10, 10, fillPainter);
		canvas.drawRoundRect(location, 10, 10, strokePainter);
	}
}
