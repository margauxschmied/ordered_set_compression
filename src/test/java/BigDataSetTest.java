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
        compressRunlength("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetRunlength.txt", "dataset/bigDataSet/timeBigDatasetCompressRunlength.txt");

        sizeInt("dataset/bigDataSet/compressBigDatasetRunlength.txt", "dataset/bigDataSet/sizeBigDatasetRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/bigDataSet/compressBigDatasetRunlength.txt", "dataset/bigDataSet/decompressBigDatasetRunlength.txt", "dataset/bigDataSet/timeBigDatasetDecompressRunlength.txt");

        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetHuffman.txt", "dataset/bigDataSet/treeBigDatasetHuffman.txt", "dataset/bigDataSet/timeBigDatasetCompressHuffman.txt");

        sizeString("dataset/bigDataSet/compressBigDatasetHuffman.txt", "dataset/bigDataSet/sizeBigDatasetHuffman.txt");
        sizeInt("dataset/bigDataSet/treeBigDatasetHuffman.txt", "dataset/bigDataSet/sizeTreeBigDatasetHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/bigDataSet/compressBigDatasetHuffman.txt", "dataset/bigDataSet/decompressBigDatasetHuffman.txt", "dataset/bigDataSet/treeBigDatasetHuffman.txt", "dataset/bigDataSet/timeBigDatasetDecompressHuffman.txt");

        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetDiff.txt", "dataset/bigDataSet/timeBigDatasetCompressDiff.txt");

        sizeInt("dataset/bigDataSet/compressBigDatasetDiff.txt", "dataset/bigDataSet/sizeBigDatasetDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/bigDataSet/compressBigDatasetDiff.txt", "dataset/bigDataSet/decompressBigDatasetDiff.txt", "dataset/bigDataSet/timeBigDatasetDecompressDiff.txt");

        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetRunlengthDiff.txt", "dataset/bigDataSet/timeBigDatasetCompressRunlengthDiff.txt");

        sizeInt("dataset/bigDataSet/compressBigDatasetRunlengthDiff.txt", "dataset/bigDataSet/sizeBigDatasetRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/bigDataSet/compressBigDatasetRunlengthDiff.txt", "dataset/bigDataSet/decompressBigDatasetRunlengthDiff.txt", "dataset/bigDataSet/timeBigDatasetDecompressRunlengthDiff.txt");


        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetRunlengthDiff.txt");

    }

    @Test
    void compressComplementary() throws IOException {
        sizeInt("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/sizeBigDataset.txt");
        compressComplementary("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetComplementary.txt", "dataset/bigDataSet/timeBigDatasetCompressComplementary.txt");
        sizeInt("dataset/bigDataSet/compressBigDatasetComplementary.txt", "dataset/bigDataSet/sizeBigDatasetComplementary.txt");
    }

    @Test
    void decompressComplementary() throws IOException {
        decompressComplementary("dataset/bigDataSet/compressBigDatasetComplementary.txt", "dataset/bigDataSet/decompressBigDatasetComplementary.txt", "dataset/bigDataSet/timeBigDatasetDecompressComplementary.txt");


        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetComplementary.txt");

    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetComplementary2.txt", "dataset/bigDataSet/timeBigDatasetCompressComplementary2.txt");
        sizeInt("dataset/bigDataSet/compressBigDatasetComplementary2.txt", "dataset/bigDataSet/sizeBigDatasetComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/bigDataSet/compressBigDatasetComplementary2.txt", "dataset/bigDataSet/decompressBigDatasetComplementary2.txt", "dataset/bigDataSet/timeBigDatasetDecompressComplementary2.txt");


        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetStreamVByte.txt", "dataset/bigDataSet/timeBigDatasetCompressStreamVByte.txt");
        sizeString("dataset/bigDataSet/compressBigDatasetStreamVByte.txt", "dataset/bigDataSet/sizeBigDatasetStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/bigDataSet/compressBigDatasetStreamVByte.txt", "dataset/bigDataSet/decompressBigDatasetStreamVByte.txt", "dataset/bigDataSet/timeBigDatasetDecompressStreamVByte.txt");


        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/compressBigDatasetCompose.txt", "dataset/bigDataSet/timeBigDatasetCompressCompose.txt");
        sizeString("dataset/bigDataSet/compressBigDatasetCompose.txt", "dataset/bigDataSet/sizeBigDatasetCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/bigDataSet/compressBigDatasetCompose.txt", "dataset/bigDataSet/decompressBigDatasetCompose.txt", "dataset/bigDataSet/timeBigDatasetDecompressCompose.txt");

        verif("dataset/bigDataSet/bigDataset_format.txt", "dataset/bigDataSet/decompressBigDatasetCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}
