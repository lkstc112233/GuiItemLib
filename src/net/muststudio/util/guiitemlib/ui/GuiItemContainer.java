package net.muststudio.util.guiitemlib.ui;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GuiItemContainer extends GuiItem {
	protected ArrayList<GuiItem> guiItems;

	public GuiItemContainer() {
		guiItems = new ArrayList<GuiItem>();
	}

	@Override
	public void draw(Canvas canvas) {
		for (GuiItem i : guiItems)
			i.draw(canvas);
		return;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		for (int i = guiItems.size() - 1; i >= 0; --i)
			if (guiItems.get(i).onTouchEvent(e))
				return true;
		return false;
	}

	@Override
	public boolean isInsideOf(RelativePoint rp) {
		for (int i = guiItems.size() - 1; i >= 0; --i)
			if (guiItems.get(i).isInsideOf(rp))
				return true;
		return false;
	}

	@Override
	public boolean onBackKey() {
		for (int i = guiItems.size() - 1; i >= 0; --i)
			if (guiItems.get(i).onBackKey())
				return true;
		return false;
	}

	public void addToList(GuiItem toAdd) {
		guiItems.add(toAdd);
	}

	public void removeFromList(GuiItem toRemove) {
		guiItems.remove(toRemove);
	}

	public void inwardAddToList() {
		boolean added = true;
		GuiItem adder;
		loop: while (added) {
			added = false;
			for (GuiItem X : guiItems)
				if ((adder = X.getAddition()) != null) {
					guiItems.add(adder);
					added = true;
					continue loop;
				}
		}
	}

	public void removeToRemoves() {
		boolean removed = true;
		loop: while (removed) {
			removed = false;
			for (GuiItem X : guiItems)
				if (X.isToRemove()) {
					removeFromList(X);
					continue loop;
				}
		}
	}

	@Override
	public boolean checkState() {
		inwardAddToList();
		removeToRemoves();
		for (int i = guiItems.size() - 1; i >= 0; --i)
			if (!guiItems.get(i).checkState())
				return true;
		return true;
	}

	@Override
	public boolean onTextEvent(CharSequence text) {
		for (int i = guiItems.size() - 1; i >= 0; --i)
			if (guiItems.get(i).onTextEvent(text))
				return true;
		return false;
	}

	@Override
	public boolean onDeleteEvent(int sum) {
		for (int i = guiItems.size() - 1; i >= 0; --i)
			if (guiItems.get(i).onDeleteEvent(sum))
				return true;
		return false;
	}
}
