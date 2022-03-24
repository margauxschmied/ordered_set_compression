
import compressor.RunLength;
import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        RunLength runLengthCompressor = new RunLength();

        List<Object> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        list.add(9);
        list.add(11);
        List<Object> lc = runLengthCompressor.compress(list);
        System.out.println(lc);

        List<Object> ld = runLengthCompressor.decompress(lc);
        System.out.println(ld);

        Huffman huffman = new Huffman();

        list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(2);
        list.add(7);
        list.add(9);
        list.add(11);
        list.add(11);

        HuffmanData huffmanData = huffman.compress(list);
        System.out.println(huffmanData.getList());
        System.out.println(huffman.decompress(huffmanData));

    }
}
