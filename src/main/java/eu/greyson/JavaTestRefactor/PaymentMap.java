package eu.greyson.JavaTestRefactor;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


@SuppressWarnings("serial")
public class PaymentMap extends HashMap<String, BigDecimal> {
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		this.forEach((k, v) -> {
			if (v != BigDecimal.ZERO) {
				builder.append("> ");
				builder.append(k);
				builder.append(" ");
				builder.append(v);
				builder.append("\n");
			}
		});

		if (StringUtils.isBlank(builder.toString())) {
			return "";
		}

		return builder.toString();
	}
	
}
