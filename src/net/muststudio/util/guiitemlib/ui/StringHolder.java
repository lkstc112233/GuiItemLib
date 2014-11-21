package net.muststudio.util.guiitemlib.ui;

public final class StringHolder {
	private String stringHolding;

	public StringHolder(String string) {
		stringHolding = string;
	}

	public StringHolder() {
		stringHolding = "";
	}

	public void setString(String string) {
		stringHolding = string;
	}

	public String getString() {
		return stringHolding;
	}

}
