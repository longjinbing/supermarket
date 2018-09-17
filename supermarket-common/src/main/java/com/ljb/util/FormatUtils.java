package com.ljb.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatUtils {

	public static BigDecimal ConvertDecimal(Object target) {
		DecimalFormat dFormat=new DecimalFormat();
		dFormat.setMaximumFractionDigits(2);
    	dFormat.setMinimumFractionDigits(2);
    	return new BigDecimal(dFormat.format(target));
	}
}
