class User {
    String name;
    int score;
    
    User(String name, int score){
      this.name = name;
      this.score = score;
    }
  }
  
  class MyApp {
    public static void main(String[] args) {
      User user1 = new User("Taro", 70);
      User user2 = new User("Jiro", 80);
    }
  }