/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitmaker.fontcreator;

/**
 *
 * @author Fran Kitmaker
 */
public class CodePoint {
    
    public static String toString(int icodePoint) {
        StringBuilder sb = new StringBuilder();
        sb.appendCodePoint(icodePoint);
        return sb.toString();
    }
    
    public static String toString(int[] icodePoint) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<icodePoint.length; i++) {
            sb.appendCodePoint(icodePoint[i]);
        }
        return sb.toString();
    }
    
    public static int[] toCodePointArray(String str) { // Example 1-6
        char[] ach = str.toCharArray();  // a char array copied from str
        int len = ach.length;            // the length of ach
        int[] acp = new int[Character.codePointCount(ach, 0, len)];
        int j = 0;                       // an index for acp

        for (int i = 0, cp; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(ach, i);
            acp[j++] = cp;
        }
        return acp;
    }
}
