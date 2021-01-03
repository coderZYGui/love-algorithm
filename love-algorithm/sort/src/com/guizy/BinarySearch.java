package com.guizy;

/**
 * Description: 二分查找算法
 *
 * @author guizy
 * @date 2021/1/3 11:25
 */
public class BinarySearch {

    /**
     * 查找v在有序数组中的位置
     * @param array
     * @param v
     * @return
     */
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*
     *  查找v在有序数组array中待插入位置
     */
    public static int search (int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    /**
     * 查找v在有序数组中的位置
     * @param array
     * @param v
     * @return
     */
    public static int indexOf2(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length - 1;
        while (begin <= end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid - 1;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
