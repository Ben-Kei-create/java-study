class MyApp {
  private static void showThreeTimes(int n) {
    System.out.println(n);  
    System.out.println(n);  
    System.out.println(n);  
  }

    private static void showThreeTimes(double n) {
    System.out.println(n);  
    System.out.println(n);  
    System.out.println(n);  
  }

  public static void main(String[] args) {
    showThreeTimes(3);
    showThreeTimes(5.2);
  }
}