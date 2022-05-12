package compressor;

import compressor.time.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class RunLength extends AbstractCompressor {

    public List<Integer> compress(List<Integer> toCompress) {
        List<Integer> res = new ArrayList<>();
        int compt = 1;
        int el = toCompress.get(0);

        for (int i = 0; i < toCompress.size(); i++) {
            if (i >= toCompress.size() - 1) {
                res.add(compt);
                res.add(el);
            } else if (toCompress.get(i + 1) != el) {
                res.add(compt);
                res.add(el);
                compt = 1;
                el = toCompress.get(i + 1);
            } else {
                compt += 1;
            }
        }

        return res;
    }

    public List<Integer> decompress(List<Integer> toDecompress) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < toDecompress.size(); i += 2) {
            for (int j = 0; j < toDecompress.get(i); j++) {
                res.add(toDecompress.get(i + 1));
            }
        }

        return res;
    }
}
