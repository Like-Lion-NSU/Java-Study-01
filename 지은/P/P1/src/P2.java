import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class User{
    private String name;
    private int level;
    private int power;
    private int sumPower;

    User(){}
    User(String name, int level, int power){
        this.name=name;
        this.level=level;
        this.power=power;
    }
    public String getName(){return this.name;}
    public int getLevel(){return this.level;}
    public int getPower(){return this.power;}
    public int getSumPower(){
        sumPower += this.power;
        return sumPower;
    }
    public String up(){
        return this.name.toUpperCase();
    }
}
public class P2 {
    public static void main(String[] args) {
        List<User> Users = new ArrayList<>(Arrays.asList(
                new User("Jamse", 10, 3000),
                new User("Alice", 20, 4000),
                new User("Thomas", 15, 3500))); //구글링해도 왜 다 배열이 아니라 하나인거냐~~
        List<String>names =
                Users.stream().
                        //filter(names.get().getname).collect(Collectors.toList()); 이것도 아니잖
                        filter(User::getName).collect(Collectors.toList());
        List<int>powerSum=
                Users.stream().//예시 찾아봐도 읍다
                        filter(User::getSumPower).collect(Collectors.toList()); //검색해도 안나옴... 오류 퉤퉤
        List<int>level15 =
                Users.stream().sorted(Comparator.comparing(User::getLevel).reversed()).
                        filter(User::getLevel>=15).map(User::up).collect(Collectors.toList()); // 약간 IF문처럼 T/F반환해서 하는 것 같은데 근데 왜 이건 우우우

        System.out.println("[User name]");
        System.out.println(names);
        System.out.println("\n[User power 합계]");
        System.out.println(powerSum);
        System.out.println("\n[User level 15 이상]");
        System.out.println(level15);
    }
}
