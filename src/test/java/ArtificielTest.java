
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


public class ArtificielTest extends DataTest{


    @Test
    void compressRunlength() throws IOException {
        compressRunlength("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielRunlength.txt", "dataset/artificiel/timeArtificielCompressRunlength.txt");

        sizeInt("dataset/artificiel/compressArtificielRunlength.txt", "dataset/artificiel/sizeArtificielRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/artificiel/compressArtificielRunlength.txt", "dataset/artificiel/decompressArtificielRunlength.txt", "dataset/artificiel/timeArtificielDecompressRunlength.txt");

        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielHuffman.txt", "dataset/artificiel/treeArtificielHuffman.txt", "dataset/artificiel/timeArtificielCompressHuffman.txt");

        sizeString("dataset/artificiel/compressArtificielHuffman.txt", "dataset/artificiel/sizeArtificielHuffman.txt");
        sizeInt("dataset/artificiel/treeArtificielHuffman.txt", "dataset/artificiel/sizeTreeArtificielHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/artificiel/compressArtificielHuffman.txt", "dataset/artificiel/decompressArtificielHuffman.txt", "dataset/artificiel/treeArtificielHuffman.txt", "dataset/artificiel/timeArtificielDecompressHuffman.txt");

        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielDiff.txt", "dataset/artificiel/timeArtificielCompressDiff.txt");

        sizeInt("dataset/artificiel/compressArtificielDiff.txt", "dataset/artificiel/sizeArtificielDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/artificiel/compressArtificielDiff.txt", "dataset/artificiel/decompressArtificielDiff.txt", "dataset/artificiel/timeArtificielDecompressDiff.txt");

        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielRunlengthDiff.txt", "dataset/artificiel/timeArtificielCompressRunlengthDiff.txt");

        sizeInt("dataset/artificiel/compressArtificielRunlengthDiff.txt", "dataset/artificiel/sizeArtificielRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/artificiel/compressArtificielRunlengthDiff.txt", "dataset/artificiel/decompressArtificielRunlengthDiff.txt", "dataset/artificiel/timeArtificielDecompressRunlengthDiff.txt");


        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielRunlengthDiff.txt");

    }

//    @Test
//    void compressComplementary() throws IOException {
//        sizeInt("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/sizeArtificiel.txt");
//        compressComplementary("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielComplementary.txt", "dataset/artificiel/timeArtificielCompressComplementary.txt");
//        sizeInt("dataset/artificiel/compressArtificielComplementary.txt", "dataset/artificiel/sizeArtificielComplementary.txt");
//    }
//
//    @Test
//    void decompressComplementary() throws IOException {
//        decompressComplementary("dataset/artificiel/compressArtificielComplementary.txt", "dataset/artificiel/decompressArtificielComplementary.txt", "dataset/artificiel/timeArtificielDecompressComplementary.txt");
//
//
//        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielComplementary.txt");
//
//    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielComplementary2.txt", "dataset/artificiel/timeArtificielCompressComplementary2.txt");
        sizeInt("dataset/artificiel/compressArtificielComplementary2.txt", "dataset/artificiel/sizeArtificielComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/artificiel/compressArtificielComplementary2.txt", "dataset/artificiel/decompressArtificielComplementary2.txt", "dataset/artificiel/timeArtificielDecompressComplementary2.txt");


        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielStreamVByte.txt", "dataset/artificiel/timeArtificielCompressStreamVByte.txt");
        sizeString("dataset/artificiel/compressArtificielStreamVByte.txt", "dataset/artificiel/sizeArtificielStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/artificiel/compressArtificielStreamVByte.txt", "dataset/artificiel/decompressArtificielStreamVByte.txt", "dataset/artificiel/timeArtificielDecompressStreamVByte.txt");


        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/compressArtificielCompose.txt", "dataset/artificiel/timeArtificielCompressCompose.txt");
        sizeString("dataset/artificiel/compressArtificielCompose.txt", "dataset/artificiel/sizeArtificielCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/artificiel/compressArtificielCompose.txt", "dataset/artificiel/decompressArtificielCompose.txt", "dataset/artificiel/timeArtificielDecompressCompose.txt");

        verif("dataset/artificiel/dataset_artificiel.txt", "dataset/artificiel/decompressArtificielCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}

