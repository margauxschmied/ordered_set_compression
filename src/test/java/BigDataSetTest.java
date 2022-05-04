import compressor.RunLength;
import compressor.huffman.Huffman;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BigDataSetTest {
    List<Integer> toCompress;
    List<Object> toDecompress;
    Huffman huffman;
    List listData;
    List listCompress;
    Map<Integer, String> mapTree;
    RunLength runLength = new RunLength();

    void creatListHuffman(String data, String compress, String tree) throws IOException {

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

        mapTree = new HashMap<>();
        List<String> tmp = new ArrayList<>();

        while (obj.hasNextLine()) {
            tmp = Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
                    String.valueOf(x)).collect(Collectors.toList());
        }

        for (int i = 0; i < tmp.size(); i+=2) {
            mapTree.put(Integer.valueOf(tmp.get(i)), tmp.get(i+1));
        }
    }
    void creatListRunlength(String data, String runlength) throws IOException {

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            listData = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
                    Integer.valueOf(x)).collect(Collectors.toList());
            System.out.println(runLength.compress(listData));

        }

//        doc = new File(runlength);
//        obj = new Scanner(doc);
//
//        listCompress = new ArrayList<>();
//
//        while (obj.hasNextLine()) {
//            listCompress = (ArrayList<Integer>) Arrays.asList(obj.nextLine().split(" ")).stream().map(x ->
//                    Integer.valueOf(x)).collect(Collectors.toList());
//
//        }
    }

    @Test
    void compress() throws IOException {
        creatListRunlength("dataset/Regin.txt", "dataset/runlength_0_100_1000.txt");
//        assertEquals(listCompress, runLength.compress(listData));
    }

    @Test
    void decompress() {
//        assertEquals(toCompress, runLength.decompress(toDecompress));
    }
}
