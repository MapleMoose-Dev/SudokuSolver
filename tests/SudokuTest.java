

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class SudokuTest {

    SudokuBoard board;


    @Before
    public void setupBoard() {
        board = new SudokuBoard("907156000325400086106823507000040801200080005508060000701234908830005714000718602");
        board.printInFormat();
    }

    @Test
    public void assertBoardIsValid() {
       Assert.assertFalse(board.isInvalid());
    }


    @Test
    public void testBacktracking() {
        Assert.assertFalse(board.isInvalid());
    }

}