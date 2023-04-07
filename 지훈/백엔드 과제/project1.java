package Techit;

import java.util.Scanner;

public class project1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("금액을 입력하시오>> ");
        int money = sc.nextInt();
        for (int a = 50000; (money/a)>= 0;) {
            System.out.println("오만원권" + (money / a) + "장");
            break;
        }
        for (int b = 10000; ((money%50000) / b) >= 0; ) {
            System.out.println("만원권" + ((money%50000) / b) + "장");
            break;
        }
        for (int c = 1000; ((money%10000) / c) >= 0; ) {
            System.out.println("천원권" + ((money%10000) / c) + "장");
            break;
        }
        for (int d = 500; ((money%1000) / d) >= 0; ) {
            System.out.println("오백원" + ((money%1000) / d) + "개");
            break;
        }
        for (int e = 100; ((money%500) / e) >= 0; ) {
            System.out.println("백원" + ((money%500) / e) + "개");
            break;
        }
        for (int f = 50; ((money%100) / f) >= 0; ) {
            System.out.println("오십원" + ((money%100) / f) + "개");
            break;
        }
        for (int g = 10; ((money%50) / g) >= 0; ) {
            System.out.println("십원" + ((money%50) / g) + "개");
            break;
        }
        for (int h = 1; ((money%10) / h) >= 0; ) {
            System.out.println("일원" + ((money%10) / h) + "개");
            break;
        }


    }
}
