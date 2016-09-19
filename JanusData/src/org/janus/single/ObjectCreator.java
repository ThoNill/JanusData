package org.janus.single;

import java.io.Serializable;

public interface ObjectCreator extends Serializable{
    Serializable create();
}
