// public class Main {
//     public static void main(String... args) {
//          final double tax = 0.1;
//         System.out.print(tax);
//     }
// }



import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int level = sc.nextInt();
        // System.out.println(count);
        // System.out.println(level);
        
        int[] values = new int[count];
        for(int i = 0; i < count; i ++){
            values[i] = sc.nextInt();
            int enemyLevel = values[i];
            int plus = enemyLevel/2;
                if( level > enemyLevel){
                    // System.out.println("levelが上です。");
                    level = level + plus;
                }
                else if (level < enemyLevel)
                {
                    // System.out.println("levelが下です。");
                    level = level/2;
                }
                else if (level == enemyLevel){
                    // System.out.println("levelが同じです。");
                }
        }
            System.out.println(level);
    }
}