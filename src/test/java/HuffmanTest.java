import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {
    List<Object> toCompress;
    List<Object> toDecompress;
    Huffman huffman;

    @BeforeEach
    void setUp() {
        huffman = new Huffman();
        toCompress = new ArrayList<>();
        toCompress.add(2);
        toCompress.add(7);
        toCompress.add(9);
        toCompress.add(11);

        toDecompress = new ArrayList<>();
        toDecompress.add("00");
        toDecompress.add("11");
        toDecompress.add("10");
        toDecompress.add("01");

    }

    @Test
    void huffman() {
        HuffmanData huffmanData = huffman.compress(toCompress);
        assertEquals(toDecompress, huffmanData.getList());

        assertEquals(toCompress, huffman.decompress(new HuffmanData(huffmanData.getRoot(), toDecompress)));
    }

    @Test
    void huffman2() {
        toCompress = new ArrayList<>();
        toCompress.add(4);
        toCompress.add(3);
        toCompress.add(15);
        toCompress.add(4);
        toCompress.add(5);
        toCompress.add(0);
        toCompress.add(8);
        toCompress.add(21);
        toCompress.add(6);
        toCompress.add(6);
        toCompress.add(13);
        toCompress.add(1);
        toCompress.add(14);


        toDecompress = new ArrayList<>();
        toDecompress.add("101");
        toDecompress.add("0111");
        toDecompress.add("1111");
        toDecompress.add("101");
        toDecompress.add("0110");
        toDecompress.add("1110");
        toDecompress.add("1000");
        toDecompress.add("010");
        toDecompress.add("00");
        toDecompress.add("00");
        toDecompress.add("1001");
        toDecompress.add("1101");
        toDecompress.add("1100");

        HuffmanData huffmanData = huffman.compress(toCompress);

        assertEquals(toDecompress, huffmanData.getList());
        assertEquals(toCompress, huffman.decompress(new HuffmanData(huffmanData.getRoot(), toDecompress)));
    }

//    @Test
//    void huffman3() {
//        toCompress = new ArrayList<>();
//        toCompress.add(4);
//        toCompress.add(3);
//        toCompress.add(15);
//        toCompress.add(4);
//        toCompress.add(5);
//        toCompress.add(13);
//        toCompress.add(15);
//        toCompress.add(9);
//
//
//        assertEquals(toDecompress, huffman.compress(toCompress));
//        //assertEquals(toCompress, huffman.decompress(toDecompress));
//    }

    //4, 3, 15, 4, 5, 0, 8, 21, 6, 6, 13, 1, 14
    //4, 3, 15, 4, 5, 13, 15, 9

}
