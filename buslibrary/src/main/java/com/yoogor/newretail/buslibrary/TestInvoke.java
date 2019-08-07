package com.yoogor.newretail.buslibrary;

/**
 * @Auther JiRui
 * @CreateTime 2019/8/7 17:52
 * @Describe
 */
public class TestInvoke {
    private String name;
    private int num;

    public TestInvoke() {
    }

    public TestInvoke(String name) {
        this.name = name;
    }

    public TestInvoke(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(name);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
