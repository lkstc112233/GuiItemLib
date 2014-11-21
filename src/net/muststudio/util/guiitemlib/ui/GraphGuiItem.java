package net.muststudio.util.guiitemlib.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GraphGuiItem extends SquareGuiItem {

	protected Bitmap texture;
	protected Rect destPos;

	public void setTexture(Bitmap textureIn) {
		texture = textureIn;
	}

	public GraphGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		super(left_up, right_bottom);
		destPos = new Rect();
		destPos.set(mainPosition.getScreenX(), mainPosition.getScreenY(),
				subPosition.getScreenX(), subPosition.getScreenY());
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(texture, null, destPos, null);
	}

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		return false;
	}
}
