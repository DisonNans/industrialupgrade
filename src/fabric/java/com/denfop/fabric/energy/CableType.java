package com.denfop.fabric.energy;

public enum CableType {
    GLASS(32768D), GLASS1(131072D), GLASS2(524288D), GLASS3(2097152D), GLASS4(8388608D),
    GLASS5(33554432D), GLASS6(134217728D), GLASS7(536870912D), GLASS8(8589934590D),
    GLASS9(439804653000D), GLASS10(1759218610000D);

    public static final double LOSS = 0.2D;
    private final double capacity;
    CableType(double capacity) { this.capacity = capacity; }
    public double capacity() { return capacity; }
}
