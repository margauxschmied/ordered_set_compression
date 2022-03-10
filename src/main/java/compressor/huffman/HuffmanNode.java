package compressor.huffman;

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    int data;
    HuffmanNode left, right;

    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
