package com.nowcoder.community;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TestJava {

    public static void main(String[] args) {
        A rec = new B();

        System.out.println(rec.num);
        rec.print();

    }

    static class A {
        int num = 1;
        public void print() {
            System.out.println("我是A");
        }
    }

    static class B extends A {
        int num = 2;
        public void print() {
            System.out.println("我是B");
        }

        public void print_b() {
            System.out.println("我当然是B了");
        }
    }

}
