import java.util.ArrayList;

public class Main {
    public static void main(String... args) {
        ArrayList<String> users = new ArrayList<>();

        users.add("kirishima");
        users.add("rokumura");
        users.add("midorikawa");
        
        users.forEach((String user) -> {
            System.out.println(user);
        });

        // users.forEach(user -> System.out.println(user));
     
        // users.forEach(System.out::println);
        users.forEach(x -> System.out.println(x));

        users.forEach(x -> {
            System.out.println(x.toUpperCase());
        });

        for(String user: users){
            System.out.println(user);
        }

    }
}
