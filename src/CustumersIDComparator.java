import java.util.Comparator;

public class CustumersIDComparator implements Comparator<Custumer> {

    @Override
    public int compare(Custumer c1, Custumer c2) {
        int IDCompate = Double.compare(c2.getId(), c1.getId());
        if (IDCompate == 0) {
            return c1.getName().compareTo(c2.getName());
        } else {
            return c1.getId()-c2.getId();
        }
    }
}
