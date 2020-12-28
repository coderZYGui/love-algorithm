package com.guizy.sort;

/**
 * Description: 选择排序
 *
 * @author guizy1
 * @date 2020/12/28 18:28
 */
public class SelectionSort extends Sort {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {  // 一共比较多少趟
            int maxIndex = 0; // 假如索引0位置的元素是最大的
            for (int begin = 1; begin <= end; begin++) {
                // if (array[maxIndex] <= array[begin]) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
