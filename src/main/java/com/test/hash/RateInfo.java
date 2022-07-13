package com.test.hash;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RateInfo {
    private int maxHash;
    private int minHash;
    private int multiplier;
    private int collisionCount;
    private double collisionRate;
}
