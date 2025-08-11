import java.util.ArrayList;

public class listSample {
    public static void study(String[] args) {
    ArrayList<String> members = new ArrayList<>();
    System.out.println(members);
    members.add("kirishima");
    members.add("Awashima");
    members.set(2, "nara");

    members.add(0, "rokumura");
    String member = members.get(1);
    System.out.println(member);
    System.out.println(members.size());
    // 要素のインデックスを取得
    System.out.println(members.indexOf("rokumura"));
    //要素の有無を確認（contains）
    System.out.println(members.contains("rokumura"));
    // 指定した要素を削除
    members.remove("kirishima");
    // 全要素削除
    members.clear();

        
    }
}

