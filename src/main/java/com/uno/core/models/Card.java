package com.uno.core.models;

import java.util.Random;

public class Card {
    private static final Colour[] COLOURS = {Colour.RED, Colour.GREEN, Colour.BLUE, Colour.YELLOW};
    private char[][] grid;
    private Random random;
    public int number;
    public Colour colour;

    // Default constructor for random cards
    public Card() {
        random = new Random();
        this.number = random.nextInt(9) + 1;
        this.colour = COLOURS[random.nextInt(COLOURS.length)];
        createGrid();
    }

    // Constructor for specific numbers and colours
    public Card(int number, Colour colour) {
        boolean validColour = false;
        for (Colour c : COLOURS) {
            if (c == colour) {
                validColour = true;
                break;
            }
        }
        if (!validColour) {
            throw new IllegalArgumentException("Invalid colour specified");
        }

        if (number < 0 || number > 9) {
            throw new IllegalArgumentException("Number must be between 0 and 9");
        }

        this.colour = colour;
        this.number = number;
        createGrid();
    }

    // Constructor for special cards with no number
    public Card(Colour colour){
        this.colour = colour;
    }

    // method for the function of each special card
    public void play(Game game){}

    public void createGrid() {
        this.grid = new char[3][3];

        char charNum = (char) ('0' + this.number);
        char charCol = this.colour.toString().charAt(0);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = charCol;
            }
        }
        grid[1][1] = charNum;
    }

    // adapted grid creation for special cards
    public void createGridSpecialCards(char symbol) {
        this.grid = new char[3][3];

        char charCol = this.colour.toString().charAt(0);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = charCol;
            }
        }
        grid[1][1] = symbol;
    }


    public char[][] getGrid() {
        return this.grid;
    }

    public void showCard() {
        String reset = "\u001B[0m";

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(colour.getAnsiCode() + grid[i][j] + " " + reset);
            }
            System.out.println();
        }
    }

    public boolean canBePlayedOn(Card topCard) {
        return this.colour.equals(topCard.colour) || this.number == topCard.number;
    }
}