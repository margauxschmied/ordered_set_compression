package compressor.huffman;

import java.util.List;
import java.util.Map;

public class HuffmanData {
    Map code;
    List list;

    public HuffmanData(Map<Integer, String> code, List list) {
        this.code = code;
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public Map getCode() {
        return code;
    }
}
