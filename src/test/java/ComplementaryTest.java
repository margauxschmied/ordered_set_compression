import compressor.Complementary;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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

    Complementary complementary;
    ArrayList<Integer> listData;

    @BeforeEach
    void setUp() {
        complementary = new Complementary();
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

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            listData = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
                    Integer.valueOf(x)).collect(Collectors.toList());
        }
    }

    @Test
    void compress() {
        assertEquals(toDecompress, complementary.complementary(toCompress));
    }

    @Test
    void decompress() {
        assertEquals(toCompress, complementary.complementary(toDecompress));
    }

    @Test
    void compress0() {
        assertEquals(new ArrayList<>() {{
            add(0);
            add(0);
        }}, complementary.compress(new ArrayList<>() {{
            add(0);
        }}));
    }

    @Test
    void compress0_100_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_1000.txt");
        assertEquals(new ArrayList<>() {{
            add(0);
            add(100);
        }}, complementary.complementary(listData));
    }

    @Test
    void compress0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt");
        assertEquals(new ArrayList<>() {{
            add(0);
            add(100);
        }}, complementary.complementary(listData));
    }

    @Test
    void compress0_100_100000_by_range() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt");
        assertEquals(new ArrayList<>() {{
            add(0);
            add(100);
        }}, complementary.complementary(listData));
    }

    @Test
    void compress1() {
        assertEquals(new ArrayList<>() {{
            add(2);
            add(1);
        }}, complementary.complementary(new ArrayList<>() {{
            add(2);
            add(1);
        }}));
    }
}
