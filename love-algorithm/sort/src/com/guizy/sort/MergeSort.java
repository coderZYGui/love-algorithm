package com.guizy.sort;

/**
 * Description: 归并排序
 *
 * @author guizy
 * @date 2021/1/3 15:15
 */
@SuppressWarnings("all")
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        divide(0, array.length);
    }

    /**
     * 对[begin, end)范围的数据进行归并排序; 左闭右开
     *
     * @param begin
     * @param end
     */
    private void divide(int begin, int end) {
        // 表示只有一个元素了, 就不需要归并排序
        if (end - begin < 2) return;

        int mid = (begin + end) >> 1;
        // 归并排序左半子序列
        divide(begin, mid);
        // 归并排序右半子序列
        divide(mid, end);
        // 合并整个序列
        merge(begin, mid, end);
    }

    /**
     * 将[begin, mid)和[mid, end)范围的序列合并成一个有序序列
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;   // 左边数组(基于leftArray)
        int ri = mid, re = end; // 右边数组(基于array)
        int ai = begin; // array的索引

        // 拷贝左边数组到leftArray
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 如果左边还没有结束
        while (li < le) {  // li == le 左边结束, 则直接结束归并
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) { // cmp <= 会造成不稳定
                // 拷贝右边数组到array
                array[ai++] = array[ri++];
            } else {
                // 拷贝左边数组到array
                array[ai++] = leftArray[li++];
            }
        }
    }
}
