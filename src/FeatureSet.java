import java.util.ArrayList;

public class FeatureSet {
    ArrayList<Integer> features =  new ArrayList();

    public void print() {

        for(int i=0;i<features.size();i++)
            System.out.print(features.get(i)+" ");
        System.out.println();
    }
}
