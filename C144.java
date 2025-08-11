/*
 * アリスさんはじゃんけんが大好きで、過去に何度も友達のボブさんとじゃんけんを行いました。手元には、アリスさんとボブさんとの間で、過去に行われたじゃんけんの結果を全て記録したノートがあります。
ノートの記録を元に、過去にアリスさんはボブさんに何回勝ったかを出力するプログラムを作成してください。例えば入力例 1 の場合、過去にじゃんけんは 6 回行われ、そのうちアリスさんは 3 回勝ち、 1 回あいこ、 2 回負けているので、プログラムでは 3 と一行に出力します。
 */

 import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int winCount = 0;
        // System.out.println(N);
        for(int i = 0; i < N; i++){
           String alis = sc.next();
           String enemy = sc.next();
        //   System.out.println(alis);
        //   System.out.println(enemy);
           
           if(alis.equals("G") && enemy.equals("C")){
               winCount++;
           }else if(alis.equals("C") && enemy.equals("P")){
               winCount++;
           }else if(alis.equals("P") && enemy.equals("G")){
               winCount++;
           }
           
        //   System.out.println(winCount);
        }
          System.out.println(winCount);
        
    }
}