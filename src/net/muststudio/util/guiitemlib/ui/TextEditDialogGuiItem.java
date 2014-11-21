package net.muststudio.util.guiitemlib.ui;

import net.muststudio.util.guiitemlib.ui.GenericButton.Task;

public class TextEditDialogGuiItem extends DialogGuiItem {
	protected class ConfirmTask implements Task {
		private ConfirmOperation operation;
		private TextEditGuiItem text;

		public ConfirmTask(ConfirmOperation operation, TextEditGuiItem text) {
			this.operation = operation;
			this.text = text;
		}

		@Override
		public void task() {
			if (operation != null)
				if (text != null)
					operation.confirm(text.getText());
				else
					operation.confirm("");
			removeThis();
		}
	}

	public interface ConfirmOperation {
		void confirm(String string);
	}

	public TextEditDialogGuiItem(ConfirmOperation task, String oriString) {
		super("", "确定", null, "取消");
		TextEditGuiItem text = new TextEditGuiItem(new RelativePoint(0.1, textTop,
				upOrDown), new RelativePoint(0.9, textBottom, upOrDown))
				.setText(oriString);
		addToList(new PressableButton(new RelativePoint(0.1, buttonTop, upOrDown),
				new RelativePoint(0.45, buttonBottom, upOrDown), "确定")
				.setTask(new ConfirmTask(task, text)));
		addToList(text);
	}
}
