package com.guizy.sort;

/**
 * Description: 堆排序
 *
 * @author guizy1
 * @date 2020/12/28 18:27
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {

    private int heapSize;

    @Override
    protected void sort() {
        // 建堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        // 当堆数组中的size大于1的时候, 才需要进行交换,并修复二叉堆的性质(下滤)
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            // swap(0, heapSize - 1);
            // heapSize--;
            swap(0, --heapSize);

            // 对0位置进行siftDown(下滤), 修复二叉堆性质
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E element = array[index];

        // 非叶子节点索引,heapSize / 2
        int notLeafCount = heapSize >> 1;
        // index 必须是非叶子节点 (二叉堆-完全二叉树性质的堆结构)
        while (index < notLeafCount) {
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            int rightIndex = childIndex + 1;
            // 右子节点比左子节点大
            if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = array[rightIndex];
            }

            // 大于等于子节点
            if (cmp(element, child) >= 0) break;

            array[index] = child;
            // 重新设置index
            index = childIndex;
        }
        array[index] = element;
    }
}
