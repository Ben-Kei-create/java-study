import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        Integer i = sc.nextInt();
        
        String judge = null;
        // 以下にコードを追加
        switch(i){
           case 1, 3 -> judge = "ODD";
           case 2, 4 -> judge = "EVEN";
           default -> judge = "OVER";
        }
        System.out.println(judge);
    }
}


import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();  // Integer でもよいですが、基本は int でOK
        
        switch (i) {
            case 1:
                System.out.println("ONE");
                break;  // break を忘れない
            case 2:
                System.out.println("TWO");
                break;
            default:  // それ以外
                System.out.println("OVER");
                break;
        }
    }
}


/*
複数ラベルのまとめ書き

case 1, 3 -> System.out.println("ODD");
→ 1 または 3 のときに "ODD" を出力

default の使用
default -> System.out.println("OVER");


→ どのケースにも当てはまらないときに "OVER" を出力
break が不要
→ -> を使った場合は自動的にそのケースで終了するので break は不要
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // ----------------------------------------
        // [乱数 - 1以上の乱数1]
        // ----------------------------------------
        // 1から6のランダムな整数を出力してください

        int a = (int) (Math.random() * 6) + 1;
        System.out.println(a);
    }
}

// import java.util.*;

public class Main {
    public static void main(String[] args) {
        // ----------------------------------------
        // [乱数 - 範囲を指定した乱数1]
        // ----------------------------------------
        // 3から8のランダムな整数を出力してください

        System.out.println((int)(Math.random()* 8 - 3 + 1) + 3);
    }
}

