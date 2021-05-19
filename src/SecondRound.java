import java.net.Inet4Address;
import java.util.*;

public class SecondRound {
    static int k = 3;
    static int convergenceCount = 10;

    public static void main(String[] args) {
        List<Point<Double>> pointsList = new ArrayList<>();

        //Adding 100 random points to the graph
        for(int i = 0 ; i<100 ; ++i){
            Random random = new Random();
            double x = random.nextDouble() * 50;
            double y = random.nextDouble() * 50;

            Point<Double> point = new Point<>(x, y);
            pointsList.add(point);
        }

        List<Clusters> clustersList = new ArrayList<>();
        KMeanPoints kMeanPoints = new KMeanPoints();

        //Creating a list of set of points
        for(int j = 0 ; j<k ; ++j){
            Clusters cluster = new Clusters();
            clustersList.add(cluster);
        }

        int lastRandom = -1;

        //Getting k random points from the generated set of points
        for(int j = 0 ; j<k ; ++j){
            int currRandom = new Random().nextInt(100);
            if(currRandom != lastRandom)
                lastRandom = currRandom;
            else{
                currRandom = new Random().nextInt(100);
            }
            Point<Double> point = pointsList.get(currRandom);

            clustersList.get(j).addPointToSet(point);
            kMeanPoints.addPoint(point);
        }

        //Clustering all points in set by calculating distances
        for(int i = 0 ; i<convergenceCount ; ++i) {

            for (Point<Double> p : pointsList) {
                double min = Double.MAX_VALUE;
                int position = -1;

                for(Point<Double> kmean : kMeanPoints.getMeans()){
                    double distance = getDistance(p, kmean);
                    if(distance < min){
                        min = distance;
                        ++position;
                    }
                }

                clustersList.get(position).addPointToSet(p);
            }

            recalculateMeans(clustersList, kMeanPoints, i != convergenceCount - 1);
            showKMeanPoints(kMeanPoints, i);
        }

        System.out.println("\n\nFinal Clusters: \n");
        showClusters(clustersList, kMeanPoints);
    }

    static void showKMeanPoints(KMeanPoints kMeanPoints, int iteration){
        int i = 0;
        System.out.println("At iteration " + (iteration+1) + ":");
        for(Point<Double> point : kMeanPoints.getMeans()){
            System.out.println("\tKMean Point " + (i+1) + " = (" + point.getX() + ", " + point.getY() + ")");
            ++i;
        }
    }

    static void recalculateMeans(List<Clusters> clustersList, KMeanPoints kMeanPoints, boolean clearSet){
        int i = 0;
//        for(HashSet<Point<Double>> set : clustersList){
        for(Clusters cluster : clustersList){
            HashSet<Point<Double>> set = cluster.getCluster();

            Iterator<Point<Double>> iterator = set.iterator();


            double xSum = 0, ySum = 0;
            int total = 0;

            for(Point<Double> point : set){
                xSum += point.getX();
                ySum += point.getY();
                ++total;
            }

            xSum /= total;
            ySum /= total;

            if(clearSet)
                set.clear();

            Point<Double> newPoint = new Point<>(xSum, ySum);
            kMeanPoints.getMeans().set(i, newPoint);
            ++i;
        }
        System.out.println();
    }

    static double getDistance(Point<Double> point, Point<Double> meanPoint){
        return Math.sqrt(Math.pow(point.getX() - meanPoint.getX(), 2) + Math.pow(point.getY() - meanPoint.getY(), 2));
    }

    static void showClusters(List<Clusters> clustersList,  KMeanPoints kMeanPoints){
        int i = 0;
        for(Clusters c : clustersList){
            HashSet<Point<Double>> set = c.getCluster();
            System.out.println("\tCluster " + (i+1) + " (size = " + set.size() + ")");
            for (Point<Double> point : set) {
                System.out.println("\t\t(" + point.getX() + ", " + point.getY() + ")");
            }
            System.out.println();
            ++i;
        }
    }

}
