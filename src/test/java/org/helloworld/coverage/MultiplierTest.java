package org.helloworld.coverage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplierTest {

    @Test
    public void should_multiply_two_numbers() {

        Multiplier multiplier = new Multiplier();

        int result = multiplier.multiply(2, 2);

        assertEquals(4, result);

    }

}
