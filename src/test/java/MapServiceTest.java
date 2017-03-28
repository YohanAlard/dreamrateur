import com.yalard.dreamrateur.com.yalard.dreamrateur.TechException;
import com.yalard.dreamrateur.com.yalard.dreamrateur.constants.Type;
import com.yalard.dreamrateur.com.yalard.dreamrateur.services.MapService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

/**
 * Created by yohanalard on 24/03/2017.
 */
public class MapServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void forbiddenCharFailTest() throws TechException {
        MapService mp = new MapService();
        exception.expect(TechException.class);
        exception.expectMessage("only M or Space are allowed");
        mp.validateLine("MMM A MMM");
    }

    @Test
    public void mustStartWithWallFailTest() throws TechException {
        MapService mp = new MapService();
        exception.expect(TechException.class);
        exception.expectMessage("must start with M");
        mp.validateLine(" MMMMM");
    }

    @Test
    public void mustEndWithWallFailTest() throws TechException {
        MapService mp = new MapService();
        exception.expect(TechException.class);
        exception.expectMessage("must end with M");
        mp.validateLine("MMMMM ");
    }

    @Test
    public void mustFirstLineContainOnlyWallFailTest() throws TechException {
        MapService mp = new MapService();
        exception.expect(TechException.class);
        exception.expectMessage("First line should only contains  M ");
        mp.validateLine("MMM MM");
    }

    @Test
    public void mustMoreThan3CharsFailTest() throws TechException {
        MapService mp = new MapService();
        exception.expect(TechException.class);
        exception.expectMessage(" line must contain at least 3 chars");
        mp.validateLine("MM");
    }

    @Test
    public void validateLineSuccess() throws TechException {
        MapService mp = new MapService();
        mp.validateLine("MMMMM");
    }

    @Test
    public void multipleLengthLinesFailTest() throws TechException {
        MapService mp = new MapService();
        mp.addLine("MMMMM");
        exception.expect(TechException.class);
        exception.expectMessage("the line must contains" + "MMMMM".length() + "chars");
        mp.addLine("M MM");
    }


    @Test
    public void multipleLineSucessTest() throws TechException {
        MapService mp = new MapService();
        mp.addLine("MMMMM");
        mp.addLine("M  MM");
        mp.addLine("M   M");
        mp.addLine("M   M");
        mp.addLine("MMMMM");
        Assert.assertTrue(mp.getLines().size() ==5);
    }

    @Test
    public void isLastLineTest() throws TechException {
        MapService mp = new MapService();
        mp.addLine("MMMMM");
        mp.addLine("M  MM");
        Assert.assertFalse(mp.isLastLine("M   M"));
        mp.addLine("M   M");
        mp.addLine("M   M");
        Assert.assertTrue(mp.isLastLine("MMMMM"));
    }



}
