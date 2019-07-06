import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationTest {


Calculation calculationTest;
    @Before
    public void init()
{
    calculationTest = new Calculation();
}
@Test

    public void createMatrixForwardTest()
    {

        assertEquals(8, calculationTest.createMatrixForward().length);

    }

    @Test

    public void createMatrixBackward()
    {

        assertEquals(8, calculationTest.createMatrixBackward().length);

    }
}
