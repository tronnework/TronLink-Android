package j$.util;

import j$.util.Spliterators;
import java.util.SortedSet;
public abstract class SortedSet$-CC {
    public static Spliterator $default$spliterator(final SortedSet sortedSet) {
        return new Spliterators.IteratorSpliterator(sortedSet, 21) {
            @Override
            public java.util.Comparator getComparator() {
                return sortedSet.comparator();
            }
        };
    }
}
