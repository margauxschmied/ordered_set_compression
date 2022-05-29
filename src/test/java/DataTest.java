import compressor.Complementary;
import compressor.Composition;
import compressor.RunLength;
import compressor.StreamVByte;
import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;
import compressor.time.Stopwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DataTest {
    Huffman huffman=new Huffman();
    List listData;
    List listCompress;
    Map<Integer, String> mapTree;
    RunLength runLength = new RunLength();
    Complementary complementary = new Complementary();
    StreamVByte streamVByte = new StreamVByte();
    Composition composition=new Composition();
    Stopwatch stopwatch=new Stopwatch();

    void compressRunlength(String data, String runlength, String time) throws IOException {
        PrintWriter writer = new PrintWriter(runlength);
        PrintWriter writerTime = new PrintWriter(time);

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

    void decompressRunlength(String data, String runlength, String time) throws IOException {
        PrintWriter writer = new PrintWriter(runlength);
        PrintWriter writerTime = new PrintWriter(time);

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

    void compressHuffman(String data, String huffmanFile, String tree, String time) throws IOException {
        PrintWriter writerHuffman = new PrintWriter(huffmanFile);
        PrintWriter writerTree = new PrintWriter(tree);
        PrintWriter writerTime = new PrintWriter(time);



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

    void decompressHuffman(String data, String huffmanFile, String tree, String time) throws IOException {
        PrintWriter writer = new PrintWriter(huffmanFile);
        PrintWriter writerTime = new PrintWriter(time);


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

    void compressDiff(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);


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

    void decompressDiff(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);

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

    void compressRunlengthDiff(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);


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

    void decompressRunlengthDiff(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);

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

    void compressComplementary(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= complementary.compress(listData);
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

    void decompressComplementary(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= complementary.decompress(listData);


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

    void compressComplementary2(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= complementary.compress2(listData);
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

    void decompressComplementary2(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

//            compress=runLength.differencialDeltaAdd0(listData);
            compress= complementary.decompress2(listData);

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

    void compressStreamVByte(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);


        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= streamVByte.compress(listData);
//            compress=runLength.differencialDeltaSub0(compress);
            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();

//            compress= (List) compress.stream().map(String::valueOf).collect(Collectors.toList());

            String result = String.join("\n", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void decompressStreamVByte(String data, String compressFile, String time) throws IOException {
        PrintWriter writer = new PrintWriter(compressFile);
        PrintWriter writerTime = new PrintWriter(time);

        String listData;
        String listSize;

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = obj.nextLine();       //Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            listSize = obj.nextLine();       //Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());

            stopwatch.start();

//            compress=runLength.differencialDeltaAdd0(listData);
            compress= streamVByte.decompress(listData, listSize);

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

    void compressCompose(String data, String runlength, String time) throws IOException {
        PrintWriter writer = new PrintWriter(runlength);
        PrintWriter writerTime = new PrintWriter(time);

        listData = new ArrayList<>();

        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {

            listData = Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            stopwatch.start();

            compress= composition.compress(listData);

            stopwatch.stop();
            writerTime.println(stopwatch.lastElapsedNanoSeconds());
            stopwatch.reset();


            String result = String.join("\n", compress);
            writer.println(result);

        }
        writer.close();
        writerTime.close();

    }

    void decompressCompose(String data, String runlength, String time) throws IOException {
        PrintWriter writer = new PrintWriter(runlength);
        PrintWriter writerTime = new PrintWriter(time);

        String listData;
        String listSize;


        File doc = new File(data);
        Scanner obj = new Scanner(doc);
        List compress;

        while (obj.hasNextLine()) {
            listData = obj.nextLine();       //Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            listSize = obj.nextLine();       //Arrays.stream(obj.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());

            stopwatch.start();

            compress= composition.decompress(listData, listSize);

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

    void sizeInt(String data, String fileOutput) throws FileNotFoundException {
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
                int max = Integer.toBinaryString((int) Collections.max(listData)).length();
                int tmp = 8 - (max % 8);
                max += tmp;
                size = max * listData.size();
            }
            catch (Exception e){
                listData = (Arrays.stream(o).map(String::valueOf).collect(Collectors.toList()));
                for (Object i : listData) {
                    size+=((String) i).length();
                }
            }



            writer.println(size);
        }
        writer.close();
    }

    void sizeString(String data, String fileOutput) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileOutput);

        File doc = new File(data);
        Scanner obj = new Scanner(doc);

        listData = new ArrayList<Integer>();

        int size=0;


        while (obj.hasNextLine()) {
            size=0;
            String[] o=obj.nextLine().split(" ");
            listData = (Arrays.stream(o).map(String::valueOf).collect(Collectors.toList()));
            for (Object i : listData) {
                size+=((String) i).length();
            }


            writer.println(size);
        }
        writer.close();
    }
}
