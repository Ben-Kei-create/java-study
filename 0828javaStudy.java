import java.util.*;

public class Main {
    public static void main(String... args) {
        ArrayList<String> users = new ArrayList<>();
        users.add("kirishima");
        users.add("rokumura");
        users.add("midorikawa");

        users.forEach(user -> {
            System.out.println(user);
    });
   }
} 


import java.util.*;

public class Main {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String i = sc.nextLine();
        System.out.println(i);
    }
}
