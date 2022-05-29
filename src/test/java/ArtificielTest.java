
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
        compressRunlength("dataset/dataset_artificiel.txt", "dataset/compressArtificielRunlength.txt", "dataset/timeArtificielArtificielCompressRunlength.txt");

        sizeInt("dataset/compressArtificielRunlength.txt", "dataset/sizeArtificielRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/compressArtificielRunlength.txt", "dataset/decompressArtificielRunlength.txt", "dataset/timeArtificielArtificielDecompressRunlength.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/dataset_artificiel.txt", "dataset/compressArtificielHuffman.txt", "dataset/treeArtificielHuffman.txt", "dataset/timeArtificielArtificielCompressHuffman.txt");

        sizeString("dataset/compressArtificielHuffman.txt", "dataset/sizeArtificielHuffman.txt");
        sizeInt("dataset/treeArtificielHuffman.txt", "dataset/sizeTreeArtificielHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/compressArtificielHuffman.txt", "dataset/decompressArtificielHuffman.txt", "dataset/treeArtificielHuffman.txt", "dataset/timeArtificielArtificielDecompressHuffman.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/dataset_artificiel.txt", "dataset/compressArtificielDiff.txt", "dataset/timeArtificielArtificielCompressDiff.txt");

        sizeInt("dataset/compressArtificielDiff.txt", "dataset/sizeArtificielDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/compressArtificielDiff.txt", "dataset/decompressArtificielDiff.txt", "dataset/timeArtificielArtificielDecompressDiff.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/dataset_artificiel.txt", "dataset/compressArtificielRunlengthDiff.txt", "dataset/timeArtificielArtificielCompressRunlengthDiff.txt");

        sizeInt("dataset/compressArtificielRunlengthDiff.txt", "dataset/sizeArtificielRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/compressArtificielRunlengthDiff.txt", "dataset/decompressArtificielRunlengthDiff.txt", "dataset/timeArtificielArtificielDecompressRunlengthDiff.txt");


        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielRunlengthDiff.txt");

    }

//    @Test
//    void compressComplementary() throws IOException {
//        sizeInt("dataset/dataset_artificiel.txt", "dataset/sizeArtificiel.txt");
//        compressComplementary("dataset/dataset_artificiel.txt", "dataset/compressArtificielComplementary.txt", "dataset/timeArtificielArtificielCompressComplementary.txt");
//        sizeInt("dataset/compressArtificielComplementary.txt", "dataset/sizeArtificielComplementary.txt");
//    }
//
//    @Test
//    void decompressComplementary() throws IOException {
//        decompressComplementary("dataset/compressArtificielComplementary.txt", "dataset/decompressArtificielComplementary.txt", "dataset/timeArtificielArtificielDecompressComplementary.txt");
//
//
//        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielComplementary.txt");
//
//    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/dataset_artificiel.txt", "dataset/compressArtificielComplementary2.txt", "dataset/timeArtificielArtificielCompressComplementary2.txt");
        sizeInt("dataset/compressArtificielComplementary2.txt", "dataset/sizeArtificielComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/compressArtificielComplementary2.txt", "dataset/decompressArtificielComplementary2.txt", "dataset/timeArtificielArtificielDecompressComplementary2.txt");


        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/dataset_artificiel.txt", "dataset/compressArtificielStreamVByte.txt", "dataset/timeArtificielArtificielCompressStreamVByte.txt");
        sizeString("dataset/compressArtificielStreamVByte.txt", "dataset/sizeArtificielStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/compressArtificielStreamVByte.txt", "dataset/decompressArtificielStreamVByte.txt", "dataset/timeArtificielArtificielDecompressStreamVByte.txt");


        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/dataset_artificiel.txt", "dataset/compressArtificielCompose.txt", "dataset/timeArtificielCompressCompose.txt");
        sizeString("dataset/compressArtificielCompose.txt", "dataset/sizeArtificielCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/compressArtificielCompose.txt", "dataset/decompressArtificielCompose.txt", "dataset/timeArtificielDecompressCompose.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/decompressArtificielCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}

