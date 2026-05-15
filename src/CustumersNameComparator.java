import java.util.Comparator;

public class CustumersNameComparator implements Comparator<Custumer> {

    @Override
    public int compare(Custumer c1, Custumer c2) {
        int compareName = c1.getName().compareTo(c2.getName());
        if(compareName != 0) return compareName;
        return c2.getYears()- c1.getYears();

    }
}
