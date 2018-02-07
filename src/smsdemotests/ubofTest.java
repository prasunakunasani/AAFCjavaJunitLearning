package smsdemotests;

import org.junit.jupiter.api.Test;
import smsdemo.Ubof;

import static org.junit.jupiter.api.Assertions.*;

class ubofTest {

    private Ubof uboftest;

    @Test
    void ubofmethod() {
        //Nicolas had it up in the class
        //Ubof uboftest;

        //This the same issue you were having with userBackingObjectFactoryTest that you created. This doesn't work here either. Something needs to happen with InjectMocks.
        String actual = uboftest.ubofmethod();

        System.out.println(actual);

    }
}