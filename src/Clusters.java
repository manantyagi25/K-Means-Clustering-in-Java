import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Clusters {
    HashSet<Point<Double>> cluster = new HashSet<>();

    Clusters(){
    }

    public HashSet<Point<Double>> getCluster(){
        return cluster;
    }

    public void addPointToSet(Point<Double> point){
        cluster.add(point);
    }

}
