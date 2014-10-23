package io.ghost.apetro.example;


import org.springframework.beans.factory.annotation.Autowired;

public class HairSplitter {

    private int numberOfTimesSetKnifeCalled = 0;

    private Knife knife;

    @Autowired
    public void setKnife( Knife aKnife ) {
        System.out.println("setKnife(" + aKnife + ")");
        this.numberOfTimesSetKnifeCalled++;
        this.knife = aKnife;
    }

    /**
     * Instrumentation to enable demonstrating how many times setKnife() has been called on a given HairSplitter
     * instance.
     * @return zero or more representing number of times setKnife() has been called.
     */
    public int getNumberOfTimesSetKnifeCalled() {
        return this.numberOfTimesSetKnifeCalled;
    }

}
