import org.testng.annotations.*;

public class EmptyTestClass1 {

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test Class1");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method Class1");
    }

    @Test
    public void emptyTest1() {
        System.out.println("Empty Test Class1");
        assert 1 == 1;
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method Class1");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test Class1");
    }
}
