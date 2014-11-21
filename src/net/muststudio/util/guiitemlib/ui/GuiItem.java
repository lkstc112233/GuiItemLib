package net.muststudio.util.guiitemlib.ui;

import net.muststudio.util.guiitemlib.util.InputMethodServiceHolder;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public abstract class GuiItem {
	// [folding] ����ת�����
	private GuiItem toAdd = null;
	private boolean removeThis = false;

	protected void addTo(GuiItem giToAdd) {
		if (toAdd == null)
			toAdd = giToAdd;
		else
			toAdd.addTo(giToAdd);
	}

	public GuiItem getAddition() {
		GuiItem add = null;
		if (toAdd != null) {
			add = toAdd;
			toAdd = null;
		}
		return add;
	}

	protected void removeThis() {
		removeThis = true;
	}

	public boolean isToRemove() {
		return removeThis;
	}

	// [end]
	// [folding] ���뷨���
	protected static View fatherView;

	public static void setView(View view) {
		fatherView = view;
	}

	protected void showInputMethod() {
		InputMethodServiceHolder.getInputMethodManager().showSoftInput(fatherView, 0);
		// InputMethodManager.SHOW_FORCED);
	}

	protected void hideInputMethod() {
		InputMethodServiceHolder.getInputMethodManager().hideSoftInputFromWindow(
				fatherView.getWindowToken(), 0);
	}

	protected void toggleInputMethod() {
		InputMethodServiceHolder.getInputMethodManager().toggleSoftInput(0, 0);
	}

	public boolean onTextEvent(CharSequence text) {
		return false;
	}

	public boolean onDeleteEvent(int sum) {
		return false;
	}

	// [end]
	public abstract void draw(Canvas canvas);

	public boolean onTouchEvent(MotionEvent e) {
		return false;
	}

	public boolean isInsideOf(RelativePoint rp) {
		return false;
	}

	public boolean onBackKey() {
		return false;
	}

	// �˴�����True��ʾ�����㲥������False��ʾ��ֹ�㲥��
	public boolean checkState() {
		return true;
	}
}
