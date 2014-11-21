package net.muststudio.util.guiitemlib.view;

import net.muststudio.util.guiitemlib.ui.GuiItem;
import net.muststudio.util.guiitemlib.ui.ScreenInfo;
import net.muststudio.util.guiitemlib.util.BitmapHolder;
import net.muststudio.util.guiitemlib.util.InputMethodServiceHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public abstract class SurfaceViewFrame extends SurfaceView implements Callback, Runnable {
	class MyInputConnection extends BaseInputConnection {

		public MyInputConnection(SurfaceView targetView, boolean fullEditor) {
			super(targetView, fullEditor);
		}

		@Override
		public boolean commitText(CharSequence text, int newCursorPosition) {
			guiItem.onTextEvent(text);
			return true;
		}

		@Override
		public boolean sendKeyEvent(KeyEvent e) {
			if (e.getAction() == KeyEvent.ACTION_DOWN)
				guiItem.onDeleteEvent(1);
			if (e.getAction() == KeyEvent.ACTION_MULTIPLE)
				guiItem.onDeleteEvent(1);
			return true;
		}
	}

	protected Thread mainLoopThread;
	protected boolean mainLoopThreadRunningFlag;
	protected SurfaceHolder sfh;
	protected GuiItem guiItem;
	private boolean uninited = true;

	public SurfaceViewFrame(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		InputMethodServiceHolder.init(context);
		GuiItem.setView(this);

		BitmapHolder.getInstance(context);

		setFocusableInTouchMode(true);
		mainLoopThreadRunningFlag = false;
	}

	@Override
	public void run() {
		while (mainLoopThreadRunningFlag) {
			logic();
			draw();
			guiItem.checkState();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		ScreenInfo.getScreenInfo().setSize(this.getWidth(), this.getHeight());
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		ScreenInfo.getScreenInfo().setSize(this.getWidth(), this.getHeight());
		setKeepScreenOn(true);

		if (uninited) {
			initilize();
			uninited = false;
		}
		createSurface();

		mainLoopThreadRunningFlag = true;
		mainLoopThread = new Thread(this);
		mainLoopThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		mainLoopThreadRunningFlag = false;
	}

	protected abstract void logic();

	protected abstract void createSurface();

	protected abstract void initilize();

	protected void draw() {
		Canvas canvas = sfh.lockCanvas();
		if (null == canvas)
			return;
		try {
			canvas.drawRGB(255, 255, 255);
			guiItem.draw(canvas);
		} finally {
			sfh.unlockCanvasAndPost(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		guiItem.onTouchEvent(event);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			if (guiItem.onBackKey())
				return true;
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
		return new MyInputConnection(this, false);
	}
}
