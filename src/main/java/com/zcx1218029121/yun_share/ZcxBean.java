package com.zcx1218029121.yun_share;

public class ZcxBean {

    private String name;

    private int age;

    public String who(){
        return "name:"+name+",age:"+age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
