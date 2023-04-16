import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Stream2 {
    private String Name;
    private int Level;
    private int Power;

    Stream2(String Name, int Level, int Power){
        this.Name = Name;
        this.Level = Level;
        this.Power = Power;
    }

    public String getName() {return this.Name;}
    public int getPower() {return this.Power;}
    public boolean getLevel() {return this.Level >= 15;}
    //public String toUpperCase() {return this.Name;}
}

public class Stream1 {
    public static void main(String[] args) {
        List<Stream2> stream = new ArrayList<>(Arrays.asList(
                new Stream2("james", 10, 3000),
                new Stream2("alice", 20, 4000),
                new Stream2("thomas", 15, 3500)
        ));

        List<String> name =
                stream.stream()
                    .map(Stream2::getName)
                        .collect(Collectors.toList());

        List<Integer> power =
                stream.stream()
                        .map(Stream2::getPower)
                        .collect(Collectors.toList());

        List<String> level =
                stream.stream()
                        .filter(Stream2::getLevel)
                        .map(Stream2::getName)
                        .sorted()
                        .collect(Collectors.toList());

        System.out.println("[user name]\n" + name + "\n");
        System.out.println("[user power 합계]\n" + power + "\n");
        System.out.println("[user level 15 이상]\n" + level + "\n");

    }
}