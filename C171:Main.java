// N-しりとりとは通常のしりとりを拡張した新しいしりとりです。

// 通常のしりとりでは最後の一文字を先頭として始まる単語を言うルールですが、N-しりとりでは最後の N 文字を先頭として始まる単語を言わなければなりません。

// たとえば、N=2 で「しりとり」を言った場合、次は「とり」から始まる単語を言わなければならないということです。

// N の値と M 個の単語のリストが与えられます。
// リストの順に単語を言ったときにN-しりとりになっている場合は YES をそうでない場合は NO を出力してください。



import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer N = sc.nextInt();
        Integer M = sc.nextInt();
        String firstSub = null;
        String endSub = null;
        int count = 0;
        
        ArrayList<String> phrases = new ArrayList<>();
        ArrayList<String> checkMoji = new ArrayList<>();
        
        for(int i = 0; i < M; i++){
            String moji = sc.next();
            phrases.add(moji);
            // System.out.println(phrases.get(i));
            String phrase = phrases.get(i);
            
            
            // 0,2,4
            if(i % 2 == 0){
                firstSub = phrase.substring(0, N);
                endSub = phrase.substring(phrase.length() - N);
                // endSub = nextPhrase.substring(0, N);
                // System.out.println(firstSub);
                // System.out.println(endSub);
                // if(firstSub.equals(nextPhrase)){
                //     count ++;
                //     System.out.println(count);
                // }
                checkMoji.add(firstSub);
                checkMoji.add(endSub);
                // System.out.println(checkMoji);
            }else{
                // firstSub = nextPhrase.substring(0, N);
                firstSub = phrase.substring(0, N);
                endSub = phrase.substring(phrase.length() - N);
                // System.out.println(firstSub);
                // System.out.println(endSub);
                
                checkMoji.add(firstSub);
                checkMoji.add(endSub);
                // System.out.println(checkMoji);

            }
        }
            // System.out.println(checkMoji);
        
        for(int i = 1; i <= M*2-3; i ++){
            // System.out.println(checkMoji.get(i));
            // System.out.println(checkMoji.get(i+1));
            if(checkMoji.get(i).equals(checkMoji.get(i+1))){
                count ++;
                // System.out.println(count);
                
            }
            // System.out.println(count);
        }
           
            if(count == M-1){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
        
        // System.out.println(checkMoji);

    }
