package de.internship.server.helper;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void testValideateString(){
        Assert.assertEquals(0 , Validator.validateString("abcd34", 2, 10, true, false, false));
        Assert.assertEquals(2 , Validator.validateString("abcd34fgfgdfg", 2, 10, true, false, false));
        Assert.assertEquals(3 , Validator.validateString("abcd34%", 2, 10, true, false, false));
        Assert.assertEquals(4 , Validator.validateString("abcd34%", 2, 10, false, true, false));
        //Assert.assertEquals(4 , Validator.validateString("Julius", 2, 10, false, true, false));

        Assert.assertEquals(5, Validator.validateString("Meiblichers", 4, 99, false, false, true));
        Assert.assertEquals(1, Validator.validateString("kek123", 2, 10, true, true, false));
        Assert.assertEquals(0, Validator.validateInt(2002));
        Assert.assertEquals(1, Validator.validateInt(1899));
        Assert.assertEquals(2, Validator.validateInt(2020));
    }
}
