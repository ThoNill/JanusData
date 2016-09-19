package org.janus.actions;

import org.janus.data.DataContext;

public interface Predicate {
    boolean hasPredicate(DataContext context);
}
