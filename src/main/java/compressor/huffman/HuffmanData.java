package compressor.huffman;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HuffmanData {
    Map<Object, Object> code;
    List<String> list;

    public HuffmanData(Map<Object, Object> code, List<String> list) {
        this.code = code;
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public Map getCode() {
        return code;
    }

    public Map<Object, Object> getReverseCode(){
        Map<Object, Object> swapped = code.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        return swapped;
    }

}
