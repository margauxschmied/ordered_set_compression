import compressor.RunLength;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

class RunLengthTest {
    List<Integer> toCompress;
    List<Integer> toDecompress;
    RunLength runLength;
    JSONParser jsonP = new JSONParser();
    ArrayList<Integer> listData;
    ArrayList<Integer> listCompress;

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

        listData = new ArrayList<>();

        File doc =new File(data);
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            listData = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x->
                    Integer.valueOf(x)).collect(Collectors.toList());
        }

        doc =new File(runlength);
        obj = new Scanner(doc);

        listCompress = new ArrayList<>();

        while (obj.hasNextLine()) {
            listCompress = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x->
                    Integer.valueOf(x)).collect(Collectors.toList());

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
        creatList("dataset/dataset_0_100_1000.txt", "dataset/runlength_0_100_1000.txt");
        LocalTime after = LocalTime.now();
        System.out.print(after.getHour()-before.getHour());
        System.out.print(":");
        System.out.print(after.getMinute()-before.getMinute());
        System.out.print(":");
        System.out.print(after.getSecond()-before.getSecond());
        System.out.print(":");
        System.out.println(after.getNano()-before.getNano());

        assertEquals(listCompress, runLength.compress(listData));

    }

    @Test
    void decompress0_100_1000() throws Exception {
        creatList("dataset/dataset_0_100_1000.txt", "dataset/runlength_0_100_1000.txt");
        assertEquals(listData, runLength.decompress(listCompress));
    }

    @Test
    void compress0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt", "dataset/runlength_0_100_100000.txt");
        assertEquals(listCompress, runLength.compress(listData));
    }

    @Test
    void decompress0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt", "dataset/runlength_0_100_100000.txt");
        assertEquals(listData, runLength.decompress(listCompress));
    }
    @Test
    void compress0_100000_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_1000.txt", "dataset/runlength_0_100000_1000.txt");
        assertEquals(listCompress, runLength.compress(listData));
    }

    @Test
    void decompress0_100000_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_1000.txt", "dataset/runlength_0_100000_1000.txt");
        assertEquals(listData, runLength.decompress(listCompress));
    }

    @Test
    void compress0_100000_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_100000.txt", "dataset/runlength_0_100000_100000.txt");
        assertEquals(listCompress, runLength.compress(listData));
    }

    @Test
    void decompress0_100000_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100000_100000.txt", "dataset/runlength_0_100000_100000.txt");
        assertEquals(listData, runLength.decompress(listCompress));
    }



}