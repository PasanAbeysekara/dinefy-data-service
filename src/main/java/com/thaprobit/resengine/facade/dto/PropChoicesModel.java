package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.PropChoiceID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Tharindu Aththanayake
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropChoicesModel {
    private PropChoiceID propChoiceId;
    private String name;
    private String description;
    private BigDecimal amount;
    private String amountCurrency;
    private ChoiceModel sysChoice;
}
