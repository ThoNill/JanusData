package org.janus.actions;

import java.math.BigDecimal;

public class BigDecimalType extends GeneralDataType {

	public static BigDecimalType decimal = new BigDecimalType();

	public BigDecimalType() {
	}

	@Override
	public Object createInitialization() {
		return BigDecimal.ZERO;
	}

}
