import compressor.RunLength;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class RunLengthTest {
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

        toDecompress = new ArrayList<>();
        toDecompress.add(1);
        toDecompress.add(2);
        toDecompress.add(1);
        toDecompress.add(7);
        toDecompress.add(1);
        toDecompress.add(9);
        toDecompress.add(1);
        toDecompress.add(11);
    }

    @Test
    void compress() {
        assertEquals(toDecompress, runLength.compress(toCompress));
    }

    @Test
    void decompress() {
        assertEquals(toCompress, runLength.decompress(toDecompress));
    }

}