package edu.princeton.cs._14array;

import edu.princeton.cs.stdlib.StdOut;

import java.util.Random;

/*
 *  Compilation:  javac CouponCollector.java
 *  Execution:    java CouponCollector n

 *
 *
 *  % java CouponCollector 1000
 *  6583
 *
 *  % java CouponCollector 1000
 *  6477
 *
 *  % java CouponCollector 1000000
 *  12782673
 */

/**
 * Wie viele zufällige Karten müssen Sie bei n verschiedenen Kartentypen
 * sammeln, bis Sie (mindestens) eine von jedem Typ haben? Dieses Programm
 * simuliert diesen Zufallsprozess.
 */
public class CouponCollector {

    private static final Random random = new Random();

    public static void main(String[] args) {

        int n = (args.length < 1) ? 1_000 : Integer.parseInt(args[0]); // number of card types

        boolean[] isCollected = new boolean[n];  // isCollected[i] = true if card i has been collected
        int count = 0;                           // total number of cards collected
        int distinct = 0;                        // number of distinct cards

        // repeatedly choose a random card and check whether it's a new one
        while (distinct < n) {
            int value = random.nextInt(n);   // random card between 0 and n-1
            count++;                               // we collected one more card
            if (!isCollected[value]) {
                distinct++;
                isCollected[value] = true;
            }
        }

        // print the total number of cards collected
        StdOut.println(count);
    }
}
