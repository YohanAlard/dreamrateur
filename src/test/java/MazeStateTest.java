import com.yalard.dreamrateur.constants.CellType;
import com.yalard.dreamrateur.constants.Direction;
import com.yalard.dreamrateur.dao.MazeState;
import com.yalard.dreamrateur.exception.TechException;
import com.yalard.dreamrateur.model.CoordinateInfo;
import com.yalard.dreamrateur.validator.MazeValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by yohanalard on 24/03/2017.
 */
public class MazeStateTest {
    @Before
    public void setUp() throws Exception {
        //we clear lines of the singleton map for each test
        MazeState.INSTANCE.addLine("MMMMM");
        MazeState.INSTANCE.addLine("M  MM");
        MazeState.INSTANCE.addLine("M   M");
        MazeState.INSTANCE.addLine("M   M");
        MazeState.INSTANCE.addLine("MMMMM");
        MazeState.INSTANCE.setRobotPosition(1,1);
    }

    @Test
    public void testCheckCurrentPosition() throws TechException {
        CoordinateInfo coordinateInfo = MazeState.INSTANCE.getCurrentPosition();
        Assert.assertTrue(coordinateInfo.getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getDownType().getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getLeftType().getCellType() == CellType.WALL);
        Assert.assertTrue(coordinateInfo.getRightType().getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getUpType().getCellType() == CellType.WALL);
    }

    @Test
    public void testMoveRightWithHistory()  throws TechException {
        CoordinateInfo coordinateInfo = MazeState.INSTANCE.moveRight(true);
        Assert.assertTrue(coordinateInfo.getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getDownType().getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getLeftType().getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getRightType().getCellType() == CellType.WALL);
        Assert.assertTrue(coordinateInfo.getUpType().getCellType() == CellType.WALL);
        Assert.assertTrue(coordinateInfo.getDirection() == Direction.RIGHT);
    }

    @Test
    public void testMoveRight()  throws TechException {
        CoordinateInfo coordinateInfo = MazeState.INSTANCE.moveRight(false);
        Assert.assertTrue(coordinateInfo.getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getDownType().getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getLeftType().getCellType() == CellType.SPACE);
        Assert.assertTrue(coordinateInfo.getRightType().getCellType() == CellType.WALL);
        Assert.assertTrue(coordinateInfo.getUpType().getCellType() == CellType.WALL);
        Assert.assertTrue(coordinateInfo.getDirection() == null);
    }

    //TODO Should complete left up and down test ...
}
