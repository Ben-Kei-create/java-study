import java.util.Scanner;
class MyApp {
  public static void main(String[] args) {
    System.out.print("Signal? ");
    String signal = new Scanner(System.in).next();
    switch (signal){
      case "red":
        System.out.println("Stop");
        break;
      case "blue":
        System.out.println("Go");
        break;    
      case "yellow":
        System.out.println("SlowDown");
        break;
    }
  }
}