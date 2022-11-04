package com.zq;

class Solution {
    public int maxProfit(int[] prices) {
        //收入
        int money = 0;
        //成本价
        int cost = prices[0];
        //出售价
        int sell = prices[0];
        for (int i = 1; i < prices.length; i++) {
            //当前价小于成本价，选择买入
            if (prices[i] < cost) {
                money += (sell - cost);
                cost = prices[i];
                sell = cost;
            }
            if (prices[i] > sell) {
                //当前价大于卖价
                sell = prices[i];
            } else {
                //当前价小于卖价，卖价为之前存的售价
                money += (sell - cost);
                cost = prices[i];
                sell = cost;
            }
        }
        return money + (sell - cost);
    }

    //g = [1,2,3], s = [1,1]
    public static void main(String[] args) {
        Solution solution = new Solution();
        Object o = solution.maxProfit(new int[]{2, 4, 1});
        System.out.println(o);
    }
}

