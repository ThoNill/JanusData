package org.janus.single;

import java.io.Serializable;

public interface ObjectRead extends Serializable{
    Serializable getValue(Serializable obj);

}
