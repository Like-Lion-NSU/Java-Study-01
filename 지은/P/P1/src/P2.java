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
                new User("Thomas", 15, 3500)));
        List<String>names =
                Users.stream().
                        filter(User::getName).collect(Collectors.toList());
        List<int>powerSum=
                Users.stream().
                        filter(User::getSumPower).collect(Collectors.toList());
        List<int>level15 =
                Users.stream().sorted(Comparator.comparing(User::getLevel).reversed()).
                        filter(User::getLevel>=15).map(User::up).collect(Collectors.toList());

        System.out.println("[User name]");
        System.out.println(names);
        System.out.println("\n[User power 합계]");
        System.out.println(powerSum);
        System.out.println("\n[User level 15 이상]");
        System.out.println(level15);
    }
}
