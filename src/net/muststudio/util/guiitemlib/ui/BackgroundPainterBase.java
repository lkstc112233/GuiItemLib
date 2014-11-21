package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class BackgroundPainterBase extends SquareGuiItem {
	protected Rect location;

	public BackgroundPainterBase(RelativePoint left_up, RelativePoint right_bottom) {
		super(left_up, right_bottom);

		location = new Rect(mainPosition.getScreenX(), mainPosition.getScreenY(),
				subPosition.getScreenX(), subPosition.getScreenY());
	}

	public BackgroundPainterBase() {
		this(new RelativePoint(0, 0), new RelativePoint(1, 0, false));
	}

	@Override
	public abstract void draw(Canvas canvas);

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		return false;
	}
}
