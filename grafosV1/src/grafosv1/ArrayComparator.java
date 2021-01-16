package grafosv1;
import java.util.Comparator;
    //This is why I don't like java ¬¬
    class ArrayComparator implements Comparator<node> {
    @Override
    public int compare(node x, node y) {
        if (x.getWeight() < y.getWeight()) return -1;
        else if (x.getWeight() > y.getWeight()) return 1;
        else  return 0;
    }
}