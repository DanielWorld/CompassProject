package danielworld.compassproject.util;

import junit.framework.Assert;

/**
 * Copyright (C) 2014-2015 Daniel Park, op7773hons@gmail.com
 * <p/>
 * This file is part of CompassProject (https://github.com/NamgyuWorld)
 * Created by danielpark on 2015. 7. 2..
 */
public class ValidationCheckTest {


    @org.junit.Test
    public void checkLongitudeDegreeTest(){

        Assert.assertTrue(ValidationCheck.checkLongitudeDegree("89"));
        Assert.assertTrue(ValidationCheck.checkLongitudeDegree("38"));
        Assert.assertTrue(ValidationCheck.checkLongitudeDegree("-39"));
        Assert.assertTrue(ValidationCheck.checkLongitudeDegree("000"));

        Assert.assertFalse(ValidationCheck.checkLongitudeDegree("92"));
        Assert.assertFalse(ValidationCheck.checkLongitudeDegree("0000"));
        Assert.assertFalse(ValidationCheck.checkLongitudeDegree("-92"));
        Assert.assertFalse(ValidationCheck.checkLongitudeDegree("-38.2"));
        Assert.assertFalse(ValidationCheck.checkLongitudeDegree("-92jg92"));
    }

    @org.junit.Test
    public void checkLatitudeDegreeTest(){
        Assert.assertTrue(ValidationCheck.checkLatitudeDegree("89"));
        Assert.assertTrue(ValidationCheck.checkLatitudeDegree("000"));
        Assert.assertTrue(ValidationCheck.checkLatitudeDegree("138"));
        Assert.assertTrue(ValidationCheck.checkLatitudeDegree("-180"));
        Assert.assertTrue(ValidationCheck.checkLatitudeDegree("0000"));

        Assert.assertFalse(ValidationCheck.checkLatitudeDegree("192"));
        Assert.assertFalse(ValidationCheck.checkLatitudeDegree("00000"));
        Assert.assertFalse(ValidationCheck.checkLatitudeDegree("-192"));
        Assert.assertFalse(ValidationCheck.checkLatitudeDegree("-38.2"));
        Assert.assertFalse(ValidationCheck.checkLatitudeDegree("-92jg92"));
    }

    @org.junit.Test
    public void checkMinuteTest(){
        Assert.assertTrue(ValidationCheck.checkMinute("39"));
        Assert.assertTrue(ValidationCheck.checkMinute("10"));
        Assert.assertTrue(ValidationCheck.checkMinute("59"));

        Assert.assertFalse(ValidationCheck.checkMinute("60"));
        Assert.assertFalse(ValidationCheck.checkMinute("0000"));
        Assert.assertFalse(ValidationCheck.checkMinute("-192"));
        Assert.assertFalse(ValidationCheck.checkMinute("192"));
        Assert.assertFalse(ValidationCheck.checkMinute("-38.2"));
        Assert.assertFalse(ValidationCheck.checkMinute("-92jg92"));
    }

    @org.junit.Test
    public void checkSecondTest(){
        Assert.assertTrue(ValidationCheck.checkSecond("39"));
        Assert.assertTrue(ValidationCheck.checkSecond("10"));
        Assert.assertTrue(ValidationCheck.checkSecond("59"));

        Assert.assertFalse(ValidationCheck.checkSecond("60"));
        Assert.assertFalse(ValidationCheck.checkSecond("0000"));
        Assert.assertFalse(ValidationCheck.checkSecond("-192"));
        Assert.assertFalse(ValidationCheck.checkSecond("192"));
        Assert.assertFalse(ValidationCheck.checkSecond("-38.2"));
        Assert.assertFalse(ValidationCheck.checkSecond("-92jg92"));
    }
}
