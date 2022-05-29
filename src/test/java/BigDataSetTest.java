import compressor.Complementary;
import compressor.Composition;
import compressor.RunLength;
import compressor.StreamVByte;
import compressor.huffman.Huffman;
import compressor.huffman.HuffmanData;
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
        compressRunlength("dataset/bigDataset_format.txt", "dataset/compressBigDatasetRunlength.txt", "dataset/timeBigDatasetBigDatasetCompressRunlength.txt");

        sizeInt("dataset/compressBigDatasetRunlength.txt", "dataset/sizeBigDatasetRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/compressBigDatasetRunlength.txt", "dataset/decompressBigDatasetRunlength.txt", "dataset/timeBigDatasetBigDatasetDecompressRunlength.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/bigDataset_format.txt", "dataset/compressBigDatasetHuffman.txt", "dataset/treeBigDatasetHuffman.txt", "dataset/timeBigDatasetBigDatasetCompressHuffman.txt");

        sizeString("dataset/compressBigDatasetHuffman.txt", "dataset/sizeBigDatasetHuffman.txt");
        sizeInt("dataset/treeBigDatasetHuffman.txt", "dataset/sizeTreeBigDatasetHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/compressBigDatasetHuffman.txt", "dataset/decompressBigDatasetHuffman.txt", "dataset/treeBigDatasetHuffman.txt", "dataset/timeBigDatasetBigDatasetDecompressHuffman.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/bigDataset_format.txt", "dataset/compressBigDatasetDiff.txt", "dataset/timeBigDatasetBigDatasetCompressDiff.txt");

        sizeInt("dataset/compressBigDatasetDiff.txt", "dataset/sizeBigDatasetDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/compressBigDatasetDiff.txt", "dataset/decompressBigDatasetDiff.txt", "dataset/timeBigDatasetBigDatasetDecompressDiff.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/bigDataset_format.txt", "dataset/compressBigDatasetRunlengthDiff.txt", "dataset/timeBigDatasetBigDatasetCompressRunlengthDiff.txt");

        sizeInt("dataset/compressBigDatasetRunlengthDiff.txt", "dataset/sizeBigDatasetRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/compressBigDatasetRunlengthDiff.txt", "dataset/decompressBigDatasetRunlengthDiff.txt", "dataset/timeBigDatasetBigDatasetDecompressRunlengthDiff.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetRunlengthDiff.txt");

    }

    @Test
    void compressComplementary() throws IOException {
        sizeInt("dataset/bigDataset_format.txt", "dataset/sizeBigDataset.txt");
        compressComplementary("dataset/bigDataset_format.txt", "dataset/compressBigDatasetComplementary.txt", "dataset/timeBigDatasetBigDatasetCompressComplementary.txt");
        sizeInt("dataset/compressBigDatasetComplementary.txt", "dataset/sizeBigDatasetComplementary.txt");
    }

    @Test
    void decompressComplementary() throws IOException {
        decompressComplementary("dataset/compressBigDatasetComplementary.txt", "dataset/decompressBigDatasetComplementary.txt", "dataset/timeBigDatasetBigDatasetDecompressComplementary.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetComplementary.txt");

    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/bigDataset_format.txt", "dataset/compressBigDatasetComplementary2.txt", "dataset/timeBigDatasetBigDatasetCompressComplementary2.txt");
        sizeInt("dataset/compressBigDatasetComplementary2.txt", "dataset/sizeBigDatasetComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/compressBigDatasetComplementary2.txt", "dataset/decompressBigDatasetComplementary2.txt", "dataset/timeBigDatasetBigDatasetDecompressComplementary2.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/bigDataset_format.txt", "dataset/compressBigDatasetStreamVByte.txt", "dataset/timeBigDatasetBigDatasetCompressStreamVByte.txt");
        sizeString("dataset/compressBigDatasetStreamVByte.txt", "dataset/sizeBigDatasetStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/compressBigDatasetStreamVByte.txt", "dataset/decompressBigDatasetStreamVByte.txt", "dataset/timeBigDatasetBigDatasetDecompressStreamVByte.txt");


        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/bigDataset_format.txt", "dataset/compressBigDatasetCompose.txt", "dataset/timeBigDatasetCompressCompose.txt");
        sizeString("dataset/compressBigDatasetCompose.txt", "dataset/sizeBigDatasetCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/compressBigDatasetCompose.txt", "dataset/decompressBigDatasetCompose.txt", "dataset/timeBigDatasetDecompressCompose.txt");

        verif("dataset/bigDataset_format.txt", "dataset/decompressBigDatasetCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}
