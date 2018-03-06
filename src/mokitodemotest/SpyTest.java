package mokitodemotest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Spy
    List<String> spyList = new ArrayList<>(); //A spy by default retains all logic from ArrayList

     @BeforeEach
     public void setup()
     {
         MockitoAnnotations.initMocks(this);
     }

    @Test
    public void test()
    {

        assertEquals(0,spyList.size());
          spyList.add("Placeholder");

        assertEquals(1,spyList.size());
        spyList.remove("Placeholder");

        assertEquals(0,spyList.size());

        //The order matters so if you move it above, the assert statements will fail
        when(spyList.size()).thenReturn(5);
        assertEquals(5,spyList.size());

        verify(spyList).add(any(String.class));


        verify(spyList, never()).clear();

    }
}


/* MOCK WAY:

public class SpyTest {

    @Mock
    List arrayListMock;



     @BeforeEach
     public void setup()
     {
         MockitoAnnotations.initMocks(this);
     }

    @Test
    public void test()
    {

        assertEquals(0,arrayListMock.size());

        when(arrayListMock.size()).thenReturn(5);

        assertEquals(5,arrayListMock.size());

        //This doesn't cause change in size since we aren't talking to the real arrayList class since no logic from there is used.
        arrayListMock.add("Placeholder");

        //However, if you want to use the arrayList class and track if certain methods are called and override some methods
        //With mocks, we don't care about the impl of class but sometimes might want to override specific functionality of class - then we need a spy


    }
}


 */