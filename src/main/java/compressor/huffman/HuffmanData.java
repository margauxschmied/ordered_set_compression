package compressor.huffman;

import java.util.List;

public class HuffmanData {
    HuffmanNode root;
    List list;
    public HuffmanData(HuffmanNode root, List list) {
        this.root=root;
        this.list=list;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public List getList() {
        return list;
    }
}
