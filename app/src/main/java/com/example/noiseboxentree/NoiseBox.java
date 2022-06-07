package com.example.noiseboxentree;

public class NoiseBox {

    private int id;
    private String name;
    private int length; // full length
    private int width; // full width
    private int height; // fill height
    private int innerLength; // device length
    private int innerWidth; // device width
    private int innerHeight; // device height
    private boolean havingSpAdvanced; // advanced soundproof
    private boolean havingFan; // presence of a fan

    // Constructor
    // Constructor doesn't receives full length, width, height
    // Though, they will be calculated in it using inner data
    public NoiseBox(int id,
                    String name,
                    int innerLength,
                    int innerWidth,
                    int innerHeight,
                    boolean isSpAdvanced,
                    boolean doesHaveFan) {
        this.id = id;
        this.name = name;
        this.innerLength = innerLength;
        this.innerWidth = innerWidth;
        this.innerHeight = innerHeight;
        this.havingSpAdvanced = isSpAdvanced;
        this.havingFan = doesHaveFan;

        // computing full length, width, height
        if(isSpAdvanced){ // when a customer wants Advanced Soundproof to be installed
            this.length = innerLength + 2 * (50 + 130 + 50 + 16);
            this.width = innerWidth + 2 * (30 + 50 + 16);
            this.height = innerLength + 2 * (30 + 50 + 16 + 130 + 50 + 16);
        }else { // when a customer is a miser
            this.length = innerLength + 2 * (50 + 130 + 50 + 16);
            this.width = innerWidth + 2 * (30 + 50 + 16);
            this.height = innerLength + 2 * (30 + 50 + 16 + 130 + 16);
        }
    }

    // Setters

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getInnerLength() {
        return innerLength;
    }

    public int getInnerWidth() {
        return innerWidth;
    }

    public int getInnerHeight() {
        return innerHeight;
    }

    public boolean isHavingSpAdvanced() {
        return havingSpAdvanced;
    }

    public boolean isHavingFan() {
        return havingFan;
    }
}
