package 과제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

class User {
    private String name;
    private int level;
    private int power;
    User(String name, int level, int power) {
        this.name = name;
        this.level = level;
        this.power = power;
    }
    public String getName(){return this.name;}
    public int getLevel(){return this.level;}
    public int getPower(){return this.power;}


}
public class project4 {
    List<User> users = new ArrayList<>(Arrays.asList(
            new User("james",10,3000),
            new User("Alice",20,4000),
            new User("Thomas",15,3500)
    ));
    List<String > username =
            users.stream()
                    .map(User::getName)
                    .collect(Collectors.toList());
    List<Integer > userpower =
            users.stream()
                    .reduce(User)
                    .map(User::getPower)
                    .collect(Collectors.toList());
    List<Integer > userlevel=
            users.stream()
                    .filter()
                    .sorted(Comparator.comparing(User::getName).)







}
