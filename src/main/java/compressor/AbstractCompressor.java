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

    public List<Object> complementary(List<Object> list){
        List<Object> res = new ArrayList();
        res.add(list.get(0));
        for(int i = (int) list.get(0); i<(Integer) list.get(list.size()-1); i++){
            if(!list.contains(i)){
                res.add(i);
            }
        }
        res.add(list.get(list.size()-1));
        return res;
    }
}
