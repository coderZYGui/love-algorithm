package com.guizy.sort;

/**
 * Description: 插入排序-优化
 *
 * @author guizy
 * @date 2021/1/3 09:46
 */
public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        // 从1开始, 将0位置的元素当作已经有序的元素
        for (int begin = 1; begin < array.length; begin++) {
            int curIndex = begin;
            // 记录一份当前要移动的元素
            E v = array[curIndex];
            // 当前要移动的元素 < 它前面的元素
            while (curIndex > 0 && cmp(v, array[curIndex - 1]) < 0) {
                // 将它前面的元素挪动到当前元素的位置
                array[curIndex] = array[curIndex - 1];
                curIndex--; // 每一次循环, curIndex都会改变
            }
            array[curIndex] = v;
        }
    }
}
