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

// import java.util.Scanner;

// class MyApp {
//   public static void main(String[] args) {
//     Integer answer = 6;
  
//     System.out.print("Your guess? ");
//     Integer guess = new Scanner(System.in).nextInt();

//     if (answer == guess){
//       System.out.println("BINGO!");
//     } else {
//       System.out.println("BOO");
//     }
//   }
// }

// import java.util.Scanner;

// class MyApp {
//   public static void main(String[] args) {
//     Integer answer = 6;
    
//     System.out.print("Your guess? ");
//     Integer guess = new Scanner(System.in).nextInt();
    
//     if (answer == guess) {
//       System.out.println("Bingo!");
//     } else if (answer > guess){
//       System.out.println("the answer is higher!");
//     } else {
//       System.out.println("the answer is lower!");
//     }
//   }
// }

// import java.util.Scanner;
// import java.util.Random;

// class MyApp {
//   public static void main(String[] args) {
//     Integer answer = new Random().nextInt(10) + 1;
    
//     System.out.print("Your guess? ");
//     Integer guess = new Scanner(System.in).nextInt();
    
//     if (answer == guess) {
//       System.out.println("Bingo!");
//     } else if (answer > guess) {
//       System.out.println("The answer is higher!");
//     } else {
//       System.out.println("The answer is lower!");
//     }
    
//     System.out.print("The answer was " + answer);
//   }
// }

// import java.util.Scanner;
// import java.util.Random;

// class MyApp {
//   public static void main(String[] args) {
//     Integer answer = new Random().nextInt(10) + 1;
//     while (true) {
//       System.out.print("Your guess? ");
//       Integer guess = new Scanner(System.in).nextInt();
//       if (answer == guess) {
//         System.out.println("Bingo!");
//         break;
//       } else if (answer > guess ) {
//         System.out.println("The answer is higher!");
//         System.out.println("answer is " + answer);
//       } else {
//         System.out.println("The answer is lower!");
//         // System.out.println("answer is " + answer);
//       }
//     }
//   }
// }


import java.util.Scanner;
import java.util.Random;

class MyApp {
  public static void main(String[] args) {
    Integer answer = new Random().nextInt(10) + 1;
    Integer count = 0;
    while (true) {
      System.out.print("Your guess? ");
      Integer guess = new Scanner(System.in).nextInt();
      // count += 1;
      count ++;
      
      if (answer == guess) {
        System.out.println("Bingo! It took " + count + "guess");
        break;
      } else if (answer > guess ) {
        System.out.println("The answer is higher!");
      } else {
        System.out.println("The answer is lower!");
      }
    }
  }
}