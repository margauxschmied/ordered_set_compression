package decompressor;

import compressor.RunLength;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class RunLengthDecompressorTest {
    List<Integer> list;
    RunLength runLengthDecompressor;

    @BeforeEach
    void setUp() {
        runLengthDecompressor =new RunLength();
        list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(2);
    }

    @Test
    void decompress() {
        List<Integer> res=new ArrayList<>();
        res.add(2);
        res.add(7);
        res.add(9);
        res.add(11);
        assertEquals(res, runLengthDecompressor.decompresse(list));
    }
}