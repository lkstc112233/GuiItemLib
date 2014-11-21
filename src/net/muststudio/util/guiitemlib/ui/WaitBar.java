package net.muststudio.util.guiitemlib.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class WaitBar extends GuiItem {

	private int currentFrame;

	private Paint color1;
	private Paint color2;

	public WaitBar() {
		currentFrame = 0;
		color1 = new Paint();
		color1.setColor(Color.RED);
		color2 = new Paint();
		color2.setColor(Color.GREEN);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawARGB(128, 0, 0, 0);
		Canvas newCan = new Canvas();
		Bitmap newBit = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(),
				Config.ARGB_8888);
		newCan.setBitmap(newBit);
		newCan.save();
		newCan.rotate(75, newCan.getWidth() / 2, newCan.getHeight() / 2);
		canvas.save();

		canvas.clipRect(10, canvas.getHeight() / 2 - 10, canvas.getWidth() - 10,
				canvas.getHeight() / 2 + 10);

		int fixer = (currentFrame += 4) % 40;

		for (int i = 0 + fixer; i < canvas.getWidth(); i += 40) {
			newCan.drawRect(0 + i, 0, 20 + i, canvas.getHeight(), color1);
		}
		for (int i = 20 + fixer; i < canvas.getWidth(); i += 40) {
			newCan.drawRect(0 + i, 0, 20 + i, canvas.getHeight(), color2);
		}

		newCan.restore();
		canvas.drawBitmap(newBit, 0, 0, null);
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return true;
	}

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		return true;
	}
}
