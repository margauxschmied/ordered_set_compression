import compressor.Complementary;
import compressor.Composition;
import compressor.RunLength;
import compressor.StreamVByte;
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


public class BigDataSetTest extends DataTest{


    @Test
    void compressRunlength() throws IOException {
        compressRunlength("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetRunlength.txt");

        sizeInt("dataset/bigDataSet/compress/compressBigDatasetRunlength.txt", "dataset/bigDataSet/size/sizeBigDatasetRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/bigDataSet/compress/compressBigDatasetRunlength.txt", "dataset/bigDataSet/decompress/decompressBigDatasetRunlength.txt");

        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetHuffman.txt", "dataset/bigDataSet/compress/treeBigDatasetHuffman.txt");

        sizeString("dataset/bigDataSet/compress/compressBigDatasetHuffman.txt", "dataset/bigDataSet/size/sizeBigDatasetHuffman.txt");
        sizeInt("dataset/bigDataSet/compress/treeBigDatasetHuffman.txt", "dataset/bigDataSet/size/sizeTreeBigDatasetHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/bigDataSet/compress/compressBigDatasetHuffman.txt", "dataset/bigDataSet/decompress/decompressBigDatasetHuffman.txt", "dataset/bigDataSet/compress/treeBigDatasetHuffman.txt");

        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetDiff.txt");

        sizeInt("dataset/bigDataSet/compress/compressBigDatasetDiff.txt", "dataset/bigDataSet/size/sizeBigDatasetDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/bigDataSet/compress/compressBigDatasetDiff.txt", "dataset/bigDataSet/decompress/decompressBigDatasetDiff.txt");

        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetRunlengthDiff.txt");

        sizeInt("dataset/bigDataSet/compress/compressBigDatasetRunlengthDiff.txt", "dataset/bigDataSet/size/sizeBigDatasetRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/bigDataSet/compress/compressBigDatasetRunlengthDiff.txt", "dataset/bigDataSet/decompress/decompressBigDatasetRunlengthDiff.txt");


        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetRunlengthDiff.txt");

    }

    @Test
    void compressComplementary() throws IOException {
        sizeInt("dataset/bigDataset_format.txt", "dataset/bigDataSet/size/sizeBigDataset.txt");
        compressComplementary("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetComplementary.txt");
        sizeInt("dataset/bigDataSet/compress/compressBigDatasetComplementary.txt", "dataset/bigDataSet/size/sizeBigDatasetComplementary.txt");
    }

    @Test
    void decompressComplementary() throws IOException {
        decompressComplementary("dataset/bigDataSet/compress/compressBigDatasetComplementary.txt", "dataset/bigDataSet/decompress/decompressBigDatasetComplementary.txt");


        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetComplementary.txt");

    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetComplementary2.txt");
        sizeInt("dataset/bigDataSet/compress/compressBigDatasetComplementary2.txt", "dataset/bigDataSet/size/sizeBigDatasetComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/bigDataSet/compress/compressBigDatasetComplementary2.txt", "dataset/bigDataSet/decompress/decompressBigDatasetComplementary2.txt");


        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetStreamVByte.txt");
        sizeString("dataset/bigDataSet/compress/compressBigDatasetStreamVByte.txt", "dataset/bigDataSet/size/sizeBigDatasetStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/bigDataSet/compress/compressBigDatasetStreamVByte.txt", "dataset/bigDataSet/decompress/decompressBigDatasetStreamVByte.txt");


        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/bigDataset_format.txt", "dataset/bigDataSet/compress/compressBigDatasetCompose.txt");
        sizeString("dataset/bigDataSet/compress/compressBigDatasetCompose.txt", "dataset/bigDataSet/size/sizeBigDatasetCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/bigDataSet/compress/compressBigDatasetCompose.txt", "dataset/bigDataSet/decompress/decompressBigDatasetCompose.txt");

        verif("dataset/bigDataset_format.txt", "dataset/bigDataSet/decompress/decompressBigDatasetCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}
