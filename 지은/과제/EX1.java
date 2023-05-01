import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EX1 {
    public static void main(String[] args) {
        List<String> inputList = Arrays.asList(
          "apple", "Banana", "Kiwi", "orange", "melon", "peach", "cherry", "strawberry");
        inputList.stream().
                filter(len->len.length()>5).distinct().sorted(Comparator.comparing(i->i.length())).forEach(System.out::println);
    }
}
