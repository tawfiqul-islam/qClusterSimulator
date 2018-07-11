import java.util.ArrayList;

public class ObjectSet {
    ArrayList<Integer> objects = new ArrayList();

    public void print() {

        for(int i=0;i<objects.size();i++)
            System.out.print(objects.get(i)+" ");

        System.out.println();
    }
}
