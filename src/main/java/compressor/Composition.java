package compressor;

import java.util.ArrayList;
import java.util.List;

public class Composition extends AbstractCompressor{
    Complementary complementary=new Complementary();
    StreamVByte streamVByte= new StreamVByte();


    public List<String> compress(List<Integer> toCompress) {
        List<Integer> resComp= complementary.compress2(toCompress);
        List<String> res=streamVByte.compress(resComp);
        return res;
    }

    public List<Integer> decompress(String toDecompress, String size) {
        List<Integer> resComp=streamVByte.decompress(toDecompress, size);

        List<Integer> res= complementary.decompress2(resComp);
        return res;
    }
}
