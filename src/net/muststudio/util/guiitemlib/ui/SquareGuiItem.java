package net.muststudio.util.guiitemlib.ui;

import android.graphics.Rect;
import android.graphics.RectF;

public abstract class SquareGuiItem extends GuiItem {
	protected RelativePoint mainPosition;
	protected RelativePoint subPosition;
	protected Rect guiItemSquareRect;
	protected RectF guiItemSquareRectF;

	public SquareGuiItem(SquareGuiItem cpy) {
		this(cpy.mainPosition, cpy.subPosition);
	}

	public SquareGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		if (left_up.getRelativeX() > right_bottom.getRelativeX()) {
			if (left_up.getRelativeY() > right_bottom.getRelativeY()) {
				mainPosition = right_bottom;
				subPosition = left_up;
			} else {
				mainPosition = new RelativePoint(right_bottom.getRelativeX(),
						left_up.getRelativeY());
				subPosition = new RelativePoint(left_up.getRelativeX(),
						right_bottom.getRelativeY());
			}

		} else if (left_up.getRelativeY() < right_bottom.getRelativeY()) {
			mainPosition = left_up;
			subPosition = right_bottom;
		} else {
			mainPosition = new RelativePoint(left_up.getRelativeX(),
					right_bottom.getRelativeY());
			subPosition = new RelativePoint(right_bottom.getRelativeX(),
					left_up.getRelativeY());
		}
		guiItemSquareRect = new Rect(mainPosition.getScreenX(),
				mainPosition.getScreenY(), subPosition.getScreenX(),
				subPosition.getScreenY());
		guiItemSquareRectF = new RectF(mainPosition.getScreenX(),
				mainPosition.getScreenY(), subPosition.getScreenX(),
				subPosition.getScreenY());
	}

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		if (rp.getScreenX() <= mainPosition.getScreenX())
			return false;
		if (rp.getScreenX() >= subPosition.getScreenX())
			return false;
		if (rp.getScreenY() <= mainPosition.getScreenY())
			return false;
		if (rp.getScreenY() >= subPosition.getScreenY())
			return false;
		return true;
	}

}
