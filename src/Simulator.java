import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Simulator {

    static int N=0;
    static int M=0;
    static int DB[][] = new int[100][100];
    static ArrayList<ResultSet> resultList= new ArrayList();
    static ArrayList<FeatureSet> featureSetList = new ArrayList();
    static ArrayList<ObjectSet> objectSetList = new ArrayList();

    static class ResultComparator implements Comparator<ResultSet> {
        @Override
        public int compare(ResultSet a, ResultSet b) {

            //sort by score
            //if score is equal then sort by total number of features
            //if still equal then sort by number of objects
            if (a.score > b.score ) {
                return -1;
            } else if (a.score  < b.score ) {
                return 1;
            } else {
                if(a.features.size()>b.features.size()) {
                    return -1;
                }else if(a.features.size()<b.features.size()) {
                    return 1;
                }else {
                    if(a.objects.size()>b.objects.size()) {
                        return -1;
                    }else if(a.objects.size()<b.objects.size()) {
                        return 1;
                    }else {
                        return 0;
                    }
                }
            }
        }
    }
    static void combinationUtil(int arr[], int data[], int start,
                                int end, int index, int r,int choice)
    {
        if (index == r)
        {
            ObjectSet objSet = new ObjectSet();
            FeatureSet feaSet = new FeatureSet();
            for (int j=0; j<r; j++) {
                if(choice==1) {
                    objSet.objects.add(data[j]);
                }
                else{
                    feaSet.features.add(data[j]);
                }
            }
            if(choice==1) {
                objectSetList.add(objSet);
            }
            else if (choice ==2){
                featureSetList.add(feaSet);
            }

            return;
        }

        for (int i=start; i<=end && end-i+1 >= r-index; i++)  {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r,choice);
        }
    }

    static void storeCombination(int arr[], int n, int r,int choice)
    {
        int data[]=new int[r];
        combinationUtil(arr, data, 0, n-1, 0, r,choice);
    }


    public static void parseInput()
    {
        BufferedReader br;

        try{
            br = new BufferedReader(new FileReader("/home/tawfiq/research/kheya/input.txt"));
            String strLine;
            int i=0;
            while ((strLine = br.readLine()) != null)   {
                try{
                    String noInStringArr[] = strLine.split(" ");
                    int j;
                    for(j=0;j<noInStringArr.length ;j++) {
                        DB[i][j] = Integer.parseInt(noInStringArr[j]);
                    }
                    M=j;
                    //then you can parse it to Int as above
                }catch(NumberFormatException npe){
                    //do something
                }
                i++;
            }
            N=i;
            br.close();

        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void generateCombinations()
    {

        int Objects[] = new int[N];
        int Features[] = new int[M];
        for(int i=0;i<N;i++)
            Objects[i]=i+1;

        for(int j=0;j<M;j++)
            Features[j]=j+1;

        for(int i=0;i<N;i++)
            storeCombination(Objects, N, i+1,1);

        for(int j=0;j<M;j++)
            storeCombination(Features, M, j+1,2);

        System.out.println("#Objects="+N+" #Features="+M);
        /*
        for(int i=0;i<objectSetList.size();i++)
            objectSetList.get(i).print();

        for(int i=0;i<featureSetList.size();i++)
            featureSetList.get(i).print();
        */
        for(int i=0;i<objectSetList.size();i++)
        {
            for(int j=0;j<featureSetList.size();j++)
            {
                ResultSet res = new ResultSet();
                res.objects=objectSetList.get(i).objects;
                res.features=featureSetList.get(j).features;
                res.calculateScore();
                resultList.add(res);
            }
        }

        //for(int i=0;i<resultList.size();i++)
           // resultList.get(i).print();
    }
    public static void printResult()
    {
        for(int i=0;i<resultList.size();i++) {
            System.out.println("ID: "+(i+1));
            resultList.get(i).print();
        }
    }
    public static void main(String[] args) {

        parseInput();
        generateCombinations();
        Collections.sort(resultList, new ResultComparator());
        printResult();
    }
}
