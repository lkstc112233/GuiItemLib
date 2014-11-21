package net.muststudio.util.guiitemlib.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class InputMethodServiceHolder {
	private static InputMethodManager input;

	public static void init(Context context) {
		input = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	public static InputMethodManager getInputMethodManager() {
		return input;
	}
}
