import java.util.ArrayList;

public class Result {

    ArrayList<Integer> objects;
    ArrayList<Integer> features;
    double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void calculateScore()
    {
        score=0;
        for(int i=0;i<objects.size();i++)
        {
            int id=objects.get(i)-1;
            double count=0;
            for(int j=0;j<features.size();j++)
            {
                if(Main.DB[id][features.get(j)-1]==1)
                {
                    count++;
                }
            }
            double tempScore=count/features.size();
            score+=tempScore;
        }
        score/=objects.size();
    }

    public void print() {

        System.out.print("Features: ");
        for(int i=0;i<features.size();i++)
            System.out.print(features.get(i)+" ");
        System.out.println();

        System.out.print("Objects: ");
        for(int i=0;i<objects.size();i++)
            System.out.print(objects.get(i)+" ");

        System.out.println();
        System.out.println("score= "+score+"\n");
    }
}
