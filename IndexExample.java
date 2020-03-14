import java.util.Iterator;

public class IndexExample {
    public static void main(String[] args) {
        for (Index i = new Index(); i.getValue() < 10; i.increment()) {
            System.out.println(i);
        }

        for (Integer k : new IterableIndex(0, 10, 1)) {
            System.out.println("This is " + k);
        }
    }
}

class Index {
    private int value;
    private int factor;

    public Index() {
        this(0, 1);
    }

    public Index(int initial, int factor) {
        value = initial;
        this.factor = factor;
    }

    public int getValue() {
        return value;
    }

    public int getFactor() {
        return factor;
    }

    public void increment() {
        value += factor;
    }

    public void decrement() {
        value -= factor;
    }

    public String toString() {
        return String.format("%d",value);
    }
}

class IterableIndex implements Iterable<Integer> {
    Index i;
    int maximum;

    class IndexIterator implements Iterator<Integer> {
        public boolean hasNext() {
            return i.getValue() < maximum;
        }

        public Integer next() {
            Integer val = i.getValue();
            if (hasNext()) {
                i.increment();
                return val;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public IterableIndex(int initial, int maximum, int factor) {
        i = new Index(initial, factor);
        this.maximum = maximum;
    }

    public Iterator<Integer> iterator() {
        return new IndexIterator();
    }
}