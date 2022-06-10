package compressor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreamVByte extends AbstractCompressor{

    public List<String> compress(List<Integer> toCompress) {
        StringBuilder res = new StringBuilder();
        StringBuilder size = new StringBuilder();
        int s;


        for (Integer compress : toCompress) {
            s = Integer.toBinaryString(compress).length();
            size.append(converteSize((int) Math.ceil((double) s / 8)));

            String tmp = Integer.toBinaryString(compress);

            StringBuilder s2 = new StringBuilder();
            if (tmp.length() % 8 != 0) {
                for (int j = 0; j < 8 - tmp.length() % 8; j++) {
                    s2.append("0");
                }
            }
            s2.append(tmp);
            res.append(s2);
        }

        return new ArrayList<>(){{add(String.valueOf(res)); add(String.valueOf(size));}};
    }


    public String converteSize(int i){
        switch (i){
            case 1: return "00";
            case 2: return "01";
            case 3: return "10";
            case 4: return "11";
        }
        return null;
    }

    public List<Integer> decompress(String toDecompress, String size) {
        List<Integer> res = new ArrayList<>();

        int cursor=0;

        for (int i = 0; i < size.length(); i+=2) {
            int s= Integer.parseInt(String.valueOf(size.charAt(i)) + size.charAt(i + 1), 2)+1;
            res.add((int) Long.parseLong(toDecompress.substring(cursor, cursor+(s*8)), 2));
            cursor+=(s*8);
        }

        return res;
    }

}
