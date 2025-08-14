public class Main {
    public static void main(String... args) {
        Prac p = new Prac();
        p.printMessage("Hello");
        p.printMessage();

    }
}

class Prac {
    String message;

    void printMessage(String s) {
        System.out.println(s);
    }

    void printMessage() {
        System.out.println("こんにちは");
    }
    
}
