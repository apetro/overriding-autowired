package io.ghost.apetro.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for HairSplitter.
 */
public class HairSplitterTest {


    /**
     * Verify that the setKnife() counter actually counts setKnife() invocations.
     */
    @Test
    public void setterInvocationIncrementsCounter() {

        final Knife knife1 = new ButterKnife();
        final Knife knife2 = new ButterKnife();
        final Knife knife3 = new ButterKnife();
        final Knife knife4 = new ButterKnife();

        final HairSplitter hairSplitter = new HairSplitter();

        assertEquals(0, hairSplitter.getNumberOfTimesSetKnifeCalled());

        hairSplitter.setKnife(knife1);

        assertEquals(1, hairSplitter.getNumberOfTimesSetKnifeCalled());

        hairSplitter.setKnife(knife2);
        hairSplitter.setKnife(knife3);

        assertEquals(3, hairSplitter.getNumberOfTimesSetKnifeCalled());

        hairSplitter.setKnife(knife4);
        assertEquals(4, hairSplitter.getNumberOfTimesSetKnifeCalled());
    }
}
