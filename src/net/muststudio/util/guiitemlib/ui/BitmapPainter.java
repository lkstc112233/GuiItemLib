package net.muststudio.util.guiitemlib.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BitmapPainter extends SquareGuiItem {
	protected Rect bitmapPosition;
	protected Bitmap bitmap;

	public BitmapPainter(RelativePoint left_up, RelativePoint right_bottom) {
		super(left_up, right_bottom);
		bitmapPosition = new Rect(mainPosition.getScreenX(), mainPosition.getScreenY(),
				subPosition.getScreenX(), subPosition.getScreenY());
	}

	public BitmapPainter setBitmap(Bitmap bmp) {
		bitmap = bmp;
		return this;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, bitmapPosition, null);
	}

}
