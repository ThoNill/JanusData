package org.janus.actions;

import java.io.Serializable;
import java.math.BigDecimal;

import org.janus.single.ObjectCreator;

public class BigDecimalType implements ObjectCreator {

	public static BigDecimalType decimal = new BigDecimalType();

	public BigDecimalType() {
	}

	@Override
	public Serializable create() {
		return BigDecimal.ZERO;
	}

}
