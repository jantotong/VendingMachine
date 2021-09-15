/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.math.*;
import java.util.*;

/**
 *
 * @author Noah McElroy
 */
public class Change {

    private BigDecimal total;
    private BigDecimal quarterValue = new BigDecimal("25");
    private BigDecimal dimeValue = new BigDecimal("10");
    private BigDecimal nickelValue = new BigDecimal("5");

    Change (BigDecimal total){
        this.total = total;
    }

    private enum changesType {
        QUARTERS, DIMES, NICKELS, PENNIES
    }

    public Map<changesType, BigDecimal> exchangeTotal(){
        BigDecimal current = total;
        Map<changesType, BigDecimal> pocket = new HashMap<>();

        pocket.put(changesType.QUARTERS, current.divide(quarterValue, 0, RoundingMode.DOWN));
        current = current.remainder(quarterValue);
        pocket.put(changesType.DIMES, current.divide(dimeValue, 0, RoundingMode.DOWN));
        current = current.remainder(dimeValue);
        pocket.put(changesType.NICKELS, current.divide(nickelValue, 0, RoundingMode.DOWN));
        current = current.remainder(nickelValue);
        pocket.put(changesType.PENNIES, current);
        return pocket;
    }

}
