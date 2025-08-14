// E : 要素
// K : キー
// N : 番号
// T : タイプ
// V : 値
// S, U, V, ... : ふたつ目以降のタイプ

import java.util.*;
public class Main {
    public static void main(String... args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        // numbers.add(false);
        numbers.add(100);
        // numbers.add("勇者");
        numbers.add(50);
        // numbers.add("戦士");

        // int num1 = numbers.get(0);
        int num2 = numbers.get(0);
        // int num3 = numbers.get(2);
        int num4 = numbers.get(1);
        // int num5 = numbers.get(4);

        // ダイヤモンド表記と対応する変数を定義し、出力するコードを記述

        System.out.println(num2);
        System.out.println(num4);

    }
}


//右側のコードではエラーが発生してしまいます。
// ジェネリッククラスのフィールドにパラメーターを宣言し、変数bの型を出力するようコードを修正してください。
// import java.util.*;
public class Sample {
    public static void main(String... args) {
        A<Boolean> a = new A<>();
        a.b = true;
        // 変数b の型を出力
        System.out.println(a.b.getClass().getName());
    }
}
class A<T> {
    // フィールドにパラメーターを宣言
    T b;
}
