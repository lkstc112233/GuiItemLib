package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class TextEditGuiItem extends SquareGuiItem {
	protected String textHeld;
	protected Paint rectPaint;
	protected Paint textPaint;

	public TextEditGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, "");
	}

	public TextEditGuiItem(RelativePoint left_up, RelativePoint right_bottom,
			String oriString) {
		super(left_up, right_bottom);
		textHeld = oriString;
		rectPaint = new Paint();
		rectPaint.setColor(-1);
		textPaint = new Paint();
		textPaint.setTextSize(this.guiItemSquareRectF.height() / 5 * 4);
		textPaint.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(guiItemSquareRect, rectPaint);
		canvas.drawText(textHeld, guiItemSquareRectF.left, guiItemSquareRectF.top
				+ guiItemSquareRectF.height() / 5 * 4, textPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (isInsideOf(RelativePoint.getRelativePoint(e.getX(), e.getY()))) {
			showInputMethod();
			return true;
		} else {
			hideInputMethod();
			return false;
		}
	}

	@Override
	public boolean onTextEvent(CharSequence text) {
		textHeld += text;
		return true;
	}

	@Override
	public boolean onDeleteEvent(int sum) {
		textHeld = textHeld.substring(0, Math.max(textHeld.length() - sum, 0));
		return true;
	}

	public String getText() {
		return textHeld;
	}

	public String clearText() {
		String temp = textHeld;
		textHeld = "";
		return temp;
	}

	public TextEditGuiItem setText(String s) {
		textHeld = s;
		return this;
	}
}
