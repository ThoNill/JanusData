package org.janus.actions;

import java.io.Serializable;
import java.math.BigDecimal;

public class BigDecimalType extends GeneralDataType {

	public static BigDecimalType decimal = new BigDecimalType();

	public BigDecimalType() {
	}

	@Override
	public Serializable createInitialization() {
		return BigDecimal.ZERO;
	}

}
