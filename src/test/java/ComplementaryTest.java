import compressor.RunLength;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComplementaryTest {
    List<Object> toCompress;
    List<Object> toDecompress;

    RunLength runLength;

    @BeforeEach
    void setUp() {
        runLength = new RunLength();
        toCompress = new ArrayList<>();
        toCompress.add(2);
        toCompress.add(7);
        toCompress.add(9);
        toCompress.add(11);
        toCompress.add(12);

        toDecompress = new ArrayList<>();
        toDecompress.add(2);
        toDecompress.add(3);
        toDecompress.add(4);
        toDecompress.add(5);
        toDecompress.add(6);
        toDecompress.add(8);
        toDecompress.add(10);
        toDecompress.add(12);

    }

    @Test
    void compress() {
        assertEquals(toDecompress, runLength.complementary(toCompress));
    }

    @Test
    void decompress() {
        assertEquals(toCompress, runLength.complementary(toDecompress));
    }

    @Test
    void compress0() {
        assertEquals(new ArrayList<>(){{add(0); add(0);}}, runLength.complementary(new ArrayList<>(){{add(0);}}));
    }

}
