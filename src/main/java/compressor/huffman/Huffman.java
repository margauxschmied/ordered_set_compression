package compressor.huffman;

import compressor.AbstractCompressor;

import java.util.*;

public class Huffman extends AbstractCompressor {

    private Map<Integer, String> charPrefixHashMap = new HashMap<>();
    static HuffmanNode root;

    private HuffmanNode buildTree(Map<Integer, Integer> freq) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new HuffmanComparator());
        Set<Integer> keySet = freq.keySet();
        for (Integer i : keySet) {

            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = i;
            huffmanNode.frequency = freq.get(i);
            huffmanNode.left = null;
            huffmanNode.right = null;
            priorityQueue.offer(huffmanNode);
        }


        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.poll();


            HuffmanNode y = priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = -1;

            sum.left = x;

            sum.right = y;
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }


    private void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                charPrefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    public List<Object> compress(List<Integer> toCompress) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < toCompress.size(); i++) {
            if (!freq.containsKey(toCompress.get(i))) {
                freq.put(toCompress.get(i), 0);
            }
            freq.put(toCompress.get(i), freq.get(toCompress.get(i)) + 1);
        }

        root = buildTree(freq);

        setPrefixCodes(root, new StringBuilder());
        List<Object> res = new ArrayList<>();

        for (int i = 0; i < toCompress.size(); i++) {
            int e = toCompress.get(i);
            res.add(charPrefixHashMap.get(e));
        }

        return res;
    }

    public List<Integer> decompress(List<Object> toDecompress) {

        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> res= new ArrayList<>();
        HuffmanNode temp = root;

        for (int i = 0; i < toDecompress.size(); i++) {
            String val = String.valueOf(toDecompress.get(i));
            for (int k=0; k< val.length(); k++) {
                int j = Integer.parseInt(String.valueOf(val.charAt(k)));

                if (j == 0) {
                    temp = temp.left;
                    if (temp.left == null && temp.right == null) {
                        stringBuilder.append(temp.data);
                        temp = root;
                    }
                }
                if (j == 1) {
                    temp = temp.right;
                    if (temp.left == null && temp.right == null) {
                        stringBuilder.append(temp.data);
                        temp = root;
                    }
                }
            }
            res.add(Integer.valueOf(stringBuilder.toString()));
            stringBuilder = new StringBuilder();
        }

        return res;
    }
}
