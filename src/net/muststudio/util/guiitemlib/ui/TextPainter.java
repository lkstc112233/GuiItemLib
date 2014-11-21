package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TextPainter extends SquareGuiItem {
	protected String string;
	protected String[] stringSliced;
	protected Paint textPaint;
	protected int textCount;
	protected Rect paintRect;
	protected boolean verticalCenter;
	protected boolean horizontalCenter;
	protected boolean paintBackground;
	private Paint whitePaint;

	public TextPainter(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, true);
	}

	public TextPainter(RelativePoint left_up, RelativePoint right_bottom, boolean paintBack) {
		super(left_up, right_bottom);
		paintRect = new Rect(mainPosition.getScreenX(), mainPosition.getScreenY(),
				subPosition.getScreenX(), subPosition.getScreenY());
		string = "";
		textPaint = new Paint();
		whitePaint = new Paint();
		verticalCenter = false;
		horizontalCenter = false;
		whitePaint.setColor(Color.WHITE);
		setTextCount(40);
	}

	public TextPainter setString(String str) {
		string = str;
		String[] stringSlicedTemp = string.split("\n");
		int count = 0;
		for (String X : stringSlicedTemp)
			count += Math.ceil(1.0 * X.length() / textCount);
		if (count == 0) {
			stringSliced = new String[1];
			stringSliced[0] = "";
			return this;
		}
		stringSliced = new String[count];
		int cache = 0;
		for (String X : stringSlicedTemp)
			for (int i = 0; i < Math.ceil(1.0 * X.length() / textCount); ++i)
				stringSliced[cache++] = X.substring(i * textCount,
						Math.min((i + 1) * textCount, X.length()));
		return this;
	}

	public TextPainter setTextCount(int count) {
		if (count <= 0)
			return this;
		textCount = count;
		textPaint.setTextSize(paintRect.width() / textCount);
		return setString(string);
	}

	public TextPainter setHorizontalCenter(boolean hrc) {
		horizontalCenter = hrc;
		return this;
	}

	public TextPainter setVerticalCenter(boolean vrc) {
		verticalCenter = vrc;
		return this;
	}

	public TextPainter setPaintBackground(boolean set) {
		paintBackground = set;
		return this;
	}

	@Override
	public void draw(Canvas canvas) {
		if (paintBackground)
			canvas.drawRect(paintRect, whitePaint);
		canvas.save();
		canvas.clipRect(paintRect);
		int count = 0;
		if (horizontalCenter)
			if (verticalCenter)
				for (String str : stringSliced)
					canvas.drawText(
							str,
							paintRect.centerX() - textPaint.measureText(str) / 2,
							(paintRect.centerY() - (stringSliced.length * textPaint.getTextSize()) / 2)
									+ ++count
									* textPaint.getTextSize()
									- textPaint.getTextSize()
									/ 5, textPaint);
			else
				for (String str : stringSliced)
					canvas.drawText(str, paintRect.centerX() - textPaint.measureText(str) / 2,
							mainPosition.getScreenY() + ++count * textPaint.getTextSize()
									- textPaint.getTextSize() / 5, textPaint);
		else if (verticalCenter)
			for (String str : stringSliced)
				canvas.drawText(str, mainPosition.getScreenX(),
						(paintRect.centerY() - (stringSliced.length * textPaint.getTextSize()) / 2)
								+ ++count * textPaint.getTextSize() - textPaint.getTextSize() / 5,
						textPaint);
		else
			for (String str : stringSliced)
				canvas.drawText(str, mainPosition.getScreenX(), mainPosition.getScreenY() + ++count
						* textPaint.getTextSize() - textPaint.getTextSize() / 5, textPaint);
		canvas.restore();
	}
}
