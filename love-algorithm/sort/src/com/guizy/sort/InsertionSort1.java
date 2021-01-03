package com.guizy.sort;

/**
 * Description: 插入排序
 *
 * @author guizy
 * @date 2021/1/3 09:46
 */
public class InsertionSort1<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 从1开始, 将0位置的元素当作已经有序的元素
        for (int begin = 1; begin < array.length; begin++) {
            // 记录当前要比较的元素索引
            int curIndex = begin;
            // curIndex > 0, 确保curIndex减到1即可,否则会越界
            while (curIndex > 0 && cmp(curIndex, curIndex - 1) < 0) {
                swap(curIndex, curIndex - 1);
                curIndex--;
            }
        }
    }
}
