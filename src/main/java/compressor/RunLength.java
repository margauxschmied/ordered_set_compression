package compressor;

import java.util.ArrayList;
import java.util.List;

public class RunLength extends AbstractCompressor {

    @Override
    public List<Integer> compresse(List<Integer> toCompress) {
        List<Integer> diff=super.defferencialDeltaSub(toCompress);

        List<Integer> res=new ArrayList<>();
        int compt=0;
        int el= diff.get(0);

        for (int i=0; i<diff.size(); i++) {
            compt += 1;
            if(i==diff.size()-1){
                res.add(compt);
                res.add(el);
            }
            else if(diff.get(i+1)!=el){
                res.add(compt);
                res.add(el);
                compt = 0;
                el=diff.get(i+1);
            }

        }

        return res;
    }

    @Override
    public List<Integer> decompresse(List<Integer> toDecompress) {
        List<Integer> res=new ArrayList<>();

        for (int i=0; i<toDecompress.size(); i+=2) {
            for(int j=0; j<toDecompress.get(i); j++){
                res.add(toDecompress.get(i+1));
            }
        }
        List<Integer> diff=super.defferencialDeltaAdd(res);

        return diff;
    }
}
