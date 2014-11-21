package net.muststudio.util.guiitemlib.ui;

import android.view.MotionEvent;
import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import net.muststudio.util.guiitemlib.ui.GuiItemContainer;
import net.muststudio.util.guiitemlib.ui.PressableButton;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.TextPainter;

public class PopupMenuGuiItem extends GuiItemContainer {
	public int itemCount;
	protected double popPositionX;
	protected double popPositionY;
	protected double subPositionX;

	public PopupMenuGuiItem(RelativePoint position) {
		this(position, null);
	}

	public PopupMenuGuiItem(RelativePoint position, String str) {
		popPositionX = position.getRelativeX();
		popPositionY = position.getRelativeY(true);
		subPositionX = popPositionX + 0.3;
		if (subPositionX > 1)
			subPositionX -= 0.6;
		if (str != null) {
			double subPositionY = popPositionY - 0.1;
			if (subPositionY < 0) {
				subPositionY += 0.1;
				popPositionY += 0.1;
			}
			addToList(new TextPainter(new RelativePoint(popPositionX, popPositionY),
					new RelativePoint(subPositionX, subPositionY))
					.setTextCount(str.length()).setString(str).setPaintBackground(true));
		}
		itemCount = 0;
	}

	public PopupMenuGuiItem addUnit(String str, Task task) {
		addToList(new PressableButton(new RelativePoint(popPositionX, popPositionY + 0.1
				* itemCount), new RelativePoint(subPositionX, popPositionY + 0.1
				* (itemCount + 1)), str).setTask(task));
		++itemCount;
		return this;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		super.onTouchEvent(e);
		if (e.getAction() == MotionEvent.ACTION_UP)
			removeThis();
		return true;
	}
}
