package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.data.NeedDataInContext;
import org.janus.helper.DebugAssistent;
import org.janus.single.ObjectCreator;
import org.janus.single.ObjectFunction;
import org.janus.single.ObjectRead;
import org.janus.single.ObjectWrite;

public  class ReferenzToValue implements DataValue, Serializable, Action {
	private Object activeObject;
	private DataValue value;
	

	public ReferenzToValue(DataValue value,Object obj) {
		super();
		activeObject = obj;
		this.value = value;
	}

	@Override
	public DataFormat getFormat() {
		if (value == null) {
			return new GeneralDataFormat();
		}
		return value.getFormat();
	}

	public void setFormat(DataFormat format) {
		value.setFormat(format);
	}
	
	public void setActiveObject(Object obj) {
		DebugAssistent.doNullCheck(obj);
		
		this.activeObject = obj;
	}
	
	private ObjectFunction getFuction() {
		if (activeObject instanceof ObjectFunction)
			return (ObjectFunction)activeObject;
		return null;
	}
	
	private ObjectRead getRead() {
		if (activeObject instanceof ObjectRead)
			return (ObjectRead)activeObject;
		return null;
	}
	
	private ObjectWrite getWrite() {
		if (activeObject instanceof ObjectWrite)
			return (ObjectWrite)activeObject;
		return null;
	}
	
	private ObjectCreator getCreator() {
		if (activeObject instanceof ObjectCreator)
			return (ObjectCreator)activeObject;
		return null;
	}

	

	@Override
	public void setObject(DataContext ctx, Serializable v) {
		DebugAssistent.doNullCheck(ctx);
		configure(ctx.getDataDescription());
		Serializable obj = value.getObject(ctx);
		
		ObjectWrite write = getWrite();
		if (write != null) {
			write.setValue(obj, v);
		}
		
	}

	@Override
	public Serializable getObject(DataContext ctx) {
		DebugAssistent.doNullCheck(ctx);
		configure(ctx.getDataDescription());
		Serializable obj = value.getObject(ctx);

		ObjectRead read = getRead();
		if (value != null) {
			return read.getValue(obj);
		}
		return obj;
	}


	@Override
	public void perform(DataContext context) {
		DebugAssistent.doNullCheck(context);
		configure(context.getDataDescription());
		ObjectFunction function = getFuction();
		ObjectCreator creator = getCreator();


		
		if (function != null) {
			setObject(context, function.tranform(value.getObject(context)));
		} else {
			if (value instanceof Action) {
				((Action)value).perform(context);
			} else {
				if (creator != null) {
					setObject(context,creator.create());
				}
			}
		}
	}	


	public void configure(DataDescription description) {
	}

	protected DataValue getValue() {
		return value;
	}

	protected void setValue(DataValue value) {
		this.value = value;
	}

	

	
}
