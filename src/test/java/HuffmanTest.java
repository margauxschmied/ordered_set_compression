import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {
    List<Integer> toCompress;
    List<String> toDecompress;
    Huffman huffman;
    ArrayList<Integer> listData;
    ArrayList<String> listCompress;
    List mapTree;

    @BeforeEach
    void setUp() {
        huffman = new Huffman();
        toCompress = new ArrayList<>();
        toCompress.add(2);
        toCompress.add(7);
        toCompress.add(9);
        toCompress.add(11);

        toDecompress = new ArrayList<>();
        toDecompress.add("00");
        toDecompress.add("11");
        toDecompress.add("10");
        toDecompress.add("01");

    }

    void creatList(String data, String compress, String tree) throws IOException {

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            listData = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
                    Integer.valueOf(x)).collect(Collectors.toList());
        }

        doc = new File(compress);
        obj = new Scanner(doc);

        listCompress = new ArrayList<>();

        while (obj.hasNextLine()) {
            listCompress = (ArrayList<String>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
                    String.valueOf(x)).collect(Collectors.toList());

        }

        doc = new File(tree);
        obj = new Scanner(doc);

        mapTree = new ArrayList<>();

        while (obj.hasNextLine()) {
            mapTree = Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
                    String.valueOf(x)).collect(Collectors.toList());
        }
    }

    @Test
    void huffman() {
        HuffmanData huffmanData = huffman.compress(toCompress);
        assertEquals(toDecompress, huffmanData.getList());

        assertEquals(toCompress, huffman.decompress(new HuffmanData(huffmanData.getReverseCode(), toDecompress)));
    }

    @Test
    void huffman2() {
        toCompress = new ArrayList<>();
        toCompress.add(4);
        toCompress.add(3);
        toCompress.add(15);
        toCompress.add(4);
        toCompress.add(5);
        toCompress.add(0);
        toCompress.add(8);
        toCompress.add(21);
        toCompress.add(6);
        toCompress.add(6);
        toCompress.add(13);
        toCompress.add(1);
        toCompress.add(14);


        toDecompress = new ArrayList<>();
        toDecompress.add("101");
        toDecompress.add("0111");
        toDecompress.add("1111");
        toDecompress.add("101");
        toDecompress.add("0110");
        toDecompress.add("1110");
        toDecompress.add("1000");
        toDecompress.add("010");
        toDecompress.add("00");
        toDecompress.add("00");
        toDecompress.add("1001");
        toDecompress.add("1101");
        toDecompress.add("1100");

        HuffmanData huffmanData = huffman.compress(toCompress);

        assertEquals(toDecompress, huffmanData.getList());
        assertEquals(toCompress, huffman.decompress(new HuffmanData(huffmanData.getReverseCode(), toDecompress)));
    }

    @Test
    void huffman0_100_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_1000.txt", "dataset/huffman_0_100_1000.txt", "dataset/tree_0_100_1000.txt");
        HuffmanData huffmanData = huffman.compress(listData);

        assertEquals(listCompress, huffmanData.getList());
//        assertEquals(huffmanData.getCode(), mapTree);
        assertEquals(listData, huffman.decompress(listCompress, mapTree));

    }

    @Test
    void huffman0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt", "dataset/huffman_0_100_100000.txt", "dataset/tree_0_100_100000.txt");
        HuffmanData huffmanData = huffman.compress(listData);

        assertEquals(listCompress, huffmanData.getList());
//        assertEquals(huffmanData.getCode(), mapTree);
        assertEquals(listData, huffman.decompress(listCompress, mapTree));

    }


    @Test
    void huffman3() {
        toCompress = new ArrayList<>();
        toCompress.add(1);
        toCompress.add(3);
        toCompress.add(4);
        toCompress.add(1);
        toCompress.add(5);
        toCompress.add(6);
        toCompress.add(4);


        toDecompress = new ArrayList<>();
        toDecompress.add("11");
        toDecompress.add("010");
        toDecompress.add("10");
        toDecompress.add("11");
        toDecompress.add("00");
        toDecompress.add("011");
        toDecompress.add("10");


        assertEquals(toDecompress, huffman.compress(toCompress).getList());

//        assertEquals(toCompress, huffman.decompress(toDecompress));
    }

    //4, 3, 15, 4, 5, 0, 8, 21, 6, 6, 13, 1, 14
    //4, 3, 15, 4, 5, 13, 15, 9

}
