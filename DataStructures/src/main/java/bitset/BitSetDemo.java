package bitset;

import java.util.BitSet;

/**
 * bitset测试
 * @author zqian
 * @date 2021/1/14
 */
public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) {
                bits1.set(i);
            }
            if ((i % 5) != 0) {
                bits2.set(i);
            }
        }
        System.out.println("bitset1初始:"+bits1);
        System.out.println("bitset2初始:"+bits2);

        bits1.andNot(bits2);
        System.out.println(bits1);
        System.out.println(bits2);

    }
}
