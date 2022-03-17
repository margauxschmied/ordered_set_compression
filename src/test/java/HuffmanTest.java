import compressor.RunLength;
import compressor.huffman.Huffman;
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
        huffman =new Huffman();
        toCompress=new ArrayList<>();
        toCompress.add(2);
        toCompress.add(7);
        toCompress.add(9);
        toCompress.add(11);

        toDecompress=new ArrayList<>();
        toDecompress.add("00");
        toDecompress.add("11");
        toDecompress.add("10");
        toDecompress.add("01");

    }

    @Test
    void compress() {
        assertEquals(toDecompress, huffman.compress(toCompress));
    }

    @Test
    void decompress() {
        //System.out.println((Integer) toDecompress.get(0));
        assertEquals(toCompress, huffman.decompress(toDecompress));
    }

}
