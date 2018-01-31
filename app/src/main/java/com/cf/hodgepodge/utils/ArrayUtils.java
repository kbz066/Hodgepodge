package com.cf.hodgepodge.utils;

/**
 * Created by cf on 2016/11/18.
 */

public class ArrayUtils {

    /**
     * 数组复制
     * @param a
     * @param b
     * @return
     */
    public static byte[] copyArray(byte[] a, byte[] b) {
        byte[] c= new byte[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }


}
