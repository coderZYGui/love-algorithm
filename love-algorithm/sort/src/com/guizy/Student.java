package com.guizy;

/**
 * Description:
 *
 * @author guizy
 * @date 2020/12/28 23:48
 */
public class Student implements Comparable<Student> {
    public int score;
    public int age;

    public Student(int score, int age) {
        this.score = score;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return age - o.age;
    }
}
