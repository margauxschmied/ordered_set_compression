package compressor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCompressor implements Compressor {

    public List<Integer> defferencialDeltaSub(List<Integer> list){
        List<Integer> res=new ArrayList();
        res.add(list.get(0));
        for (int i=1; i<list.size(); i++){
            res.add(list.get(i)-list.get(i-1));
        }
        return res;
    }

    public List<Integer> defferencialDeltaAdd(List<Integer> list){
        List<Integer> res=new ArrayList();
        res.add(list.get(0));
        for (int i=1; i<list.size(); i++){
            res.add(list.get(i)+res.get(i-1));
        }
        return res;
    }


//    public abstract List<Object> compress(List<Object> toCompress);
//
//    public abstract List<Object> decompress(List<Object> toDecompress);
}
