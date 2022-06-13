import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ArtificielPseudoCroissantTest extends DataTest{


    @Test
    void compressRunlength() throws IOException {
        sizeInt("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/sizeArtificiel.txt");

        compressRunlength("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielRunlength.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressRunlength.txt");

        sizeInt("dataset/artificielPseudoCroissant/compressArtificielRunlength.txt", "dataset/artificielPseudoCroissant/sizeArtificielRunlength.txt");
    }

    @Test
    void decompressRunlength() throws IOException {
        decompressRunlength("dataset/artificielPseudoCroissant/compressArtificielRunlength.txt", "dataset/artificielPseudoCroissant/decompressArtificielRunlength.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressRunlength.txt");

        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielRunlength.txt");
    }

    @Test
    void compressHuffman() throws IOException {
        compressHuffman("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielHuffman.txt", "dataset/artificielPseudoCroissant/treeArtificielHuffman.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressHuffman.txt");

        sizeString("dataset/artificielPseudoCroissant/compressArtificielHuffman.txt", "dataset/artificielPseudoCroissant/sizeArtificielHuffman.txt");
        sizeInt("dataset/artificielPseudoCroissant/treeArtificielHuffman.txt", "dataset/artificielPseudoCroissant/sizeTreeArtificielHuffman.txt");
    }

    @Test
    void decompressHuffman() throws IOException {
        decompressHuffman("dataset/artificielPseudoCroissant/compressArtificielHuffman.txt", "dataset/artificielPseudoCroissant/decompressArtificielHuffman.txt", "dataset/artificielPseudoCroissant/treeArtificielHuffman.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressHuffman.txt");

        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielHuffman.txt");
    }

    @Test
    void compressDiff() throws IOException {
        compressDiff("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielDiff.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressDiff.txt");

        sizeInt("dataset/artificielPseudoCroissant/compressArtificielDiff.txt", "dataset/artificielPseudoCroissant/sizeArtificielDiff.txt");
    }

    @Test
    void decompressDiff() throws IOException {
        decompressDiff("dataset/artificielPseudoCroissant/compressArtificielDiff.txt", "dataset/artificielPseudoCroissant/decompressArtificielDiff.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressDiff.txt");

        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielDiff.txt");

    }
    @Test
    void compressRunlengthDiff() throws IOException {
        compressRunlengthDiff("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielRunlengthDiff.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressRunlengthDiff.txt");

        sizeInt("dataset/artificielPseudoCroissant/compressArtificielRunlengthDiff.txt", "dataset/artificielPseudoCroissant/sizeArtificielRunlengthDiff.txt");
    }

    @Test
    void decompressRunlengthDiff() throws IOException {
        decompressRunlengthDiff("dataset/artificielPseudoCroissant/compressArtificielRunlengthDiff.txt", "dataset/artificielPseudoCroissant/decompressArtificielRunlengthDiff.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressRunlengthDiff.txt");


        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielRunlengthDiff.txt");

    }

//    @Test
//    void compressComplementary() throws IOException {
//        sizeInt("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/sizeArtificiel.txt");
//        compressComplementary("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielComplementary.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressComplementary.txt");
//        sizeInt("dataset/artificielPseudoCroissant/compressArtificielComplementary.txt", "dataset/artificielPseudoCroissant/sizeArtificielComplementary.txt");
//    }
//
//    @Test
//    void decompressComplementary() throws IOException {
//        decompressComplementary("dataset/artificielPseudoCroissant/compressArtificielComplementary.txt", "dataset/artificielPseudoCroissant/decompressArtificielComplementary.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressComplementary.txt");
//
//
//        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielComplementary.txt");
//
//    }

    @Test
    void compressComplementary2() throws IOException {
        compressComplementary2("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielComplementary2.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressComplementary2.txt");
        sizeInt("dataset/artificielPseudoCroissant/compressArtificielComplementary2.txt", "dataset/artificielPseudoCroissant/sizeArtificielComplementary2.txt");
    }

    @Test
    void decompressComplementary2() throws IOException {
        decompressComplementary2("dataset/artificielPseudoCroissant/compressArtificielComplementary2.txt", "dataset/artificielPseudoCroissant/decompressArtificielComplementary2.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressComplementary2.txt");


        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielComplementary2.txt");

    }

    @Test
    void compressStreamVByte() throws IOException {
        compressStreamVByte("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielStreamVByte.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressStreamVByte.txt");
        sizeString("dataset/artificielPseudoCroissant/compressArtificielStreamVByte.txt", "dataset/artificielPseudoCroissant/sizeArtificielStreamVByte.txt");
    }

    @Test
    void decompressStreamVByte() throws IOException {
        decompressStreamVByte("dataset/artificielPseudoCroissant/compressArtificielStreamVByte.txt", "dataset/artificielPseudoCroissant/decompressArtificielStreamVByte.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressStreamVByte.txt");


        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielStreamVByte.txt");

    }

    @Test
    void compressCompose() throws IOException {
        compressCompose("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/compressArtificielCompose.txt", "dataset/artificielPseudoCroissant/timeArtificielCompressCompose.txt");
        sizeString("dataset/artificielPseudoCroissant/compressArtificielCompose.txt", "dataset/artificielPseudoCroissant/sizeArtificielCompose.txt");
    }

    @Test
    void decompressCompose() throws IOException {
        decompressCompose("dataset/artificielPseudoCroissant/compressArtificielCompose.txt", "dataset/artificielPseudoCroissant/decompressArtificielCompose.txt", "dataset/artificielPseudoCroissant/timeArtificielDecompressCompose.txt");

        verif("dataset/artificielPseudoCroissant/dataset_artificiel_pseudo_croissant.txt", "dataset/artificielPseudoCroissant/decompressArtificielCompose.txt");

    }

    @Test
    void truc(){
        System.out.println(Integer.toBinaryString(-1));
    }
}

