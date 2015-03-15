package org.janus.actions;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public abstract class StartRunEnd<K> extends ActionList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252136951514332424L;
	protected String name;
	ActionList beforeRun = new ActionList();
	ActionList afterRun = new ActionList();

	protected abstract void stop(K data) throws Exception;

	protected abstract K start(DataContext ctx) throws Exception;

	protected abstract boolean next(DataContext context, K data)
			throws Exception;

	public StartRunEnd() {
		super();
	}

	@Override
	public void configure(DataDescription description) {
		DebugAssistent.doNullCheck(description);

		super.configure(description);
		beforeRun.configure(description);
		afterRun.configure(description);
	}

	@Override
	public void perform(DataContext context) {
		DebugAssistent.doNullCheck(context);
		configure(context.getDataDescription());
		beforeRun.perform(context);

		Exception ex = null;
		K data = null;
		try {
			data = start(context);
			run(context, data);
		} catch (Exception e) {
			ex = e;
		}
		if (data != null) {
			try {
				stop(data);
			} catch (Exception e1) {
				if (ex == null) {
					ex = e1;
				}
				DebugAssistent.log(e1);
			}
		}
		if (ex != null) {
			throw new RuntimeException(ex);
		}
		afterRun.perform(context);

	}

	public StartRunEnd<K> addBeforeRun(Action action) {
		DebugAssistent.doNullCheck(action);

		beforeRun.addAction(action);
		return this;
	}

	public StartRunEnd<K> removeBeforeRun(Action action) {
		DebugAssistent.doNullCheck(action);

		beforeRun.removeAction(action);
		return this;
	}

	public StartRunEnd<K> addAfterRun(Action action) {
		DebugAssistent.doNullCheck(action);

		afterRun.addAction(action);
		return this;
	}

	public StartRunEnd<K> removeAfterRun(Action action) {
		DebugAssistent.doNullCheck(action);

		afterRun.removeAction(action);
		return this;
	}

	public String getName() {
		return name;
	}

	private void run(DataContext ctx, K data) throws Exception {

		while (next(ctx, data)) {
			super.perform(ctx);
		}
	}

}