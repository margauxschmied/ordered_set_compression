import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ArtificielTest extends DataTest{


    @Test
    void compressRunlength() throws IOException {
        compressRunlength("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetRunlength.txt");

        sizeInt("dataset/artificiel/compress/compressBigDatasetRunlength.txt", "dataset/artificiel/size/sizeBigDatasetRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/artificiel/compress/compressBigDatasetRunlength.txt", "dataset/artificiel/decompress/decompressBigDatasetRunlength.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetHuffman.txt", "dataset/artificiel/compress/treeBigDatasetHuffman.txt");

        sizeString("dataset/artificiel/compress/compressBigDatasetHuffman.txt", "dataset/artificiel/size/sizeBigDatasetHuffman.txt");
        sizeInt("dataset/artificiel/compress/treeBigDatasetHuffman.txt", "dataset/artificiel/size/sizeTreeBigDatasetHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/artificiel/compress/compressBigDatasetHuffman.txt", "dataset/artificiel/decompress/decompressBigDatasetHuffman.txt", "dataset/artificiel/compress/treeBigDatasetHuffman.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetDiff.txt");

        sizeInt("dataset/artificiel/compress/compressBigDatasetDiff.txt", "dataset/artificiel/size/sizeBigDatasetDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/artificiel/compress/compressBigDatasetDiff.txt", "dataset/artificiel/decompress/decompressBigDatasetDiff.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetRunlengthDiff.txt");

        sizeInt("dataset/artificiel/compress/compressBigDatasetRunlengthDiff.txt", "dataset/artificiel/size/sizeBigDatasetRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/artificiel/compress/compressBigDatasetRunlengthDiff.txt", "dataset/artificiel/decompress/decompressBigDatasetRunlengthDiff.txt");


        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetRunlengthDiff.txt");

    }

//    @Test
//    void compressComplementary() throws IOException {
//        sizeInt("dataset/dataset_artificiel.txt", "dataset/artificiel/size/sizeBigDataset.txt");
//        compressComplementary("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetComplementary.txt");
//        sizeInt("dataset/artificiel/compress/compressBigDatasetComplementary.txt", "dataset/artificiel/size/sizeBigDatasetComplementary.txt");
//    }
//
//    @Test
//    void decompressComplementary() throws IOException {
//        decompressComplementary("dataset/artificiel/compress/compressBigDatasetComplementary.txt", "dataset/artificiel/decompress/decompressBigDatasetComplementary.txt");
//
//
//        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetComplementary.txt");
//
//    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetComplementary2.txt");
        sizeInt("dataset/artificiel/compress/compressBigDatasetComplementary2.txt", "dataset/artificiel/size/sizeBigDatasetComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/artificiel/compress/compressBigDatasetComplementary2.txt", "dataset/artificiel/decompress/decompressBigDatasetComplementary2.txt");


        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetStreamVByte.txt");
        sizeString("dataset/artificiel/compress/compressBigDatasetStreamVByte.txt", "dataset/artificiel/size/sizeBigDatasetStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/artificiel/compress/compressBigDatasetStreamVByte.txt", "dataset/artificiel/decompress/decompressBigDatasetStreamVByte.txt");


        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/dataset_artificiel.txt", "dataset/artificiel/compress/compressBigDatasetCompose.txt");
        sizeString("dataset/artificiel/compress/compressBigDatasetCompose.txt", "dataset/artificiel/size/sizeBigDatasetCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/artificiel/compress/compressBigDatasetCompose.txt", "dataset/artificiel/decompress/decompressBigDatasetCompose.txt");

        verif("dataset/dataset_artificiel.txt", "dataset/artificiel/decompress/decompressBigDatasetCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}
