package com.game.action;

import com.game.model.Cell;
import com.game.model.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class ActionFieldLeftTest {

  private Cell[][] actualCellsUp;
  private Cell[][] expectedCellsUp;

  private ActionField modifier;

  public ActionFieldLeftTest(Cell[][] actualCellsUp, Cell[][] expectedCellsUp) {
    this.actualCellsUp = actualCellsUp;
    this.expectedCellsUp = expectedCellsUp;
    modifier = new ActionField(new Field(this.actualCellsUp));
  }

  @Parameterized.Parameters
  public static List<Object[]> data() {
    List<Object[]> data = new ArrayList<>();
    data.add(new Object[]{
            new Cell[][]{
                    {instance(4, 0, 0), instance(0, 0, 1), instance(2, 0, 2)},
                    {instance(0, 1, 0), instance(5, 1, 1), instance(2, 1, 2)},
                    {instance(4, 2, 0), instance(5, 2, 1), instance(0, 2, 2)}
            },
            new Cell[][]{
                    {instance(4, 0, 0), instance(2, 0, 1), instance(0, 0, 2)},
                    {instance(5, 1, 0), instance(2, 1, 1), instance(0, 1, 2)},
                    {instance(4, 2, 0), instance(5, 2, 1), instance(0, 2, 2)}
            }
    });

    data.add(new Object[]{
            new Cell[][]{
                    {instance(2, 0, 0), instance(4, 0, 1), instance(2, 0, 2)},
                    {instance(0, 1, 0), instance(2, 1, 1), instance(2, 1, 2)},
                    {instance(2, 2, 0), instance(2, 2, 1), instance(0, 2, 2)}
            },
            new Cell[][]{
                    {instance(2, 0, 0), instance(4, 0, 1), instance(2, 0, 2)},
                    {instance(4, 1, 0), instance(0, 1, 1), instance(0, 1, 2)},
                    {instance(4, 2, 0), instance(0, 2, 1), instance(0, 2, 2)}
            }
    });

    data.add(new Object[]{
            new Cell[][]{
                    {instance(2, 0, 0), instance(2, 0, 1), instance(2, 0, 2)},
                    {instance(0, 1, 0), instance(2, 1, 1), instance(2, 1, 2)},
                    {instance(2, 2, 0), instance(2, 2, 1), instance(0, 2, 2)}
            },
            new Cell[][]{
                    {instance(4, 0, 0), instance(2, 0, 1), instance(0, 0, 2)},
                    {instance(4, 1, 0), instance(0, 1, 1), instance(0, 1, 2)},
                    {instance(4, 2, 0), instance(0, 2, 1), instance(0, 2, 2)}
            }
    });

    data.add(new Object[]{
            new Cell[][]{
                    {instance(0, 0, 0), instance(2, 0, 1), instance(2, 0, 2)},
                    {instance(0, 1, 0), instance(2, 1, 1), instance(2, 1, 2)},
                    {instance(2, 2, 0), instance(2, 2, 1), instance(0, 2, 2)}
            },
            new Cell[][]{
                    {instance(4, 0, 0), instance(0, 0, 1), instance(0, 0, 2)},
                    {instance(4, 1, 0), instance(0, 1, 1), instance(0, 1, 2)},
                    {instance(4, 2, 0), instance(0, 2, 1), instance(0, 2, 2)}
            }
    });
    return data;
  }

  private static Cell instance(int value, int i, int j) {
    Cell cell = new Cell(i, j);
    if (value > 0) {
      cell.setCellValue(value);
    }
    return cell;
  }

  @Test
  public void testExpectedUpModifiedWhenPressButtonUp() {
    modifier.actionLeft();
    assertArrayEquals(expectedCellsUp, actualCellsUp);
  }
}
