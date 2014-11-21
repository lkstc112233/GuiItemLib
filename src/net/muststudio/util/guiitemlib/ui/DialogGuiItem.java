package net.muststudio.util.guiitemlib.ui;

import net.muststudio.util.guiitemlib.ui.GenericButton.Task;

public class DialogGuiItem extends GuiItemContainer {
	protected static final double buttonTop = 0.29;
	protected static final double buttonBottom = 0.2;
	protected static final double textTop = 0.19;
	protected static final double textBottom = 0.01;
	protected static final boolean upOrDown = true;

	protected class ProcessAndRemove implements Task {
		private Task task;

		public ProcessAndRemove() {
			this(null);
		}

		public ProcessAndRemove(Task tsk) {
			task = tsk;
		}

		@Override
		public void task() {
			if (task != null)
				task.task();
			removeThis();
		}
	}

	public DialogGuiItem(String mainContent) {
		this(mainContent, "我知道了", null);
	}

	public DialogGuiItem(String mainContent, String mainButton) {
		this(mainContent, mainButton, null);
	}

	public DialogGuiItem(String mainContent, String mainButton, Task mainButtonDo) {
		addToList(new ScreenBlocker());
		addToList(new BackgroundPainterPureColorRoundSquare(new RelativePoint(0, 0,
				upOrDown), new RelativePoint(1, 0.3, upOrDown)));
		addToList(new TextPainter(new RelativePoint(0.1, textTop, upOrDown),
				new RelativePoint(0.9, textBottom, upOrDown)).setString(mainContent)
				.setTextCount(12).setHorizontalCenter(true).setVerticalCenter(true));
		addToList(new PressableButton(new RelativePoint(0.1, buttonTop, upOrDown),
				new RelativePoint(0.9, buttonBottom, upOrDown), mainButton)
				.setTask(new ProcessAndRemove(mainButtonDo)));
	}

	public DialogGuiItem(String mainContent, String mainButton, Task mainButtonDo,
			String subButton) {
		this(mainContent, mainButton, mainButtonDo, subButton, null);
	}

	public DialogGuiItem(String mainContent, String mainButton, Task mainButtonDo,
			String subButton, Task subButtonDo) {
		addToList(new ScreenBlocker());
		addToList(new BackgroundPainterPureColorRoundSquare(new RelativePoint(0, 0,
				upOrDown), new RelativePoint(1, 0.3, upOrDown)));
		addToList(new TextPainter(new RelativePoint(0.1, textTop, upOrDown),
				new RelativePoint(0.9, textBottom, upOrDown)).setString(mainContent)
				.setTextCount(12).setHorizontalCenter(true).setVerticalCenter(true));
		addToList(new PressableButton(new RelativePoint(0.1, buttonTop, upOrDown),
				new RelativePoint(0.45, buttonBottom, upOrDown), mainButton)
				.setTask(new ProcessAndRemove(mainButtonDo)));
		addToList(new PressableButton(new RelativePoint(0.55, buttonTop, upOrDown),
				new RelativePoint(0.9, buttonBottom, upOrDown), subButton)
				.setTask(new ProcessAndRemove(subButtonDo)));
	}

	@Override
	public boolean onBackKey() {
		if (super.onBackKey())
			return true;
		removeThis();
		return true;
	}
}
