package compressor;

import java.util.List;

public interface Compressor {

    List<Integer> compresse(List<Integer> toCompress);

    List<Integer> decompresse(List<Integer> toDecompress);

}
