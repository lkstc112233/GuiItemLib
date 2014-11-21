package net.muststudio.util.guiitemlib.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

public final class BitmapHolder {
	private static BitmapHolder instance;

	public static BitmapHolder getInstance(Context context) {
		if (instance != null)
			return instance;
		return instance = new BitmapHolder(context.getResources());
	}

	public static BitmapHolder getInstance() {
		return instance;
	}

	public BitmapHolder(Resources resources) {
		this.resources = resources;
		formedBitmaps = new SparseArray<Bitmap>();
	}

	private Resources resources;
	private SparseArray<Bitmap> formedBitmaps;

	public Bitmap getBitmap(int id) {
		Bitmap temp = formedBitmaps.get(id);
		if (temp != null)
			return temp;
		try {
			temp = BitmapFactory.decodeResource(resources, id);
		} catch (OutOfMemoryError e) {
			instance = new BitmapHolder(this.resources);
			formedBitmaps = null;
			System.gc();
			return instance.getBitmap(id);
		}
		synchronized (this) {
			formedBitmaps.put(id, temp);
		}
		return temp;
	}

	public void release() {
		synchronized (this) {
			for (int i = 0; i < formedBitmaps.size(); ++i)
				formedBitmaps.valueAt(i).recycle();
			formedBitmaps.clear();
		}
	}
}
