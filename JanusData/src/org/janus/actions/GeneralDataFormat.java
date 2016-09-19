package org.janus.actions;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class GeneralDataFormat implements DataFormat {
    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
            Locale.GERMANY);
    NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

    public static final GeneralDataFormat general = new GeneralDataFormat();

    GeneralDataFormat() {
    }

    @Override
    public String format(Object obj) {
        if (obj == null) {
            return "";
        }
        String text ;
        if (obj instanceof Date) {
            synchronized (df) {
                text = df.format(obj);
            }
        } else if (obj instanceof BigDecimal) {
            synchronized (nf) {
                text = nf.format(obj);
            }
        } else {
            text = obj.toString().trim();
        }
        return text;
    }

}
