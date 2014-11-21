package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class ScreenBlocker extends GuiItem {

	@Override
	public void draw(Canvas canvas) {
		// ��һ����ɫ�Ķ�����ס��Ļ
		canvas.drawARGB(128, 0, 0, 0);
	}

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return true;
	}

}
