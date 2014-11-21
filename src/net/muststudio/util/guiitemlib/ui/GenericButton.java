package net.muststudio.util.guiitemlib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

public class GenericButton extends SquareGuiItem {
	// 需要处理的任务。是一个接口。
	public static interface Task {
		public void task();
	}

	protected Task task;
	protected RectF rect;
	protected StringHolder title;
	protected BackgroundPainterBase background;
	protected Paint textPainter;

	public GenericButton setTask(Task ts) {
		task = ts;
		return this;
	}

	public GenericButton(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, "", new BackgroundPainterPureColorRoundSquare(
				left_up, right_bottom));
	}

	public GenericButton(RelativePoint left_up, RelativePoint right_bottom, String str) {
		this(left_up, right_bottom, str, new BackgroundPainterPureColorRoundSquare(
				left_up, right_bottom));
	}

	public GenericButton(RelativePoint left_up, RelativePoint right_bottom,
			BackgroundPainterBase background) {
		this(left_up, right_bottom, "", background);
	}

	public GenericButton(RelativePoint left_up, RelativePoint right_bottom, int color) {
		this(left_up, right_bottom, "", new BackgroundPainterPureColorRoundSquare(
				left_up, right_bottom).setColor(color));
	}

	public GenericButton(RelativePoint left_up, RelativePoint right_bottom, String str,
			int color) {
		this(left_up, right_bottom, str, new BackgroundPainterPureColorRoundSquare(
				left_up, right_bottom).setColor(color));
	}

	public GenericButton(RelativePoint left_up, RelativePoint right_bottom, String str,
			BackgroundPainterBase background) {
		super(left_up, right_bottom);
		rect = new RectF(mainPosition.getScreenX(), mainPosition.getScreenY(),
				subPosition.getScreenX(), subPosition.getScreenY());
		this.background = background;
		textPainter = new Paint();
		title = new StringHolder("");
		title.setString(str);
	}

	@Override
	public void draw(Canvas canvas) {
		background.draw(canvas);
		textPainter.setTextSize(rect.height() * 2 / 3);
		textPainter.setColor(Color.BLACK);
		canvas.drawText(title.getString(),
				rect.left + (rect.width() - textPainter.measureText(title.getString()))
						/ 2, rect.bottom - rect.height() / 4, textPainter);
	}

	public StringHolder getStringHolder() {
		return title;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (!isInsideOf(RelativePoint.getRelativePoint(e.getX(), e.getY())))
			return false;
		if (null == task)
			return false;
		if (e.getAction() == MotionEvent.ACTION_UP)
			task.task();
		return true;
	}

}
