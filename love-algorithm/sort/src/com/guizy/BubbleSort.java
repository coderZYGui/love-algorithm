package com.guizy;

import com.guizy.tools.Integers;
import com.guizy.tools.Times;

/**
 * Description: 冒泡排序
 *
 * @author guizy1
 * @date 2020/12/21 11:28
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] array1 = Integers.random(20000, 1, 100000);
        Integer[] array2 = Integers.copy(array1);
        Times.test("bubbleSort1", () -> bubbleSort1(array1));
        Times.test("bubbleSort2", () -> bubbleSort2(array2));
    }

    public static void bubbleSort1(Integer[] array) {
        // 这里end > 0为终止条件, 只有end>0,也就是说,end为1的时候,它还可以和它前面的进行比较.
        // end为0的时候, 它前面就没有元素了
        for (int end = array.length - 1; end > 0; end--) {
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
}
