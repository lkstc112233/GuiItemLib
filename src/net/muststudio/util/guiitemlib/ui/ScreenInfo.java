package net.muststudio.util.guiitemlib.ui;

public class ScreenInfo {
	private static ScreenInfo screenInfoStorage;

	public static ScreenInfo getScreenInfo() {
		if (null == screenInfoStorage)
			screenInfoStorage = new ScreenInfo();
		return screenInfoStorage;
	}

	private int screenW;
	private int screenH;

	private boolean initialized;

	public int getScreenWidth() {
		if (initialized)
			return screenW;
		else
			throw (new IllegalStateException());
	}

	public int getScreenHeight() {
		if (initialized)
			return screenH;
		else
			throw (new IllegalStateException());
	}

	private ScreenInfo() {
		initialized = false;
	}// Hide Constructor to public.

	public void setSize(int width, int height) {
		screenW = width;
		screenH = height;
		initialized = true;
	}
}
