package Abstraction;

public class BaseClass1 extends AbstractClass{
    @Override
    public void print(String name) {
        System.out.println("printing name  " + name);
    }
}

class TestAbstractClass{
    public static void main(String[] args){
        BaseClass1 b = new BaseClass1();
        System.out.println(AbstractClass.unmatchedKeyList);
        b.print("asad");
    }
}
