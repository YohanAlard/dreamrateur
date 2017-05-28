import com.yalard.dreamrateur.dao.MazeState;
import com.yalard.dreamrateur.exception.TechException;
import com.yalard.dreamrateur.validator.MazeValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by yohanalard on 24/03/2017.
 */
public class MazeValidatorTest {
    @Before
    public void setUp() throws Exception {
        //we clear lines of the singleton map for each test
        MazeValidator.INSTANCE.clearLines();
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void forbiddenCharFailTest() throws TechException {
        exception.expect(TechException.class);
        exception.expectMessage("only M or Space are allowed");
        MazeValidator.INSTANCE.validateLine("MMM A MMM");
    }

    @Test
    public void mustStartWithWallFailTest() throws TechException {
        exception.expect(TechException.class);
        exception.expectMessage("must start with M");
        MazeValidator.INSTANCE.validateLine(" MMMMM");
    }

    @Test
    public void mustEndWithWallFailTest() throws TechException {
        exception.expect(TechException.class);
        exception.expectMessage("must end with M");
        MazeValidator.INSTANCE.validateLine("MMMMM ");
    }

    @Test
    public void mustFirstLineContainOnlyWallFailTest() throws TechException {
        exception.expect(TechException.class);
        exception.expectMessage("First line should only contains  M ");
        MazeValidator.INSTANCE.validateLine("MMM MM");
    }

    @Test
    public void mustMoreThan3CharsFailTest() throws TechException {
        exception.expect(TechException.class);
        exception.expectMessage(" line must contain at least 3 chars");
        MazeValidator.INSTANCE.validateLine("MM");
    }

    @Test
    public void validateLineSuccess() throws TechException {
        MazeValidator.INSTANCE.validateLine("MMMMM");
    }

    @Test
    public void multipleLengthLinesFailTest() throws TechException {
        MazeState.INSTANCE.addLine("MMMMM");
        exception.expect(TechException.class);
        exception.expectMessage("the line must contains" + "MMMMM".length() + "chars");
        MazeState.INSTANCE.addLine("M MM");
    }


    @Test
    public void multipleLineSucessTest() throws TechException {
        MazeState.INSTANCE.addLine("MMMMM");
        MazeState.INSTANCE.addLine("M  MM");
        MazeState.INSTANCE.addLine("M   M");
        MazeState.INSTANCE.addLine("M   M");
        MazeState.INSTANCE.addLine("MMMMM");
        Assert.assertTrue(MazeValidator.INSTANCE.getLines().size() ==5);
    }

    @Test
    public void isLastLineTest() throws TechException {
        MazeState.INSTANCE.addLine("MMMMM");
        MazeState.INSTANCE.addLine("M  MM");
        Assert.assertFalse(MazeValidator.INSTANCE.isLastLine("M   M"));
        MazeState.INSTANCE.addLine("M   M");
        MazeState.INSTANCE.addLine("M   M");
        Assert.assertTrue(MazeValidator.INSTANCE.isLastLine("MMMMM"));
    }



}
