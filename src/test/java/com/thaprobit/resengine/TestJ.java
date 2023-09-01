package com.thaprobit.resengine;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/9/2020 6:20 PM
 */
public class TestJ {
    @Test
    public void testMethod() {
        LocalDate now = LocalDate.now();

        System.out.println(now);

        LocalDate plusDays = now.plusDays(10);
        System.out.println(plusDays);
        System.out.println(now);

    }
}
