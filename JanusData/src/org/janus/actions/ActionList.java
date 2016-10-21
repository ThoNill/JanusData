package org.janus.actions;

import java.io.Serializable;
import java.util.ArrayList;

import org.janus.data.ConfigurableHelper;
import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public class ActionList extends ArrayList<Action> implements Action, Serializable {

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
        this.add(action);
        return this;
    }

    public ActionList removeAction(Action action) {
        DebugAssistent.doNullCheck(action);

        this.remove(action);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActionList other = (ActionList) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

}
