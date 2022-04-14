package compressor.huffman;

import compressor.AbstractCompressor;

import java.util.*;

public class Huffman extends AbstractCompressor {

    private Map<Integer, String> charPrefixHashMap = new HashMap<>();

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

        HuffmanNode root;

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


    private void createCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                charPrefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                createCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                createCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    public HuffmanData compress(List<Integer> toCompress) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < toCompress.size(); i++) {
            if (!freq.containsKey(toCompress.get(i))) {
                freq.put(toCompress.get(i), 0);
            }
            freq.put(toCompress.get(i), freq.get(toCompress.get(i)) + 1);
        }

        HuffmanNode root = buildTree(freq);

        createCodes(root, new StringBuilder());
        List<Object> res = new ArrayList<>();

        for (int i = 0; i < toCompress.size(); i++) {
            int e = toCompress.get(i);
            res.add(charPrefixHashMap.get(e));
        }

//        System.out.println(charPrefixHashMap);
        return new HuffmanData(charPrefixHashMap, res);
    }
    //{0=9, 1=10, 2=9, 3=11, 4=10, 5=9, 6=11, 7=9, 8=9, 9=13, 10=7, 11=6, 12=12, 13=8, 14=8, 15=15, 16=8, 17=8, 18=8, 19=8, 20=4, 21=9, 22=10, 23=11, 24=13, 25=11, 26=16, 27=7, 28=13, 29=13, 30=11, 31=14, 32=9, 33=10, 34=11, 35=9, 36=8, 37=10, 38=10, 39=7, 40=10, 41=11, 42=14, 43=11, 44=14, 45=10, 46=5, 47=9, 48=13, 49=11, 50=3, 51=14, 52=15, 53=7, 54=9, 55=11, 56=17, 57=11, 58=4, 59=6, 60=7, 61=6, 62=8, 63=15, 64=9, 65=7, 66=11, 67=15, 68=10, 69=7, 70=5, 71=6, 72=15, 73=8, 74=5, 75=9, 76=10, 77=12, 78=16, 79=10, 80=10, 81=12, 82=11, 83=11, 84=7, 85=6, 86=10, 87=9, 88=13, 89=8, 90=8, 91=11, 92=8, 93=12, 94=9, 95=9, 96=15, 97=9, 98=10, 99=19, 100=3}

    public List<Object> decompress(HuffmanData huffmanData) {
//        StringBuilder stringBuilder = new StringBuilder();
        List<Object> toDecompress = huffmanData.getList();
        List<Object> res = new ArrayList<>();
        Map<Integer, String> code = huffmanData.getCode();

        for (int i = 0; i < toDecompress.size(); i++) {
            String val = String.valueOf(toDecompress.get(i));
//            for (int k = 0; k < val.length(); k++) {
//                int j = Integer.parseInt(String.valueOf(val.charAt(k)));
//
//                if (j == 0) {
//                    temp = temp.left;
//                    if (temp.left == null && temp.right == null) {
//                        stringBuilder.append(temp.data);
//                        temp = huffmanData.getRoot();
//                    }
//                }
//                if (j == 1) {
//                    temp = temp.right;
//                    if (temp.left == null && temp.right == null) {
//                        stringBuilder.append(temp.data);
//                        temp = huffmanData.getRoot();
//                    }
//                }
//            }

            for (Map.Entry<Integer,String> entry : code.entrySet())
            {
                if(entry.getValue().equals(val)){
                    res.add(entry.getKey());
                }
            }

//            stringBuilder = new StringBuilder();
        }

        return res;
    }
}
