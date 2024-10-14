class MyApp {
  private static void showAd() {
    System.out.println("---------");
    System.out.println("SALE! 50% OFF!");
    System.out.println("---------");
  }

  private static void showContent() {
    System.out.println("BREAKING NEWS!");
    System.out.println("Two baby pandas born at our Zoo!");
  }
  
  public static void main(String[] args) {
    showAd();
    showContent();
    showAd();
  }
}