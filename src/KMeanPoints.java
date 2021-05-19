import java.util.ArrayList;
import java.util.List;

public class KMeanPoints {
    List<Point<Double>> means = new ArrayList<>();

    KMeanPoints(){

    }

    public List<Point<Double>> getMeans(){
        return means;
    }

    public void addPoint(Point<Double> point){
        means.add(point);
    }

    public Point<Double> getPoint(int position){
        return means.get(position);
    }

    public int size(){
        return means.size();
    }
}
