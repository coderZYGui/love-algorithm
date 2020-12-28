package com.guizy.sort;

/**
 * Description: Sort基类
 *
 * @author guizy1
 * @date 2020/12/28 18:23
 */
public abstract class Sort {

    protected Integer[] array;
    private int cmpCount;
    private int swapCount;

    public void sort(Integer[] array) {
        if (array == null || array.length < 2) return;
        this.array = array;
        sort();
    }

    protected abstract void sort();

    /*
        返回值等于0, 代表 array[i1] == array[i2]
        返回值小于0, 代表 array[i1] < array[i2]
        返回值大于0, 代表 array[i1] > array[i2]
     */
    protected int cmp(int i1, int i2) {
        cmpCount++;
        return array[i1] - array[i2];
    }

    protected void swap(int i1, int i2) {
        swapCount++;
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }
}
