package compressor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCompressor implements Compressor {

    public List<Object> differencialDeltaSub(List<Object> list) {
        List<Object> res = new ArrayList();
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res.add((Integer) list.get(i) - (Integer) list.get(i - 1));
        }
        return res;
    }

    public List<Object> differencialDeltaAdd(List<Object> list) {
        List<Object> res = new ArrayList();
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res.add((Integer) list.get(i) + (Integer) res.get(i - 1));
        }
        return res;
    }

    public List<Object> differencialDeltaSub4(List<Object> list) {
        List<Object> res = new ArrayList();
        res.add(list.get(0));
        res.add(list.get(1));
        res.add(list.get(2));
        res.add(list.get(3));

        for (int i = 4; i < list.size(); i += 4) {
            res.add((Integer) list.get(i) - (Integer) list.get(i - 4));
            if (i + 1 < list.size()) {
                res.add((Integer) list.get(i + 1) - (Integer) list.get(i - 3));
                if (i + 2 < list.size()) {
                    res.add((Integer) list.get(i + 2) - (Integer) list.get(i - 2));
                    if (i + 3 < list.size()) {
                        res.add((Integer) list.get(i + 3) - (Integer) list.get(i - 1));
                    }
                }
            }
        }
        return res;
    }

    public List<Object> differencialDeltaAdd4(List<Object> list) {
        List<Object> res = new ArrayList();
        res.add(list.get(0));
        res.add(list.get(1));
        res.add(list.get(2));
        res.add(list.get(3));

        for (int i = 4; i < list.size(); i += 4) {
            res.add((Integer) list.get(i) + (Integer) res.get(i - 4));
            if (i + 1 < list.size()) {
                res.add((Integer) list.get(i + 1) + (Integer) list.get(i - 3));
                if (i + 2 < list.size()) {
                    res.add((Integer) list.get(i + 2) + (Integer) list.get(i - 2));
                    if (i + 3 < list.size()) {
                        res.add((Integer) list.get(i + 3) + (Integer) list.get(i - 1));
                    }
                }
            }
        }
        return res;
    }

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

    public List<Integer> complementaryByRange(List<Integer> list) {
        List<List<Integer>> range=new ArrayList<>();
        List<Integer> tmp = new ArrayList();

//        tmp.add(list.get(0));
        for (int i=1; i<list.size(); i++){
            tmp.add(list.get(i-1));
            if(list.get(i)< list.get(i-1)){
                range.add(tmp);
                tmp=new ArrayList<>();
            }
//            else{
//                tmp.add(list.get(i-1));
//            }
        }

//        for (int i=1; i<list.size()-1; i++){
////            tmp.add(list.get(i));
//            if(list.get(i)+1< list.get(i+1)){
//                tmp.add(list.get(i));
//            }
//            else{
//                range.add(tmp);
//                tmp=new ArrayList<>();
//            }
//        }
        tmp.add(list.get(list.size() - 1));

        if(!tmp.isEmpty()) {
            range.add(tmp);
        }


        List<Integer> res = new ArrayList();
        for (List<Integer> l: range) {
            if(l.size()>1 && l.get(l.size() - 1)-l.get(0)<l.size()*2) {
//                res.add(l.get(0));
//                for (int i = 0; i < l.size() - 1; i++) {
//                    int nb = l.get(i);
//                    while (nb < l.get(i + 1)-1) { //TODO: garder les repetition
//                        res.add(nb);
//                        nb += 1;
//                    }
//                }
//                res.add(l.get(l.size() - 1));
//                for (int i = l.get(0); i < l.get(l.size() - 1); i++) {
//                    if (!list.contains(i)) {
//                        res.add(i);
//                    }
//                }
//                res.add(l.get(l.size() - 1));
                res.addAll(complementary(l));
            }
            else{
                res.addAll(l);}
//            for (int i = l.get(0); i < l.get(l.size() - 1); i++) {
//                if (!list.contains(i)) {
//                    res.add(i);
//                }
//            }
//            res.add(l.get(l.size() - 1));
//            res.addAll(complementary(l));
        }
        return res;
    }
}
