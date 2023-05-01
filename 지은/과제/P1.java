import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner w = new Scanner(System.in);
        int a = w.nextInt();
        int OM,M,C,B,OS,S;
        OM=a/50000;
        a=a-50000*OM;
        M=a/10000;
        a=a-10000*M;
        C=a/1000;
        a=a-1000*C;
        B=a/100;
        a=a-100*B;
        OS=a/50;
        a=a-50*OS;
        S=a/10;
        a=a-10*S;
        System.out.printf("50000원 %d개\n", OM);
        System.out.printf("10000원 %d개\n", M);
        System.out.printf("1000원 %d개\n", C);
        System.out.printf("100원 %d개\n", B);
        System.out.printf("50원 %d개\n", OS);
        System.out.printf("10원 %d개\n", S);
        System.out.printf("1원 %d개", a);
    }

}