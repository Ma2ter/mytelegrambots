package telegrambot.utils.roll;

import com.sun.org.apache.bcel.internal.generic.FADD;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import telegrambot.common.results.RollResult;

import static org.junit.Assert.*;

/**
 * Created by atols on 28.08.2017.
 */
@RunWith(Arquillian.class)
public class DiceRollerTest extends Assert{

    private static final Integer COUNT = 1000000;

    private Long getMidValue(String roll){
        Long numResult = 0L;
        System.out.println(numResult);
        for(int i = 0; i < COUNT; i ++){
            RollResult rollResult = DiceRoller.roll("2d6", false);
            numResult += Long.parseLong(rollResult.getResult());
        }
        numResult /= COUNT;
        return numResult;
    }

    @Test
    public void roll() throws Exception {
        assertEquals("60.0", DiceRoller.roll("15 + 15 + 15+15 + 0 + 0 + 0", false).getResult());
        assertEquals("2.0", DiceRoller.roll("sqrt(4)", false).getResult());
        assertTrue(DiceRoller.roll("qwe", false).toString().contains("Unexpected"));
        assertTrue(DiceRoller.roll("1/0", false).toString().contains("Infinity"));
        assertTrue(DiceRoller.roll("0/0", false).toString().contains("NaN"));
        assertEquals(DiceRoller.roll("0d5", false).getResult(), "0.0");
        assertTrue(DiceRoller.roll("5d0", false).toString().contains("positive"));

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DiceRoller.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
