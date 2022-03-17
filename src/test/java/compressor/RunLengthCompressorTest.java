package compressor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class RunLengthCompressorTest {
    List<Object> list;
    RunLength runLengthCompressor;

    @BeforeEach
    void setUp() {
        runLengthCompressor =new RunLength();
        list=new ArrayList<>();
        list.add(2);
        list.add(7);
        list.add(9);
        list.add(11);
    }

    @Test
    void compress() {
        List<Object> res=new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(1);
        res.add(5);
        res.add(2);
        res.add(2);
        assertEquals(res, runLengthCompressor.compress(list));
    }

}