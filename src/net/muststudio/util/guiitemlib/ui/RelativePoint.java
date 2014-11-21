package net.muststudio.util.guiitemlib.ui;

public class RelativePoint {
	public static RelativePoint getRelativePoint(float f, float g) {
		double myx = ((double) f) / ScreenInfo.getScreenInfo().getScreenWidth();
		double myy = ((double) g) / ScreenInfo.getScreenInfo().getScreenWidth();
		return new RelativePoint(myx, myy, true);
	}

	public double x;
	public double y;
	public boolean upRelative;
	public boolean RelativeY;

	public RelativePoint(double x, double y, boolean upRel, boolean relY) {
		this.x = x;
		this.y = y;
		upRelative = upRel;
		RelativeY = relY;
	}

	public RelativePoint(double x, double y, boolean upRel) {
		this(x, y, upRel, false);
	}

	public RelativePoint(double x, double y) {
		this(x, y, true);
	}

	public int getScreenX() {
		int thx = (int) (x * ScreenInfo.getScreenInfo().getScreenWidth());
		return thx;
	}

	public int getScreenY() {
		int thy;
		if (RelativeY)
			thy = (int) (y * ScreenInfo.getScreenInfo().getScreenHeight());
		else
			thy = (int) (y * ScreenInfo.getScreenInfo().getScreenWidth());
		if (upRelative)
			return thy;
		else
			return ScreenInfo.getScreenInfo().getScreenHeight() - thy;
	}

	public int getScreenY(boolean upBased) {
		if (upBased)
			return getScreenY();
		else
			return ScreenInfo.getScreenInfo().getScreenHeight() - getScreenY();
	}

	public double getRelativeX() {
		return x;
	}

	public double getRelativeY() {
		double thy;
		double screenHeight = ((double) ScreenInfo.getScreenInfo().getScreenHeight())
				/ ((double) ScreenInfo.getScreenInfo().getScreenWidth());
		if (RelativeY)
			thy = y * screenHeight;
		else
			thy = y;
		if (upRelative)
			return thy;
		else
			return screenHeight - thy;
	}

	public double getRelativeY(boolean upBased) {
		if (upBased)
			return getRelativeY();
		else
			return ((double) ScreenInfo.getScreenInfo().getScreenHeight())
					/ ((double) ScreenInfo.getScreenInfo().getScreenWidth()) - getRelativeY();

	}
}
