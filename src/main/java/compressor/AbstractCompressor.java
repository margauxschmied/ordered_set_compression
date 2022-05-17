package compressor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCompressor implements Compressor {

    public List<Integer> differencialDeltaSub(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res.add(list.get(i) - list.get(i - 1));
        }
        return res;
    }

    public List<Integer> differencialDeltaAdd(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res.add(list.get(i) + res.get(i - 1));
        }
        return res;
    }

    public List<Integer> differencialDeltaSub0(List<Integer> list) {
        List<Integer> res = new ArrayList();

        List<Integer> tmp = new ArrayList();

//        res.add(list.get(0));

        for (int i=1; i<list.size(); i++) {
            tmp.add(list.get(i - 1));
            if (list.get(i - 1)!=0 && list.get(i) < list.get(i - 1)) {
                if(tmp.size()>2 ){
                    res.addAll(differencialDeltaSub00(tmp));
                    System.out.println(tmp);
                }
                else{
                    res.addAll(tmp);
                }
                tmp = new ArrayList<>();

            }
        }

        tmp.add(list.get(list.size() - 1));

        res.addAll(tmp);

        return res;
    }

    public List<Integer> differencialDeltaSub00(List<Integer> list) {
        List<Integer> res = new ArrayList();

        res.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            if(list.get(i)!=0) {

                res.add(list.get(i) - list.get(i - 1));
            }
            else{
                res.add(0);
            }
        }
        return res;
    }

    public List<Integer> differencialDeltaAdd0(List<Integer> list) {
        List<Integer> res = new ArrayList();

        List<Integer> tmp = new ArrayList();

//        res.add(list.get(0));

        for (int i=1; i<list.size(); i++) {
            tmp.add(list.get(i - 1));
            if (list.get(i - 1)!=0 && list.get(i) < list.get(i - 1)) {
                if(tmp.size()>=2 ){
                    res.addAll(differencialDeltaAdd00(tmp));
                }
                else{
                    res.addAll(tmp);
                }
                tmp = new ArrayList<>();

            }
        }

        tmp.add(list.get(list.size() - 1));

        res.addAll(tmp);

        return res;
    }

    public List<Integer> differencialDeltaAdd00(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(list.get(0));
        res.add(list.get(1));

        for (int i = 2; i < list.size(); i++) {
            if(list.get(i)!=0) {
                res.add(list.get(i) + res.get(i - 1));
            }
            else{
                res.add(0);
            }
        }
        return res;
    }

    public List<Integer> differencialDeltaSub4(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(list.get(0));
        res.add(list.get(1));
        res.add(list.get(2));
        res.add(list.get(3));

        for (int i = 4; i < list.size(); i += 4) {
            res.add(list.get(i) - list.get(i - 4));
            if (i + 1 < list.size()) {
                res.add(list.get(i + 1) - list.get(i - 3));
                if (i + 2 < list.size()) {
                    res.add(list.get(i + 2) - list.get(i - 2));
                    if (i + 3 < list.size()) {
                        res.add(list.get(i + 3) - list.get(i - 1));
                    }
                }
            }
        }
        return res;
    }

    public List<Integer> differencialDeltaAdd4(List<Integer> list) {
        List<Integer> res = new ArrayList();
        res.add(list.get(0));
        res.add(list.get(1));
        res.add(list.get(2));
        res.add(list.get(3));

        for (int i = 4; i < list.size(); i += 4) {
            res.add(list.get(i) + res.get(i - 4));
            if (i + 1 < list.size()) {
                res.add(list.get(i + 1) + list.get(i - 3));
                if (i + 2 < list.size()) {
                    res.add(list.get(i + 2) + list.get(i - 2));
                    if (i + 3 < list.size()) {
                        res.add(list.get(i + 3) + list.get(i - 1));
                    }
                }
            }
        }
        return res;
    }


}
