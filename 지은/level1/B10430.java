import java.util.Scanner;
public class B10430 {
    public static void main(String[] args) {
        Scanner w = new Scanner(System.in);
        int A = w.nextInt();
        int B = w.nextInt();
        int C = w.nextInt();
        System.out.println((A+B)%C);
        System.out.println(((A%C) + (B%C))%C);
        System.out.println((A*B)%C);
        System.out.println(((A%C)*(B%C))%C);
    }
}
