package net.muststudio.util.guiitemlib.ui;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;

import android.os.Environment;

import net.muststudio.util.guiitemlib.ui.RelativePoint;

public class FiltedFileListGuiItem extends FileListGuiItem implements FileFilter {
	protected class FileNameFilter {
		String extension;

		@Override
		public boolean equals(Object object) {
			if (this == object)
				return true;
			if (object instanceof FileNameFilter) {
				return extension.equalsIgnoreCase(((FileNameFilter) object).extension);
			} else
				return false;
		}

		@Override
		public int hashCode() {
			return extension.hashCode();
		}

		public FileNameFilter(String extension) {
			this.extension = extension;
		}

		public boolean fits(File file) {
			String[] name = file.getName().split("\\.");
			if (name.length < 2)
				return false;
			if (name[name.length - 1].equalsIgnoreCase(extension))
				return true;
			return false;
		}

		public boolean fits(String fileName) {
			String[] name = fileName.split("\\.");
			if (name.length < 2)
				return false;
			if (name[name.length - 1].equalsIgnoreCase(extension))
				return true;
			return false;
		}
	}

	protected HashSet<FileNameFilter> filters;

	public FiltedFileListGuiItem(RelativePoint left_up, RelativePoint right_bottom) {
		this(left_up, right_bottom, Environment.getExternalStorageDirectory());
	}

	public FiltedFileListGuiItem(RelativePoint left_up, RelativePoint right_bottom,
			File rootFile) {
		super(left_up, right_bottom, rootFile);
		filters = new HashSet<FileNameFilter>();
	}

	@Override
	public boolean accept(File file) {
		if (filters != null)
			for (FileNameFilter f : filters)
				if (f.fits(file))
					return true;
		return false;
	}

	@Override
	protected FileFilter getFileFilter() {
		return this;
	}

	public FiltedFileListGuiItem addFliter(String extension) {
		filters.add(new FileNameFilter(extension));
		refresh();
		return this;
	}
}
