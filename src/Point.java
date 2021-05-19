public class Point<T> {
    T x;
    T y;

    Point(T x, T y){
        this.x = x;
        this.y = y;
    }

    public T getX(){
        return x;
    }

    public T getY(){
        return y;
    }
}
