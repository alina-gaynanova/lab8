package utilities.idobjects;

import java.util.Comparator;

public class IdComparator implements Comparator<IdObject> {
    @Override
    public int compare(IdObject o1, IdObject o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.getId()-o2.getId();
    }
}
