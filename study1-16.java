class MyApp {
  public static void main(String[] args) {
    int[] scores = {70, 90, 80};
    int[] scoresBackup = scores.clone();
    scores[1] = 100; //再代入している
    System.out.println(scores[1]); // 100が来るはず
    System.out.println(scoresBackup[1]); // 90が来るはず
  }
}