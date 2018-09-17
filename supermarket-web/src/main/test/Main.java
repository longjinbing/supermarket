import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        position s=new position(in.nextInt(), in.nextInt());
        position e=new position(in.nextInt(), in.nextInt());
        List<position> temp=new ArrayList<>();
        if(s.x+1<9&&s.y+2<9) {
            temp.add(new position(s.x + 1, s.y + 2));
        }
        if(s.x+1<9&&s.y-2<9) {
            temp.add(new position(s.x + 1, s.y + 2));
        }
        if(s.x+2<9&&s.y+1<9) {
            temp.add(new position(s.x + 2, s.y + 1));
        }
        if(s.x+2<9&&s.y+1<9) {
            temp.add(new position(s.x + 2, s.y + 1));
        }
        int res=0;
        System.out.println(String.valueOf(res));
    }

    public static class position{

        public position(int x,int y){
            x=x;
            y=y;
        }
        public int x;
        public int y;
    }
}
