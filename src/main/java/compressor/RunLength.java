package compressor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunLength extends AbstractCompressor {

    public List<Object> compress(List<Object> toCompress) {
        List<Object> res=new ArrayList<>();
        int compt=0;
        int el= (int) toCompress.get(0);

        for (int i=0; i<toCompress.size(); i++) {
            compt += 1;
            if(i==toCompress.size()-1){
                res.add(compt);
                res.add(el);
            }
            else if((int) toCompress.get(i)==el){
                res.add(compt);
                res.add(el);
                compt = 0;
                el= (int) toCompress.get(i+1);
            }
        }

        return res;
    }

    public List<Object> decompress(List<Object> toDecompress) {
        List<Object> res=new ArrayList<>();

        for (int i=0; i<toDecompress.size(); i+=2) {
            for(int j=0; j<(Integer) toDecompress.get(i); j++){
                res.add(toDecompress.get(i+1));
            }
        }
        //List<Integer> diff=super.defferencialDeltaAdd(res);

        return res;
    }
}
