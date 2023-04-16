package 과제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class project3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("부원 5명의 이름을 입력해주세요. >>");
        String name1 = sc.nextLine();
        String name2 = sc.nextLine();
        String name3 = sc.nextLine();
        String name4 = sc.nextLine();
        String name5 = sc.nextLine();
        List<String> members = Arrays.asList(name1, name2, name3, name4, name5);

        members.stream()
                .filter(k->k.startsWith("김"))
                .forEach(System.out::println);

    }
}
