package compressor.huffman;

import java.util.Comparator;

public class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        if (o1.frequency < o2.frequency) {
            return -1;
        } else if (o1.frequency > o2.frequency) {
            return 1;
        }
        return 0;
    }
}
