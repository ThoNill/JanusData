package org.janus.actions;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.helper.DebugAssistent;

public class PredicateAction implements Action {
    private Predicate predicate;
    private Action child;

    public PredicateAction(Predicate predicate, Action child) {
        super();
        DebugAssistent.doNullCheck(predicate, child);

        this.predicate = predicate;
        this.child = child;
    }

    @Override
    public void configure(DataDescription description) {
        DebugAssistent.doNullCheck(description);

        child.configure(description);

    }

    @Override
    public void perform(DataContext context) {
        DebugAssistent.doNullCheck(context);

        if (predicate.hasPredicate(context)) {
            child.perform(context);
        }

    }

}
