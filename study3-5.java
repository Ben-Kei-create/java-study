public class MyApp {
  private static int sum(int a, int b) {
    return a + b;
  }

  private static void showSum(int a, int b) {
    System.out.println(a + b);
  }

  public static void main(String[] args) {
    // System.out.println(sum(3, 7));
    // showSum(3, 7);
    System.out.println(sum(3, 7) * 3);
    showSum(3, 7) * 3;
  }
}