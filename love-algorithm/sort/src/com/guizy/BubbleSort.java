package com.guizy;

import com.guizy.tools.Integers;
import com.guizy.tools.Times;
import org.junit.Test;

/**
 * Description: 冒泡排序
 *
 * @author guizy1
 * @date 2020/12/21 11:28
 */
public class BubbleSort {

    @Test
    public void test2() {
        // 表示1-10000都是asc有序
        // Integer[] array1 = Integers.ascOrder(1, 10000);
        // 表示min-max中,前disorderCount是无序的
        Integer[] array1 = Integers.tailAscOrder(1, 100000, 2000);
        Integer[] array2 = Integers.copy(array1);
        Integer[] array3 = Integers.copy(array1);
        Times.test("bubbleSort1", () -> bubbleSort1(array1));
        Times.test("bubbleSort2", () -> bubbleSort2(array2));
        Times.test("bubbleSort3", () -> bubbleSort3(array3));
        /*
            【bubbleSort1】
            开始：13:53:45.736
            结束：13:53:56.217
            耗时：10.48秒
            -------------------------------------
            【bubbleSort2】
            开始：13:53:56.221
            结束：13:53:56.667
            耗时：0.446秒
            -------------------------------------
            【bubbleSort3】
            开始：13:53:56.667
            结束：13:53:56.689
            耗时：0.022秒
            -------------------------------------
        */
    }

    /**
     * 在完全无序的数据下, 优化2的效率还没有bubbleSort2高,因为还需要执行一些额外的指令(sorted变量等)
     * 所以bubbleSort2适用于数据有序的情况下
     */
    @Test
    public void test1() {
        Integer[] array1 = Integers.random(20000, 1, 100000);
        Integer[] array2 = Integers.copy(array1);
        Times.test("bubbleSort1", () -> bubbleSort1(array1));
        Times.test("bubbleSort2", () -> bubbleSort2(array2));

        /*
            【bubbleSort1】
            开始：13:27:09.498
            结束：13:27:11.006
            耗时：1.508秒
            -------------------------------------
            【bubbleSort2】
            开始：13:27:11.008
            结束：13:27:12.970
            耗时：1.962秒
            -------------------------------------
         */
    }

    public static void bubbleSort1(Integer[] array) {
        // 这里end > 0为终止条件, 只有end>0,也就是说,end为1的时候,它还可以和它前面的进行比较.
        // end为0的时候, 它前面就没有元素了
        for (int end = array.length - 1; end > 0; end--) {  // 一共比较多少趟
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                }
            }
        }
    }

    // 针对上面的方式做优化(当给的数组本来就是有序的,此时就需要优化,不用一一比较了)
    public static void bubbleSort2(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            // 这个变量写在这里,是为了判断每一趟排序操作是否将序列变为完全有序.
            // 因为有可能在某一趟之后, 数组的元素就变为完全有序了
            boolean sorted = false; // 是否排过序
            for (int begin = 1; begin <= end; begin++) {
                // 如果不进入下面的判断,表示本轮循环肯定是有序的
                if (array[begin - 1] > array[begin]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    sorted = true;  // 排过序
                }
            }
            if (!sorted) break;
        }
    }

    public static void bubbleSort3(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            // sortedIndex=1,是针对完全有序的情况: 因为完全有序下,就不会走到下面的if判断. 所以初始值给1. 最后end=1,一轮扫描就结束了
            int sortedIndex = 1; // 从哪个索引开始,后面的都已经排好序了
            for (int begin = 1; begin <= end; begin++) {
                // 如果不进入下面的判断,表示本轮循环肯定是有序的
                if (array[begin - 1] > array[begin]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    // 记录最后一次交换的位置
                    sortedIndex = begin;
                }
            }
            // 将最后一次交换的位置,给end, 作为下次循环的终点
            end = sortedIndex;
        }
    }
}
