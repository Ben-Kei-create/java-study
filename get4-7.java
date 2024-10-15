class User {
  String name;
  private int score;

  User(String name, int score) {
    this.name = name;
    this.score = score;
  }

  String getUserString() {
    return this.name + ", " + this.score;
  }

  // setter
  void setScore(int score) {
    if (score < 0 || score > 100) {
      System.out.println("Invalid score!");
      return;
    }
    this.score = score;
  }
  
  //getter
  int getScore() {
    return this.score;
  }
}

class MyApp {
  public static void main(String[] args) {
    User user1 = new User("Taro", 70);
    User user2 = new User("Jiro", 80);

    user1.score = 900;
    // user1.setScore(900);
    System.out.println(user1.getScore());

    System.out.println(user1.getUserString());
    System.out.println(user2.getUserString());
  }
}