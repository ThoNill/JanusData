package org.janus.actions;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class GeneralDataType extends Object implements DataType {
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
			Locale.GERMANY);
	NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

	public static final GeneralDataType general = new GeneralDataType();

	public GeneralDataType() {
	}

	@Override
	public String convert2String(Object obj) {
		if (obj == null) {
			return "";
		}
		String text = "";
		if (obj instanceof Date) {
			synchronized (df) {
				text = df.format(obj);
			}
		} else if (obj instanceof BigDecimal) {
			synchronized (nf) {
				text = nf.format(obj);
			}
		} else {
			text = (obj == null) ? "" : obj.toString().trim();
		}
		return text;
	}

	@Override
	public Object createInitialization() {
		return null;
	}

}
