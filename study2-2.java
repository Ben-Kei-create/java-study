import java.util.Scanner;

class MyApp {
  public static void main(String[] args) {
    System.out.print("Score? ");
    int score = new Scanner(System.in).nextInt();
    if (score >= 90) {
      System.out.println("A!");
    } else if (score >= 70) {
            System.out.println("B!");
    } else {
      System.out.println("C");
    }
  }
}