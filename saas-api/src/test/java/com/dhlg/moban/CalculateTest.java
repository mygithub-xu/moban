package com.dhlg.moban;

import org.junit.Assert;
import org.junit.Test;

public class CalculateTest {

    @Test
    public void testAdd(){int result = new Calculate().add(1,2);
        Assert.assertEquals(3,result);
    }

    @Test
    public void testSub(){int result = new Calculate().sub(4,1);
        Assert.assertEquals(3,result);
    }

    @Test public void testMul(){int result = new Calculate().mul(3,3);
        Assert.assertEquals(9,result);
    }

    @Test
    public void testDiv(){
        int result = new Calculate().div(8,2);
        Assert.assertEquals(4,result);
    }
}
