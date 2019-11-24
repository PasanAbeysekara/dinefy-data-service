package com.solution.hangouts.dao.global;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Point
{
	private BigDecimal x;
	private BigDecimal y;
}
