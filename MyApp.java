// class MyApp {
//   public static void main(String[] args) {
//     System.out.println("hello taguchi");
//     System.out.println("hello taguchi again!");
//   }
// }

import java.util.Scanner;
class MyApp {
  public static void main(String[] args) {
    System.out.print("Your name?");
    String name = new Scanner(System.in).next();
    
    System.out.println("hello " + name);
    System.out.println("hello " + name +  " again!");
  }
}