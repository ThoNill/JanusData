package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.data.NeedDataInContext;
import org.janus.helper.DebugAssistent;
import org.janus.single.ObjectCreator;
import org.janus.single.ObjectFunction;
import org.janus.single.ObjectRead;

public  class DefaultValue implements DataValue, Serializable, Action,NeedDataInContext{
	private int index = -1;
	private ObjectCreator creator;
	private DataFormat format;
	

	public DefaultValue(ObjectCreator creator) {
		super();
		this.creator = creator;
		setFormat(GeneralDataFormat.general);
	}

	


	@Override
	public DataFormat getFormat() {
		return format;
	}



	public void setFormat(DataFormat format) {
		this.format = format;
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
		configure(ctx.getDataDescription());
		
		Serializable obj = ifNullThenCreate(ctx);
		return obj;
	}




	protected Serializable ifNullThenCreate(DataContext ctx) {
		Serializable obj = ctx.getObject(index);
		if (obj == null) {
			if (creator != null) {
				obj =  creator.create();
				ctx.setObject(index,obj);
			};
		}
		return obj;
	}

	@Override
	public void perform(DataContext context) {
		DebugAssistent.doNullCheck(context);
		configure(context.getDataDescription());
		Object obj = context.getObject(index);
		if (obj == null && creator != null) {
			context.setObject(index, creator.create());
		}

	}	


	public void configure(DataDescription description) {
		DebugAssistent.doNullCheck(description);
	}

	
	@Override
	public void setContextIndex(int index) {
		this.index = index;
		
	}

	@Override
	public int getContextIndex() {
		return index;
	}




	protected void setCreator(ObjectCreator creator) {
		this.creator = creator;
	}


	
}
