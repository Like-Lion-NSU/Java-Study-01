import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class List1 {
    public static void main(String[] args){
        List<String> list = new ArrayList();
        Scanner sc = new Scanner(System.in);

        System.out.println("부원 5명의 이름을 입력해주세요.>>");
        String a = sc.next();
        String b = sc.next();
        String c = sc.next();
        String d = sc.next();
        String e = sc.next();

       // if(name->name.startsWith("김"))
        System.out.println(name->name.startsWith("김"));
        System.out.print("나와 성이 같은 사람은 총 " + "2" + "명입니다.");


       /*names.stream()
        {
            .filter(n->n.startsWith("김"))
                .forEach(System.out::println);
        }

        System.out.print(member );
        System.out.print("나와 성이 같은 사람은 총 " + number + "명입니다.");*/
    }
}