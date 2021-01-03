package com.guizy;

import com.guizy.sort.*;
import com.guizy.tools.Asserts;
import com.guizy.tools.Integers;
import com.guizy.tools.Times;
import org.junit.Test;

import java.util.Arrays;

/**
 * Description: 排序
 *
 * @author guizy1
 * @date 2020/12/21 11:28
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Main {

    @Test
    public void testBinarySearch() {
        // int[] array = {2, 4, 6, 8, 10};
        // Asserts.test(BinarySearch.indexOf(array, 4) == 1);
        // Asserts.test(BinarySearch.indexOf(array, 2) == 0);
        // Asserts.test(BinarySearch.indexOf(array, 10) == 4);
        // Asserts.test(BinarySearch.indexOf(array, 43) == -1);

        int[] array = {2, 4, 8, 8, 8, 12, 14};
        Asserts.test(BinarySearch.search(array, 5) == 2);
        Asserts.test(BinarySearch.search(array, 1) == 0);
        Asserts.test(BinarySearch.search(array, 15) == 7);
        Asserts.test(BinarySearch.search(array, 8) == 5);
    }

    public static void main(String[] args) {
        // Integer[] array1 = Integers.random(10000, 1, 20000);
        // Integer[] array2 = Integers.copy(array1);
        // Integer[] array3 = Integers.copy(array1);
        // Times.test("HeapSort", () -> new HeapSort().sort(array1));
        // Times.test("SelectionSort", () -> new SelectionSort().sort(array2));
        // Times.test("BubbleSort3", () -> new BubbleSort3().sort(array3));

        Integer[] array = Integers.random(10000, 1, 20000);
        testSorts(array,
                // new BubbleSort1(),
                // new BubbleSort2(),
                new InsertionSort1(),
                new InsertionSort2(),
                new InsertionSort3(),
                new SelectionSort(),
                new HeapSort(),
                new BubbleSort3());
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
        }
        // 根据Sort[]数组中对象的 排序时间 进行排序
        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

    // ----------------------------------选择排序----------------------------------------
    @Test
    public void test() {
        Integer[] array = Integers.random(10, 1, 100);
//        Integers.println(array);
//        selectionSort(array);
//        Integers.println(array);
        selectionSort(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    public static void selectionSort(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {  // 一共比较多少趟
            int maxIndex = 0; // 假如索引0位置的元素是最大的
            for (int begin = 1; begin <= end; begin++) {
                if (array[maxIndex] <= array[begin]) {
                    maxIndex = begin;
                }
            }
            int temp = array[maxIndex];
            array[maxIndex] = array[end];   // 每一趟循环, 都和本次循环最后的元素做交换
            array[end] = temp;
        }
    }

    // ----------------------------------冒泡排序----------------------------------------
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
            // 索引begin从1开始,begin-1,就可以拿到0的位置元素了
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
