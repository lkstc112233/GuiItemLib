package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class BlockedFloatingGuiItemContainer extends GuiItemContainer {
	@Override
	public void draw(Canvas canvas) {
		canvas.drawRGB(255, 255, 255);
		super.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		super.onTouchEvent(e);
		return true;
	}

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		super.isInsideOf(rp);
		return true;
	}

	@Override
	public boolean onBackKey() {
		super.onBackKey();
		return true;
	}

	@Override
	public boolean checkState() {
		super.checkState();
		return false;
	}

	@Override
	public boolean onTextEvent(CharSequence text) {
		super.onTextEvent(text);
		return true;
	}

	@Override
	public boolean onDeleteEvent(int sum) {
		super.onDeleteEvent(sum);
		return true;
	}
}
