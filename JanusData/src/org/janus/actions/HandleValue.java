package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public class HandleValue implements DataValue, Action, Serializable{
	private int index = -1;
	private String name;
	private DataType type;

	public HandleValue(String name) {
		super();
		DebugAssistent.doNullCheck(name);
		this.name = name;
		setType(GeneralDataType.general);
	}

	@Override
	public DataType getType() {
		return type;
	}

	@Override
	public DataValue setType(DataType type) {
		DebugAssistent.doNullCheck(type);

		this.type = type;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void configure(DataDescription description) {
		DebugAssistent.doNullCheck(description);

		if (index < 0) {
			index = description.getHandle(name);
		}
	}

	@Override
	public void setObject(DataContext ctx, Serializable value) {
		DebugAssistent.doNullCheck(ctx);

		configure(ctx.getDataDescription());
		ctx.setObject(index, value);
	}

	@Override
	public Serializable getObject(DataContext ctx) {
		DebugAssistent.doNullCheck(ctx);

		if (index < 0) {
			configure(ctx.getDataDescription());
		}
		return ctx.getObject(index);
	}

	@Override
	public void perform(DataContext context) {
		DebugAssistent.doNullCheck(context);

		setObject(context, type.createInitialization());
	}

}
