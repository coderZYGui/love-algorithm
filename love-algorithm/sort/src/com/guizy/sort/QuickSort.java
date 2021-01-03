package com.guizy.sort;

/**
 * Description: 实现快速排序
 *
 * @author guizy
 * @date 2021/1/3 20:04
 */
@SuppressWarnings("all")
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin, end)范围的元素进行快速排序
     * end - begin 就是元素个数, 因为元素范围为 0 - arr.length
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        // 至少要有两个元素, 才能进行快排
        if (end - begin < 2) return;

        // 确定轴点位置
        int mid = pivotIndex(begin, end);
        // 对子序列进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 构造出[begin, end)范围的轴点元素
     *
     * @param begin
     * @param end
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        // 随机选择一个元素跟begin位置进行交换，为了降低最好时间复杂度
        swap(begin, begin + (int)(Math.random() * (end - begin)));

        // 备份begin位置的元素
        T pivot = array[begin];
        // end指向最后一个元素, 之前end索引指向的是arr.length
        end--;

        while (begin < end) {
            while (begin < end) {
                if (cmp(array[end], pivot) > 0) {   // 右边元素 > 轴点元素
                    end--;
                } else {    // 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (cmp(array[begin], pivot) < 0) { // 左边元素< 轴点元素
                    begin++;
                } else {    // 左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;
                }
            }
        }

        // 将轴点元素放入最终的位置
        array[begin] = pivot;
        // 返回轴点元素的位置
        return begin;
    }
}
