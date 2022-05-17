package compressor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Complementary extends AbstractCompressor{


    public List<Integer> complementary(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(list.get(0));

        for (int i = list.get(0); i < list.get(list.size() - 1); i++) {
            if (!list.contains(i)) {
                res.add(i);
            }
        }
        res.add(list.get(list.size() - 1));
        return res;
    }

    public static <T> List<T> copyOfRange(List<T> list, int from, int to) {
        if (from < 0 || from >= list.size() || to < 0 || to >= list.size() || from > to)
            throw new IllegalArgumentException("Illegal extraction bounds");
        List<T> result = new ArrayList<>(to - from + 1);
        for (int i = from; i <= to; i++)
            result.add(list.get(i));
        return result;
    }

    public List<Integer> decomplementaryByRange(List<Integer> list) {
        return complementaryByRange(copyOfRange(list, 1, list.size()-1), -(list.get(0)), false);
    }

    public List<Integer> complementaryByRange(List<Integer> list) {
        return complementaryByRange(list, Collections.min(list)-1, true);
    }

    public List<Integer> complementaryByRange(List<Integer> list, int min, boolean compress) {
        List<List<Integer>> range=new ArrayList<>();
        List<Integer> tmp = new ArrayList();
//        System.out.println(Collections.min(list));


        for (int i=1; i<list.size(); i++) {
            tmp.add(list.get(i - 1)-min);
            if (list.get(i) < list.get(i - 1)) {
                range.add(tmp);
                tmp = new ArrayList<>();
            }
        }

        tmp.add(list.get(list.size() - 1)-min);

        if(!tmp.isEmpty()) {
            range.add(tmp);
        }

//        System.out.println(range);
        List<Integer> res = new ArrayList();
        if(compress) {
            res.add(min);
        }
        for (List<Integer> l: range) {
            if(l.size()>2 ){//&& l.get(l.size() - 1)-l.get(0)<l.size()*2) {
                res.addAll(complementary(l));
            }
            else{
                res.addAll(l);}
        }
        return res;
    }

    public List complementaryByRange2(List listData) {
        return complementaryByRange2(listData, (int) Collections.min(listData)-1);
    }

    public List<Integer> compressComplementary2(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(0);
        res.add(list.get(0));
        res.add(list.get(list.size() - 1)-list.get(0));
        return res;
    }

    public List<Integer> complementaryByRange2(List<Integer> list, int min) {
        List<Integer> tmp = new ArrayList();

        List<Integer> res = new ArrayList();

        res.add(min);


        for (int i=1; i<list.size(); i++) {
            tmp.add(list.get(i - 1)-min);
            if (list.get(i) != list.get(i - 1)+1) {
                if(tmp.size()>2 ){
                    res.addAll(compressComplementary2(tmp));
                }
                else{
                    res.addAll(tmp);
                }
                tmp = new ArrayList<>();

            }
        }

        tmp.add(list.get(list.size() - 1)-min);

        res.addAll(tmp);


        return res;
    }

    public List<Integer> decomplementaryByRange2(List<Integer> list) {
        return decomplementaryByRange2(copyOfRange(list, 1, list.size()-1), -(list.get(0)));
    }

    public List<Integer> decompressComplementary2(List<Integer> list) {
        List<Integer> res = new ArrayList();
        for (int i = list.get(0); i < (list.get(list.size() - 1)+1); i++) {
            res.add(i);
        }
        return res;
    }
    public List<Integer> decomplementaryByRange2(List<Integer> listData, int min) {
        List<Integer> res = new ArrayList();


        for (int i=0; i<listData.size(); i++) {
            if (listData.get(i) ==0) {
                int finalI1 = i;
                res.addAll(decompressComplementary2(new ArrayList<Integer>() {{
                    add(listData.get(finalI1 +1)-min);
                    add(listData.get(finalI1 +2)+listData.get(finalI1 +1)-min);
                }}));
                i+=2;
            }
            else {
                res.add(listData.get(i) - min);
            }

        }
        return res;
    }


}
