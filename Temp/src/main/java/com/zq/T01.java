package com.zq;

public class T01 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("aaa");
        sb.append("aab");
        sb.delete(0, sb.length()-1);
        System.out.println(sb.toString());

    }
}
