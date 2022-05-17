import compressor.Complementary;
import compressor.RunLength;
import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;
import compressor.time.Stopwatch;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import java.util.Collections;


public class BigDataSetTest {
    List<Integer> toCompress;
    List<Object> toDecompress;
    Huffman huffman=new Huffman();
    List listData;
    List listCompress;
    Map<Integer, String> mapTree;
    RunLength runLength = new RunLength();
    Complementary complementary = new Complementary();

    Stopwatch stopwatch=new Stopwatch();

    void compressRunlength(String data, String runlength) throws IOException {
        PrintWriter writer = new PrintWriter(runlength);
        PrintWriter writerTime = new PrintWriter("dataset/timeCompressRunlength.txt");

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            String tmp=obj.nextLine();
            listData = Arrays.stream(tmp.split(" ")).map(Integer::valueOf).collect(Collectors.toList());

            stopwatch.start();
            compress= runLength.compress(listData);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();
            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);


            //TODO : https://attacomsian.com/blog/java-read-write-binary-files
//            System.out.println(result.getBytes().length);
//            System.out.println(tmp.getBytes().length);

//            System.out.println(Arrays.toString(result.getBytes()));
//            System.out.println(Arrays.toString(tmp.getBytes()));

        }
        writer.close();
        writerTime.close();

    }

    void decompressRunlength(String data, String runlength) throws IOException {
        PrintWriter writer = new PrintWriter(runlength);
        PrintWriter writerTime = new PrintWriter("dataset/timeDecompressRunlength.txt");

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= runLength.decompress(listData);
            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void compressHuffman(String data, String huffmanFile, String tree) throws IOException {
        PrintWriter writerHuffman = new PrintWriter(huffmanFile);
        PrintWriter writerTree = new PrintWriter(tree);
        PrintWriter writerTime = new PrintWriter("dataset/timeCompressHuffman.txt");



        listData = new ArrayList<>();
        List compress =new ArrayList<>();

        HuffmanData huffmanData;

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            huffman=new Huffman();
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());

            stopwatch.start();

            huffmanData= huffman.compress(listData);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) huffmanData.getList().stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);

            writerHuffman.println(result);

            HuffmanData finalHuffmanData = huffmanData;
            String mapAsString = (String) huffmanData.getCode().keySet().stream()
                    .map(key -> key + " " + finalHuffmanData.getCode().get(key))
                    .collect(Collectors.joining(" "));

            writerTree.println(mapAsString);


        }

        writerHuffman.close();
        writerTree.close();
        writerTime.close();

    }

    void decompressHuffman(String data, String huffmanFile, String tree) throws IOException {
        PrintWriter writer = new PrintWriter(huffmanFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeDecompressHuffman.txt");


        listData = new ArrayList<>();
        List<String> code =new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        File doc2 = new File(tree);
        Scanner obj2 = new Scanner(doc2);

        Map<Integer, String> map = new HashMap<>();

        List decompress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(String::valueOf).collect(Collectors.toList());
            code= Arrays.stream(obj2.nextLine().split(" ")).map(String::valueOf).collect(Collectors.toList());

            stopwatch.start();

            decompress= huffman.decompress(listData, code);
            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            decompress= (List) decompress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", decompress);
            writer.println(result);



        }

        writer.close();
        writerTime.close();

    }

    void compressDiff(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeCompressDiff.txt");


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= runLength.differencialDeltaSub(listData);
            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void decompressDiff(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeDecompressDiff.txt");

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= runLength.differencialDeltaAdd(listData);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void compressRunlengthDiff(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeCompressRunlengthDiff.txt");


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= runLength.differencialDeltaSub(listData);
            compress= runLength.compress(compress);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void decompressRunlengthDiff(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeDecompressRunlengthDiff.txt");

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= runLength.decompress(listData);
            compress= runLength.differencialDeltaAdd(compress);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();
    }

    void compressComplementary(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeCompressComplementary.txt");


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= complementary.complementaryByRange(listData);
            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void decompressComplementary(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeDecompressComplementary.txt");

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= complementary.decomplementaryByRange(listData);


            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void compressComplementary2(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeCompressComplementary2.txt");


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= complementary.complementaryByRange2(listData);
//            compress=runLength.differencialDeltaSub0(compress);
            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void decompressComplementary2(String data, String compressFile) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter("dataset/timeDecompressComplementary2.txt");

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

//            compress=runLength.differencialDeltaAdd0(listData);
            compress= complementary.decomplementaryByRange2(listData);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void verif(String data, String decompress) throws FileNotFoundException {
        listData = new ArrayList<>();
        List listDecompress = new ArrayList<>();


        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        File doc2 = new File(decompress);
        Scanner obj2 = new Scanner(doc2);

        while (obj.hasNextLine()) {
            listData=(Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
            listDecompress=(Arrays.stream(obj2.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
            assertEquals(listData, listDecompress);
        }

    }

    void size(String data, String fileOutput) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileOutput);

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        listData = new ArrayList<Integer>();

        int size=0;


        while (obj.hasNextLine()) {
            size=0;
            String[] o=obj.nextLine().split(" ");
            try {
                listData = (Arrays.stream(o).map(Integer::valueOf).collect(Collectors.toList()));
                int max=(int) Collections.max(listData);
                int tmp=max%8;
                max+=tmp;
                size=max*listData.size();
            } catch (Exception e) {
                listData = (Arrays.stream(o).map(String::valueOf).collect(Collectors.toList()));
                for (Object i : listData) {
                    size+=((String) i).length();
                }
            }


            writer.println(size);
        }
        writer.close();
    }

    @Test
    void compressRunlength() throws IOException {
        compressRunlength("dataset/bigDataset_format.txt", "dataset/compressBigDatasetRunlength.txt");

        size("dataset/compressBigDatasetRunlength.txt", "dataset/sizeBigDatasetRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/compressBigDatasetRunlength.txt", "dataset/decompressBigDatasetRunlength.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/bigDataset_format.txt", "dataset/compressBigDatasetHuffman.txt", "dataset/treeBigDatasetHuffman.txt");

        size("dataset/compressBigDatasetHuffman.txt", "dataset/sizeBigDatasetHuffman.txt");
        size("dataset/treeBigDatasetHuffman.txt", "dataset/sizeTreeBigDatasetHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/compressBigDatasetHuffman.txt", "dataset/decompressBigDatasetHuffman.txt", "dataset/treeBigDatasetHuffman.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/bigDataset_format.txt", "dataset/compressBigDatasetDiff.txt");

        size("dataset/compressBigDatasetDiff.txt", "dataset/sizeBigDatasetDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/compressBigDatasetDiff.txt", "dataset/decompressBigDatasetDiff.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/bigDataset_format.txt", "dataset/compressBigDatasetRunlengthDiff.txt");

        size("dataset/compressBigDatasetRunlengthDiff.txt", "dataset/sizeBigDatasetRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/compressBigDatasetRunlengthDiff.txt", "dataset/decompressBigDatasetRunlengthDiff.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetRunlengthDiff.txt");

    }

    @Test
    void compressComplementary() throws IOException {
        size("dataset/bigDataset_format.txt", "dataset/sizeBigDataset.txt");
        compressComplementary("dataset/bigDataset_format.txt", "dataset/compressBigDatasetComplementary.txt");
        size("dataset/compressBigDatasetComplementary.txt", "dataset/sizeBigDatasetComplementary.txt");
    }

    @Test
    void decompressComplementary() throws IOException {
        decompressComplementary("dataset/compressBigDatasetComplementary.txt", "dataset/decompressBigDatasetComplementary.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetComplementary.txt");

    }

    @Test
    void compressComplementary2() throws IOException {
        size("dataset/bigDataset_format.txt", "dataset/sizeBigDataset.txt");
        System.out.println("ok");
        compressComplementary2("dataset/bigDataset_format.txt", "dataset/compressBigDatasetComplementary2.txt");
        System.out.println("ok");
        size("dataset/compressBigDatasetComplementary2.txt", "dataset/sizeBigDatasetComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/compressBigDatasetComplementary2.txt", "dataset/decompressBigDatasetComplementary2.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetComplementary2.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}
