package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import net.muststudio.util.guiitemlib.ui.GuiItem;
import net.muststudio.util.guiitemlib.ui.GuiItemContainer;
import net.muststudio.util.guiitemlib.ui.RelativePoint;
import net.muststudio.util.guiitemlib.ui.SquareGuiItem;

public class ListGuiItem extends SquareGuiItem {
	public abstract class ListUnit extends GuiItem {
		protected RectF unitRect;
		protected Paint backgroundPaint;

		public ListUnit() {
			backgroundPaint = new Paint();
			backgroundPaint.setColor(-1);
		}

		public abstract float getHeight();

		public void setRect(float left, float right, float top) {
			unitRect = new RectF(left, top, right, top + getHeight());
		}
	}

	protected class ScreenPoint {
		public float x;
		public float y;

		public ScreenPoint(MotionEvent e) {
			this(e.getX(), e.getY());
		}

		public ScreenPoint(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}
	}

	// // TODO ²âÊÔ
	// private class TextListUnit extends ListUnit {
	// protected String itemText;
	// private Paint textPaint;
	// private Paint rectPaint;
	// private Paint stockePaint;
	// private ColorHolder color;
	//
	// private class ColorHolder {
	// public int color = -1;
	//
	// public void set(int i) {
	// switch (i) {
	// case 1:
	// color = Color.RED;
	// break;
	// case 2:
	// color = Color.GREEN;
	// break;
	// case 3:
	// color = Color.BLUE;
	// break;
	// default:
	// color = Color.WHITE;
	// break;
	// }
	// }
	// }
	//
	// public TextListUnit(String text) {
	// itemText = text;
	// textPaint = new Paint();
	// textPaint.setAntiAlias(true);
	// textPaint.setTextSize(50);
	// rectPaint = new Paint();
	// rectPaint.setColor(-1);
	// color = new ColorHolder();
	// stockePaint = new Paint();
	// stockePaint.setStyle(Style.STROKE);
	// }
	//
	// @Override
	// public void draw(Canvas canvas) {
	// rectPaint.setColor(color.color);
	// canvas.drawRect(unitRect, rectPaint);
	// canvas.drawRect(unitRect, stockePaint);
	// canvas.drawText(itemText, unitRect.left, unitRect.bottom, textPaint);
	// }
	//
	// @Override
	// public float getHeight() {
	// return 50;
	// }
	//
	// @Override
	// public boolean onTouchEvent(MotionEvent e) {
	// if (e.getAction() == MotionEvent.ACTION_UP)
	// if (unitRect.contains(e.getX(), e.getY()))
	// addTo(new PopupMenuGuiItem(RelativePoint.getRelativePoint(e.getX(),
	// e.getY())).addItem("ºìÉ«", new Task() {
	// @Override
	// public void task() {
	// color.set(1);
	// }
	// }).addItem("ÂÌÉ«", new Task() {
	// @Override
	// public void task() {
	// color.set(2);
	// }
	// }).addItem("À¶É«", new Task() {
	// @Override
	// public void task() {
	// color.set(3);
	// }
	// }).addItem("°×É«", new Task() {
	// @Override
	// public void task() {
	// color.set(0);
	// }
	// }));
	// // background = !background;
	// return false;
	// }
	// }

	private MotionOffsetter offset;
	private GuiItemContainer container;
	private ScreenPoint homeEvent;
	private ScreenPoint lastPoint;
	private float itemHeightCount;

	public ListGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		super(left_up, right_bottom);
		clearUnits();
		// // TODO ²âÊÔ
		// for (int i = 0; i < 120; ++i)
		// addUnit(new TextListUnit("²âÊÔÔªËØ" + i));
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (guiItemSquareRectF.contains(e.getX(), e.getY())) {
				lastPoint = homeEvent = new ScreenPoint(e);
				return true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (homeEvent != null) {
				float dy = Math.abs(e.getY() - homeEvent.getY());
				if (dy > 10)
					homeEvent = null;
				return true;
			}
			if (lastPoint != null) {
				moveList(lastPoint.getY() - e.getY());
				lastPoint = new ScreenPoint(e);
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (lastPoint == null)
				break;
			if (homeEvent != null)
				break;
			lastPoint = homeEvent = null;
			return true;
		}
		offset.adjustMotion(e);
		boolean returner = container.onTouchEvent(e);
		offset.resetMotion(e);
		return returner;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.translate(0, -offset.getDy());
		canvas.clipRect(guiItemSquareRect.left, guiItemSquareRect.top + offset.getDy(),
				guiItemSquareRect.right, guiItemSquareRect.bottom + offset.getDy());
		synchronized (this) {
			container.draw(canvas);
		}
		canvas.restore();
	}

	@Override
	public boolean checkState() {
		synchronized (this) {
			return container.checkState();
		}
	}

	protected void moveList(float dy) {
		offset.moveDy(dy);
		float y = offset.getDy();
		if (y < 0)
			offset.setDy(0);
		if (y > itemHeightCount - guiItemSquareRect.height())
			if (itemHeightCount - guiItemSquareRect.height() > 0)
				offset.setDy(itemHeightCount - guiItemSquareRect.height());
			else
				offset.setDy(0);
	}

	public void addUnit(ListUnit unit) {
		unit.setRect(guiItemSquareRect.left, guiItemSquareRect.right, itemHeightCount);
		itemHeightCount += unit.getHeight();
		synchronized (this) {
			container.addToList(unit);
		}
	}

	public void clearUnits() {
		offset = new MotionOffsetter();
		container = new GuiItemContainer();
		itemHeightCount = 0;
	}
}
