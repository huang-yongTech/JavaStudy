package com.hy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap的无序是指数据存入和取出来的顺序不一样，但是HashMap的数据取出还是按照一定顺序的
 */
public class TestHashMap {
    public static void main(String[] args) {
        HashMap<String, Student> map = new HashMap<>();
        map.put("cds", new Student(1, 1, "teacher"));
        map.put("sva", new Student(3, 3, "student"));
        map.put("hua", new Student(5, 5, "IT"));
        map.put("bff", new Student(2, 2, "professor"));
        map.put("2ju", new Student(0, 0, "123"));

        //使用Iterator遍历会更有效率么？
        for (Map.Entry<String, Student> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + " id:" + entry.getValue().getId() +
                    " age:" + entry.getValue().getAge() + " profess:" + entry.getValue().getProfess());
        }
    }

    private static class Student {
        private int id;
        private int age;
        private String profess;

        String getProfess() {
            return profess;
        }

        public void setProfess(String profess) {
            this.profess = profess;
        }

        public Student() {
        }

        Student(int id, int age, String profess) {
            this.id = id;
            this.age = age;
            this.profess = profess;
        }

        int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
