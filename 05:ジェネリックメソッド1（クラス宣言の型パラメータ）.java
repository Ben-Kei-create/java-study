// public class Main {
//     public static void main(String... args) {
//         A<Boolean> a = new A<>();
//         a.set(true);
//         System.out.println(a.get());
//     }
// }

// class A<T> {
//     T t;

//     // 型引数で指定された型を引数の型とするメソッドを定義
//     public void set(T t)
//     {
//         this.t = t;
//     }
    
//     public T get(){
//         return t;
//     }
    
// }

public class Main {
    public static void main(String... args) {
        A<Integer> a = new A<>();
        // メソッド独自の型パラメータを利用するジェネリックメソッドを呼び出し（引数には true または false を使用）
        Object o = a.judge(true);
        // System.out.println(o);
        
    }
}

class A<T> {
    T t;

    // メソッド独自の型パラメータを利用するジェネリックメソッドを定義
    void judge() {
        // System.out.println(t.getClass());
        
    }
    
    <U> U judge(U s){
        System.out.println(s.getClass());
        return s;
    }
}
