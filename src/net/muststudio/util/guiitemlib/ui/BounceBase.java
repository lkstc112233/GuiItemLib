package net.muststudio.util.guiitemlib.ui;

public abstract class BounceBase extends HopBase {
	public BounceBase(RelativePoint beginPump) {
		super(beginPump);
	}

	protected void move() {
		super.move();
		while (fixX()) {
			velocity.flipX();
			velocity.absorbXEnergy();
		}
		while (fixY()) {
			velocity.flipY();
			velocity.absorbYEnergy();
		}
	}
}
