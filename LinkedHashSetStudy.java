import java.util.LinkedHashSet;

public class Main {
    public static void main(String... args) {
        LinkedHashSet<String> animals = new LinkedHashSet<>();
        System.out.println(animals);
        animals.add("cat");
        animals.remove("lion");
        animals.clear();



        System.out.println(animals);

        System.out.println(animals.isEmpty());
        animals.forEach(x -> System.out.println(x));
 
        for(String animal: animals){
            System.out.println(animal);
        }

    }
}
