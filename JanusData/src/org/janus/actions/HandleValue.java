package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public class HandleValue extends ReferenzToValue {

    private String name;
    int index;

    public HandleValue(String name) {
        this(name, null);
    }

    public HandleValue(String name, Serializable obj) {
        super(null, obj);
        DebugAssistent.doNullCheck(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void configure(DataDescription description) {
        DebugAssistent.doNullCheck(description);
        index = description.getHandle(name);
    }

    @Override
    public void setObject(DataContext ctx, Serializable v) {
        if (getValue() != null) {
            super.setObject(ctx, v);
        } else {
            ctx.setObject(index, v);
        }
    }

    @Override
    public Serializable getObject(DataContext ctx) {
        if (getValue() != null) {
            return super.getObject(ctx);
        } else {
            return ctx.getObject(index);
        }
    }

}
