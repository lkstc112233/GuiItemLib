package net.muststudio.util.guiitemlib.ui;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.view.MotionEvent;

import net.muststudio.util.guiitemlib.ui.RelativePoint;

public class FileListGuiItem extends ListGuiItem {
	protected class FileUnit extends ListUnit {
		private File file;
		private Paint textPaint;

		public FileUnit(File file) {
			this.file = file;
			textPaint = new Paint();
			textPaint.setTextSize(40);
			textPaint.setAntiAlias(true);
			checkColor();
		}

		@Override
		public float getHeight() {
			return 50;
		}

		@Override
		public void draw(Canvas canvas) {
			checkColor();
			canvas.drawRect(unitRect, backgroundPaint);
			canvas.drawText(file.getName(), unitRect.left, unitRect.bottom - 10,
					textPaint);
		}

		private void checkColor() {
			if (file.isDirectory())
				backgroundPaint.setColor(Color.WHITE);
			else if (file.isFile())
				if (file != fileSelected)
					backgroundPaint.setColor(Color.RED);
				else
					backgroundPaint.setColor(Color.GREEN);
		}

		@Override
		public boolean onTouchEvent(MotionEvent e) {
			if (e.getAction() == MotionEvent.ACTION_UP)
				if (unitRect.contains(e.getX(), e.getY()))
					selectFile(file);
			return false;
		}
	}

	protected static FileFilter directoryFilter;
	protected static FileFilter fileFilter;

	protected static FileFilter getDirectoryFilter() {
		if (directoryFilter == null)
			directoryFilter = new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			};
		return directoryFilter;
	}

	protected static FileFilter getStaticFileFilter() {
		if (fileFilter == null)
			fileFilter = new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isFile();
				}
			};
		return fileFilter;
	}

	protected FileFilter getFileFilter() {
		return getStaticFileFilter();
	}

	protected File fileSelected;
	protected File folderOperating;
	private File rootFile;

	public FileListGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, Environment.getExternalStorageDirectory());
	}

	public FileListGuiItem(RelativePoint left_up, RelativePoint right_bottom,
			File rootFile) {
		super(left_up, right_bottom);
		this.rootFile = rootFile;
		selectFile(rootFile);
	}

	protected void refresh() {
		clearUnits();
		File[] files = folderOperating.listFiles(getDirectoryFilter());
		Arrays.sort(files);
		for (File f : files)
			addUnit(new FileUnit(f));
		files = folderOperating.listFiles(getFileFilter());
		Arrays.sort(files);
		for (File f : files)
			addUnit(new FileUnit(f));
	}

	private void selectFile(File file) {
		if (file.isDirectory()) {
			fileSelected = null;
			folderOperating = file;
			refresh();
		} else if (file.isFile()) {
			fileSelected = file;
		}
	}

	@Override
	public boolean onBackKey() {
		if (!folderOperating.getAbsolutePath().equals(rootFile.getAbsolutePath())) {
			folderOperating = folderOperating.getParentFile();
			refresh();
		}
		return true;
	}

	public File getSelectedFile() {
		return fileSelected;
	}
}
