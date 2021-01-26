package grafosv1;
import java.util.ArrayList;
import java.util.Comparator;
    class Edge2Comparator implements Comparator<ArrayList<Integer>> {
    @Override
    public int compare(ArrayList<Integer> x, ArrayList<Integer> y) {
        if (x.get(2) > y.get(2)) return -1;
        else if (x.get(2) < y.get(2)) return 1;
        else  return 0;
    }
}