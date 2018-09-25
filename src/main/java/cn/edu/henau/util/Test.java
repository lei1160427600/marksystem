package cn.edu.henau.util;

/**
 * @Author xue
 * @Description:
 * @Date Created in 23:19 2018/3/12
 * @Modified By:
 */
public class Test {
    private String name ;
    private int age;
    public Test() {
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
    /**
     * @Description
     * @Date:2018/3/12 23:30
     * @Parameters:[]
     * @Return:java.lang.String
     */
    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
