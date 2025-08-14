import java.util.ArrayList;

public class Main {
    public static void main(String... args) {
        MyFirstClass o1 = new MyFirstClass();
        o1.name = "kyoko";
    }
}

class MyFirstClass {
    String name;

    String getName() {
        return "私の名前は" + name;
    }

    void printName() {
        System.out.println(name);
    }

    void printIntValue(int i) {
        System.out.println(i);
    }

    int multiply(int i1, int i2) {
        return i1 * i2;
    }

    void addString(ArrayList<String> l) {
        l.add("add");
    }

    void addInt(int i) {
        i++;
    }
}
