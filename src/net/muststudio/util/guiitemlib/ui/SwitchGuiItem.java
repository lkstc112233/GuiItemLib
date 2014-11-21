package net.muststudio.util.guiitemlib.ui;

import net.muststudio.util.guiitemlib.ui.GenericButton.Task;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class SwitchGuiItem extends SquareGuiItem {
	private GuiItemContainer containerOn;
	private StringHolder onString;
	private StringHolder offString;
	private GuiItemContainer containerOff;
	private boolean switchStatus;

	public SwitchGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		super(left_up, right_bottom);
		GenericButton btn;
		containerOn = new GuiItemContainer();
		containerOn.addToList(new BackgroundPainterPureColorRoundSquare(mainPosition,
				subPosition).setColor(Color.rgb(0x66, 0xcc, 0xff)));
		containerOn.addToList(new TextPainter(RelativePoint.getRelativePoint(
				(mainPosition.getScreenX() + 2 * subPosition.getScreenX()) / 3,
				mainPosition.getScreenY()), subPosition).setHorizontalCenter(true)
				.setVerticalCenter(true).setString("On").setTextCount(3)
				.setPaintBackground(false));
		btn = new GenericButton(mainPosition, RelativePoint.getRelativePoint(
				(mainPosition.getScreenX() + 2 * subPosition.getScreenX()) / 3,
				subPosition.getScreenY())).setTask(new Task() {
			@Override
			public void task() {
				turnSwitch();
			}
		});
		onString = btn.getStringHolder();
		containerOn.addToList(btn);
		containerOff = new GuiItemContainer();
		containerOff.addToList(new BackgroundPainterPureColorRoundSquare(mainPosition,
				subPosition));
		containerOff.addToList(new TextPainter(RelativePoint.getRelativePoint(
				(mainPosition.getScreenX() * 2 + subPosition.getScreenX()) / 3,
				subPosition.getScreenY()), mainPosition).setHorizontalCenter(true)
				.setVerticalCenter(true).setString("Off").setTextCount(3)
				.setPaintBackground(false));
		btn = new GenericButton(subPosition, RelativePoint.getRelativePoint(
				(mainPosition.getScreenX() * 2 + subPosition.getScreenX()) / 3,
				mainPosition.getScreenY())).setTask(new Task() {
			@Override
			public void task() {
				turnSwitch();
			}
		});
		offString = btn.getStringHolder();
		containerOff.addToList(btn);

	}

	public SwitchGuiItem turnSwitch() {
		switchStatus = !switchStatus;
		return this;
	}

	public SwitchGuiItem turnSwitch(boolean status) {
		switchStatus = status;
		return this;
	}

	public SwitchGuiItem setSwitchHint(String str) {
		onString.setString(str);
		offString.setString(str);
		return this;
	}

	public boolean getSwitchStatus() {
		return switchStatus;
	}

	public SwitchGuiItem setSwitchStatus(boolean status) {
		switchStatus = status;
		return this;
	}

	@Override
	public void draw(Canvas canvas) {
		if (switchStatus)
			containerOn.draw(canvas);
		else
			containerOff.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (switchStatus)
			return containerOn.onTouchEvent(e);
		else
			return containerOff.onTouchEvent(e);
	}
}
