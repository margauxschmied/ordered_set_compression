import compressor.RunLength;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class RunLengthTest {
    List<Integer> toCompress;
    List<Integer> toDecompress;
    RunLength runLength;
    JSONParser jsonP = new JSONParser();
    ArrayList<Integer> listData;
    ArrayList<Integer> listRunlength;

    @BeforeEach
    void setUp() {
        runLength = new RunLength();
        toCompress = new ArrayList<>();
        toCompress.add(2);
        toCompress.add(2);
        toCompress.add(7);
        toCompress.add(9);
        toCompress.add(11);
        toCompress.add(11);
        toCompress.add(12);


        toDecompress = new ArrayList<>();
        toDecompress.add(2);
        toDecompress.add(2);
        toDecompress.add(1);
        toDecompress.add(7);
        toDecompress.add(1);
        toDecompress.add(9);
        toDecompress.add(2);
        toDecompress.add(11);
        toDecompress.add(1);
        toDecompress.add(12);
    }

    void creatList(String data, String runlength) throws IOException, ParseException {
        JSONArray jsonData = (JSONArray) jsonP.parse(new FileReader(data));

        listData = new ArrayList<>();

        for (Object jsonDatum : jsonData) {
            listData.add((int) (long) jsonDatum);
        }

        JSONArray jsonRunlength = (JSONArray) jsonP.parse(new FileReader(runlength));

        listRunlength = new ArrayList<>();

        for (Object o : jsonRunlength) {
            listRunlength.add((int) (long) o);
        }
    }

    @Test
    void compress() {
        assertEquals(toDecompress, runLength.compress(toCompress));
    }

    @Test
    void decompress() {
        assertEquals(toCompress, runLength.decompress(toDecompress));
    }

    @Test
    void compress0_100_1000() throws IOException, ParseException {
        LocalTime before = LocalTime.now();
        creatList("dataset/dataset_0_100_1000.json", "dataset/runlength_0_100_1000.json");
        LocalTime after = LocalTime.now();
        System.out.print(after.getHour()-before.getHour());
        System.out.print(":");
        System.out.print(after.getMinute()-before.getMinute());
        System.out.print(":");
        System.out.print(after.getSecond()-before.getSecond());
        System.out.print(":");
        System.out.println(after.getNano()-before.getNano());

        assertEquals(listRunlength, runLength.compress(listData));

    }

    @Test
    void decompress0_100_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_1000.json", "dataset/runlength_0_100_1000.json");
        assertEquals(listData, runLength.decompress(listRunlength));
    }

    @Test
    void compress0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.json", "dataset/runlength_0_100_100000.json");
        assertEquals(listRunlength, runLength.compress(listData));
    }

    @Test
    void decompress0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.json", "dataset/runlength_0_100_100000.json");
        assertEquals(listData, runLength.decompress(listRunlength));
    }
    @Test
    void compress0_100000_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_1000.json", "dataset/runlength_0_100000_1000.json");
        assertEquals(listRunlength, runLength.compress(listData));
    }

    @Test
    void decompress0_100000_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_1000.json", "dataset/runlength_0_100000_1000.json");
        assertEquals(listData, runLength.decompress(listRunlength));
    }

    @Test
    void compress0_100000_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_100000.json", "dataset/runlength_0_100000_100000.json");
        assertEquals(listRunlength, runLength.compress(listData));
    }

    @Test
    void decompress0_100000_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_100000.json", "dataset/runlength_0_100000_100000.json");
        assertEquals(listData, runLength.decompress(listRunlength));
    }



}