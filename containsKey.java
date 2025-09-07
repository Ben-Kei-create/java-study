import java.util.LinkedHashMap;

public class Main {
    public static void main(String... args) {
        LinkedHashMap<Integer, String> users = new LinkedHashMap<>();
        users.put(1, "kirishima");
        users.put(2, "rokumura");
        users.put(3, "midorikawa");
        users.remove(1);
        System.out.println(users);
    }
}