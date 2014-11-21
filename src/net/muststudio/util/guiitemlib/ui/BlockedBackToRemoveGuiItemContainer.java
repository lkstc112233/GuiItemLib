package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class BlockedBackToRemoveGuiItemContainer extends GuiItemContainer {
	protected int backgroundColor = -1;// WHITE

	public void setBackgroundColor(int color) {
		backgroundColor = color;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(backgroundColor);
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
		if (!super.onBackKey())
			removeThis();
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
