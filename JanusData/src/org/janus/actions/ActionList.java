package org.janus.actions;

import java.util.Vector;

import org.janus.data.ConfigurableHelper;
import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public class ActionList extends Vector<Action> implements Action {

	private static final long serialVersionUID = 1L;
	private DataDescription description;

	public ActionList() {
		super();
	}

	@Override
	public void configure(DataDescription description) {
		DebugAssistent.doNullCheck(description);

		if (DescriptionIsNew(description)) {
			this.description = description;
			ConfigurableHelper.configure(description, this);
		}
	}

	private boolean DescriptionIsNew(DataDescription description) {
		return this.description == null
				|| !this.description.equals(description);
	}

	@Override
	public void perform(DataContext context) {
		DebugAssistent.doNullCheck(context);

		if (description == null) {
			configure(context.getDataDescription());
		}
		for (Action action : this) {
			action.perform(context);
		}
	}
	
	public ActionList addAction(Action action) {
		DebugAssistent.doNullCheck(action);

		if (description != null) {
			action.configure(description);
		}
		this.addElement(action);
		return this;
	}

	public ActionList removeAction(Action action) {
		DebugAssistent.doNullCheck(action);

		this.removeElement(action);
		return this;
	}

	

}
