import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 日数
        int days = sc.nextInt();
        // 週の天気
        int[] week = new int[days];
        // 天候
        int[] whether = new int[days];
        
        for(int i = 0; i < days; i++){
            whether[i] = sc.nextInt();
            int whether_1day = whether[i];
            week.add(whether_1day);
        }
        
        for(int i = 0; i < days; i++){
            if(week[i] == 0 &&  week[i+1] == 2){
                System.out.println(i);
            }else if(week[i] != 0 || week[i] != 2){
                System.out.println("-1");
            }
        }
    }
}


// String line = sc.nextLine();
// 虹が出る可能性のある日・・前日が雨の日である晴れ
// 2→0
// 0-晴れ、 1-くもり、 2-雨
// 今日を含めた D 日間の天気予報が与えられます。
// 虹が見られる可能性がある日を 1 日目から順にすべて出力してください。



public class Main {
    public static void main(String... args) {
        ArrayList<String> members = new ArrayList<>();
        
        members.add("kirishima");
        members.add("rokumura");
        

        members.forEach(member -> {
            
            if(members.contains("kirishima")){
                System.out.println("membersにkirishimaは含まれている");
            }else{
                System.out.println("membersにkirishimaは含まれていない");
            }
        });
    }
}
public class Main {
    public static void main(String... args) {

        Integer x = 37;
        Integer y = 16;
        
        // 以下のコードを修正
        if () {
            System.out.println("xとyのいずれか、もしくは両方とも偶数です");
        } else {
            System.out.println("xとyは奇数です");
        }
    }
}
