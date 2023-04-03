import java.util.Scanner;
public class B2588 {
    public static void main(String[] args) {
        Scanner w = new Scanner(System.in);
        int A = w.nextInt();
        String B = w.next();
        for(int i=2;i>=0;i--){
            int b = Integer.parseInt(B.substring(i,i+1));
            System.out.println(A*b);
        }
        int b = Integer.parseInt(B);
        System.out.println(A*b);
    }
}
