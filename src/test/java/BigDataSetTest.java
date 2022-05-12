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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BigDataSetTest {
    List<Integer> toCompress;
    List<Object> toDecompress;
    Huffman huffman=new Huffman();
    List listData;
    List listCompress;
    Map<Integer, String> mapTree;
    RunLength runLength = new RunLength();
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

            compress= runLength.complementaryByRange(listData);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
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

            compress= runLength.complementaryByRange(listData);


            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join(" ", compress);
            writer.println(result);

        }
        writer.close();
    }

    void verif(String data, String decompress) throws FileNotFoundException {
        listData = new ArrayList<>();
        List listDecompress = new ArrayList<>();


        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        File doc2 = new File(decompress);
        Scanner obj2 = new Scanner(doc2);
        boolean b=true;

        while (obj.hasNextLine()) {
            listData=(Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
            listDecompress=(Arrays.stream(obj2.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
            assertEquals(listData, listDecompress);
        }

    }

    @Test
    void compressRunlength() throws IOException {
        compressRunlength("dataset/Regin_format.txt", "dataset/compressBigDatasetRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/compressBigDatasetRunlength.txt", "dataset/decompressBigDatasetRunlength.txt");

        verif("dataset/Regin_format.txt", "dataset/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/Regin_format.txt", "dataset/compressBigDatasetHuffman.txt", "dataset/treeBigDatasetHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/compressBigDatasetHuffman.txt", "dataset/decompressBigDatasetHuffman.txt", "dataset/treeBigDatasetHuffman.txt");

        verif("dataset/Regin_format.txt", "dataset/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/Regin_format.txt", "dataset/compressBigDatasetDiff.txt");

    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/compressBigDatasetDiff.txt", "dataset/decompressBigDatasetDiff.txt");

        verif("dataset/Regin_format.txt", "dataset/decompressBigDatasetDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/Regin_format.txt", "dataset/compressBigDatasetRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/compressBigDatasetRunlengthDiff.txt", "dataset/decompressBigDatasetRunlengthDiff.txt");


        verif("dataset/Regin_format.txt", "dataset/decompressBigDatasetRunlengthDiff.txt");

    }

    @Test
    void compressComplementary() throws IOException {
        compressComplementary("dataset/Regin_format_without_repetition.txt", "dataset/compressBigDatasetComplementary.txt");
    }

    @Test
    void decompressComplementary() throws IOException {
        decompressComplementary("dataset/compressBigDatasetComplementary.txt", "dataset/decompressBigDatasetComplementary.txt");


        verif("dataset/Regin_format_without_repetition.txt", "dataset/decompressBigDatasetComplementary.txt");

    }
}
