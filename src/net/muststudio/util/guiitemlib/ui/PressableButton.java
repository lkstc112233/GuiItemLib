package net.muststudio.util.guiitemlib.ui;

import net.muststudio.util.guiitemlib.R;
import net.muststudio.util.guiitemlib.util.BitmapHolder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PressableButton extends GenericButton {
	protected static Bitmap pressedTexture;
	protected static NinePatch pressedNinePatch;
	protected static Bitmap releasedTexture;
	protected static NinePatch releasedNinePatch;

	protected static Bitmap getPressedTexture() {
		if (pressedTexture == null)
			pressedTexture = BitmapHolder.getInstance().getBitmap(
					R.drawable.button_pressed);
		return pressedTexture;
	}

	protected static NinePatch getPressedNinePatch() {
		if (pressedNinePatch == null)
			pressedNinePatch = new NinePatch(getPressedTexture(), getPressedTexture()
					.getNinePatchChunk(), null);
		return pressedNinePatch;
	}

	protected static Bitmap getReleasedTexture() {
		if (releasedTexture == null)
			releasedTexture = BitmapHolder.getInstance().getBitmap(
					R.drawable.button_released);
		return releasedTexture;
	}

	protected static NinePatch getReleasedNinePatch() {
		if (releasedNinePatch == null)
			releasedNinePatch = new NinePatch(getReleasedTexture(), getReleasedTexture()
					.getNinePatchChunk(), null);
		return releasedNinePatch;
	}

	protected Paint backgroundPaint;
	protected Paint pressedPaint;

	protected boolean pressed;

	public PressableButton(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, "", Color.WHITE);
	}

	public PressableButton(RelativePoint left_up, RelativePoint right_bottom,
			String string) {
		this(left_up, right_bottom, string, Color.WHITE);
	}

	public PressableButton(RelativePoint left_up, RelativePoint right_bottom, int color) {
		this(left_up, right_bottom, "", color);
	}

	public PressableButton(RelativePoint left_up, RelativePoint right_bottom,
			String string, int color) {
		super(left_up, right_bottom, string);
		backgroundPaint = new Paint();
		backgroundPaint.setColor(color);
		pressedPaint = new Paint();
		pressedPaint.setColor(Color.argb(128, 128, 128, 128));
		pressed = false;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(rect, backgroundPaint);
		if (pressed) {
			canvas.drawRect(rect, pressedPaint);
			getPressedNinePatch().draw(canvas, rect);
		} else
			getReleasedNinePatch().draw(canvas, rect);
		textPainter.setTextSize(rect.height() * 2 / 3);
		textPainter.setColor(Color.BLACK);
		canvas.drawText(title.getString(),
				rect.left + (rect.width() - textPainter.measureText(title.getString()))
						/ 2, rect.bottom - rect.height() / 4, textPainter);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (!isInsideOf(RelativePoint.getRelativePoint(e.getX(), e.getY())))
			return pressed = false;
		if (null == task)
			return false;
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			pressed = true;
			break;
		case MotionEvent.ACTION_UP:
			pressed = false;
			task.task();
			break;
		}
		return true;
	}
}
