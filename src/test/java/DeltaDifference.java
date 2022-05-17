import compressor.RunLength;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeltaDifference {
    List<Integer> toCompress;
    List<Integer> toDecompress1;
    List<Integer> toDecompress4;

    RunLength runLength;

    @BeforeEach
    void setUp() {
        runLength = new RunLength();
        toCompress = new ArrayList<>();
        toCompress.add(2);
        toCompress.add(7);
        toCompress.add(9);
        toCompress.add(11);
        toCompress.add(11);

        toDecompress1 = new ArrayList<>();
        toDecompress1.add(2);
        toDecompress1.add(5);
        toDecompress1.add(2);
        toDecompress1.add(2);
        toDecompress1.add(0);

        toDecompress4 = new ArrayList<>();
        toDecompress4.add(2);
        toDecompress4.add(7);
        toDecompress4.add(9);
        toDecompress4.add(11);
        toDecompress4.add(9);
    }

    @Test
    void compress1() {
        assertEquals(toDecompress1, runLength.differencialDeltaSub(toCompress));
    }

    @Test
    void decompress1() {
        assertEquals(toCompress, runLength.differencialDeltaAdd(toDecompress1));
    }

    @Test
    void compress4() {
        assertEquals(toDecompress4, runLength.differencialDeltaSub4(toCompress));
    }

    @Test
    void decompress4() {
        assertEquals(toCompress, runLength.differencialDeltaAdd4(toDecompress4));
    }
}
