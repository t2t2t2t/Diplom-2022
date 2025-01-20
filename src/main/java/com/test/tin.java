package com.test;

import java.util.Scanner;

public class tin {
    public static void main(String[] args) {
        int A=0,B=0,C=0,D=0;
Scanner stringScanner = new Scanner(System.in);

A=Integer.parseInt(stringScanner.next());
B=Integer.parseInt(stringScanner.next());
C=Integer.parseInt(stringScanner.next());
D=Integer.parseInt(stringScanner.next());
if(D>B){
    System.out.println(A+(D-B)*C);
} else {
    System.out.println(A);
}
    }
}
