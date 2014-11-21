package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TextGuiItem extends GuiItem {
	public enum TextSize {
		small {
			@Override
			public int getTextSize() {
				return 20;
			}
		},
		medium {
			@Override
			public int getTextSize() {
				return 30;
			}
		},
		large {
			@Override
			public int getTextSize() {
				return 40;
			}
		},
		;
		public abstract int getTextSize();
	}

	protected RelativePoint paintPosition;
	protected Paint paint;
	protected String toPaintText;

	public TextGuiItem(RelativePoint left_up, String text, TextSize size) {
		paintPosition = left_up;
		toPaintText = text;
		paint = new Paint();
		paint.setTextSize(size.getTextSize());
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(toPaintText, paintPosition.getScreenX(),
				paintPosition.getScreenY() - paint.getTextSize(), paint);
	}
}
