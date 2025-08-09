//  X 円の商品を購入しようとしています。あなたは、手持ちの小銭で X 円ちょうどを支払おうとしています。
//  ここで言う小銭とは、500円硬貨、100円硬貨、50円硬貨、10円硬貨、5円硬貨、1円硬貨、のことを指し、すべての硬貨が十分な枚数あります。
//  支払いで使う小銭の合計枚数を最も少なくしたとき、何枚の硬貨が必要になるかを出力してください。
//  入力例 1 の場合、733 円の商品に対して、500円硬貨 1 枚、100円硬貨 2 枚、10円硬貨 3 枚、1 円硬貨 3 枚で支払うことができます。これが最少の小銭の合計枚数なので、9 と出力してください。

import java.util.*;
public class Main {
    public static void main(String[] args) {
        
        int five_hundred_yen = 500;
        int one_hundred_yen = 100;
        int fifty_yen = 50;
        int ten_yen = 10;
        int five_yen = 5;
        int one_yen = 1;

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        // System.out.println(input);
        int lest;
        int minus_yen;
        int count = 0;
        //733
        if(input > 500){
            lest = input / 500;
            minus_yen = 500*lest;
            input = input - minus_yen;
            count = lest;
            // System.out.println(count);
        }
        if(input < 500 && input > 100){
            lest = input / 100;
            minus_yen = 100*lest;
            input = input - minus_yen;
            count = count + lest;
            // System.out.println(count);
        }
        if(input < 100 && input > 50){
            lest = input / 50;
            minus_yen = 50*lest;
            input = input - minus_yen;
            count = count + lest;
            // System.out.println(count);
        }
        if(input < 50 && input > 10){
            lest = input / 10;
            minus_yen = 10*lest;
            input = input - minus_yen;
            count = count + lest;
            // System.out.println(count);
        }
        if(input < 10 && input > 5){
            lest = input / 5;
            minus_yen = 5*lest;
            input = input - minus_yen;
            count = count + lest;
            // System.out.println(count);
        }
        if(input < 5 && input > 1){
            lest = input / 1;
            minus_yen = 1*lest;
            input = input - minus_yen;
            count = count + lest;
            // System.out.println(count);
        }
        System.out.println(count);
    }
}