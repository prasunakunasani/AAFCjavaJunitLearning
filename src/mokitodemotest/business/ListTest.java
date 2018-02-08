package mokitodemotest.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class ListTest{


    @Mock
    private List mockList;

    @BeforeEach
    public void initMocks()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void letsMockListSizeMethod_ReturnMulitpleValues(){

        //to return single value, can do:
        //want to mock List.size() method - when List.size() is called, then return 2
        //Could use: when(mockList.size()).thenReturn(2); but apparently this won't work when stubbing void methods, when using spies and when stubbing the same method
        // more than once. So, just get into the habit of using the doReturn one. However, you need to pick for the situation (when does type checking at compile time).
        //Important - https://stackoverflow.com/questions/20353846/mockito-difference-between-doreturn-and-when
       // OR:  doReturn(2).when(mockList).size();

        //to return multiple values can do:
        //when(mockList.size()).thenReturn(2).thenReturn(3); OR:

        doReturn(2).doReturn(3).when(mockList).size();

        //System.out.println(mockList.size());
        assertEquals(2,mockList.size());
        assertEquals(3,mockList.size());

    }

    @Test
    public void letMockListGetMethod()
    {
        doReturn("Blah").when(mockList).get(0);

        //regardless of parameter, if you want the same thing to be returned, then use Argument Matcher:
        doReturn("Blah").when(mockList).get(anyInt());

        assertEquals("Blah",mockList.get(0));
        //If get(1) is not mocked and matchers are not used but call assert,When you don't tell Mockito what to do, they return defaults and your test still passes
        //assertEquals(null,mockList.get(1));

        //called to demonstrate anyInt Matcher
        assertEquals("Blah",mockList.get(390485));

    }


    //NOTE: This does not work. But maybe it'll work for a real class that needs to throw an exception
    @Test
    public void letMockList_throwAnException(){

     //   doThrow(Exception.class).when(mockList).get(5);
        doThrow(new Exception("Some error message")).when(mockList).get(anyInt());

        mockList.get(0);

    }

}


/* Important - Minimum setup required to run a mock using @annotations:

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ListTest{


    @Mock
    private List mockList;

    @BeforeEach
    public void initMocks()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test(){

        System.out.println(mockList);
           }
}


 */