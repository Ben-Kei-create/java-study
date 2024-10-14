// class MyApp {
//   public static void main(String[] args) {
//     System.out.println("hello taguchi");
//     System.out.println("hello taguchi again!");
//   }
// }

// import java.util.Scanner;
// class MyApp {
//   public static void main(String[] args) {
//     System.out.print("Your name?");
//     String name = new Scanner(System.in).next();
    
//     System.out.println("hello " + name);
//     System.out.println("hello " + name +  " again!");
//   }
// }

// import java.util.Scanner;

// class MyApp {
//   public static void main(String[] args) {
//     System.out.print("Your guess? ");
//     Integer guess = new Scanner(System.in).nextInt();
    
//     System.out.println("Your guess: " + guess);
//   }
// }

import java.util.Scanner;

class MyApp {
  public static void main(String[] args) {
    Integer answer = 6;
  
    System.out.print("Your guess? ");
    Integer guess = new Scanner(System.in).nextInt();

    if (answer == guess){
      System.out.println("BINGO!");
    } else {
      System.out.println("BOO");
    }
  }
}