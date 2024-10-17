class User {
  String name;
  int score;
  static int count = 0;

  User(String name, int score) {
    this.name = name;
    this.score = score;
    User.count++;
  }
}

class MyApp {
  public static void main(String[] args) {
    // int count = 0;
    User user1 = new User("Taro", 70);
    // count ++;
    User user2 = new User("Jiro", 80);
    // count ++;
    System.out.println(User.count);
  }
}

