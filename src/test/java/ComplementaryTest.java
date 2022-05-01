import compressor.RunLength;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ComplementaryTest {
    List<Integer> toCompress;
    List<Integer> toDecompress;

    RunLength runLength;
    JSONParser jsonP = new JSONParser();
    ArrayList<Integer> listData;

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

    void creatList(String data) throws IOException {
        listData = new ArrayList<>();

        File doc =new File(data);
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            listData = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x->
                    Integer.valueOf(x)).collect(Collectors.toList());
        }
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

    @Test
    void compress0_100_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_1000.txt");
        assertEquals(new ArrayList<>(){{add(0); add(100);}}, runLength.complementary(listData));
    }

    @Test
    void compress0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt");
        assertEquals(new ArrayList<>(){{add(0); add(100);}}, runLength.complementary(listData));
    }

}
