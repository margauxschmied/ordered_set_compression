import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {
    List<Integer> toCompress;
    List<Object> toDecompress;
    Huffman huffman;
    JSONParser jsonP = new JSONParser();
    ArrayList<Integer> listData;
    ArrayList<Object> listHuffman;
    Map<Integer, String> mapTree;

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

    void creatList(String data, String runlength, String tree) throws IOException, ParseException {
        JSONArray jsonData = (JSONArray) jsonP.parse(new FileReader(data));

        listData = new ArrayList<>();

        for (Object jsonDatum : jsonData) {
            listData.add((int) (long) jsonDatum);
        }

        JSONArray jsonRunlength = (JSONArray) jsonP.parse(new FileReader(runlength));

        listHuffman = new ArrayList<>();

        for (Object o : jsonRunlength) {
            listHuffman.add(o);
        }

        JSONObject jsonTree = (JSONObject) jsonP.parse(new FileReader(tree));

        mapTree = new HashMap();

        for (int i=0; i<jsonTree.size(); i++) {
//            System.out.println(jsonTree.get(i+""));
            mapTree.put(i, (String) jsonTree.get(i+""));
        }
    }

    @Test
    void huffman() {
        HuffmanData huffmanData = huffman.compress(toCompress);
        assertEquals(toDecompress, huffmanData.getList());

        assertEquals(toCompress, huffman.decompress(new HuffmanData(huffmanData.getCode(), toDecompress)));
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
        assertEquals(toCompress, huffman.decompress(new HuffmanData(huffmanData.getCode(), toDecompress)));
    }

    @Test
    void huffman0_100_1000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_1000.txt", "dataset/huffman_0_100_1000.json", "dataset/tree_0_100_1000.json");
        HuffmanData huffmanData = huffman.compress(listData);

        assertEquals(listHuffman, huffmanData.getList());
        assertEquals(listData, huffman.decompress(new HuffmanData(mapTree, listHuffman)));

    }

    @Test
    void huffman0_100_100000() throws IOException, ParseException {
        creatList("dataset/dataset_0_100_100000.txt", "dataset/huffman_0_100_100000.json", "dataset/tree_0_100_100000.json");
        HuffmanData huffmanData = huffman.compress(listData);

        assertEquals(listHuffman, huffmanData.getList());
        assertEquals(listData, huffman.decompress(new HuffmanData(mapTree, listHuffman)));

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

        System.out.println(toCompress);

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
