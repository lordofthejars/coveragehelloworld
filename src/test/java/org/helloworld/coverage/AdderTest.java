package org.helloworld.coverage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdderTest {

    @Test
    public void should_add_two_numbers() {
        Adder adder = new Adder();

        int result = adder.add(1, 1);

        assertEquals(2, result);

    }

}
