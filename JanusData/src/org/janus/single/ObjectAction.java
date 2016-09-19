package org.janus.single;

import java.io.Serializable;

public interface ObjectAction {
    void perform(Serializable obj);
}
