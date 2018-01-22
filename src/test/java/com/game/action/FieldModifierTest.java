package com.game.action;

import com.game.model.Cell;
import com.game.model.Field;
import com.game.model.State;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class FieldModifierTest {
  @Parameterized.Parameter(0)
  public Cell[][] actualCellsUp;

  @Parameterized.Parameter(1)
  public Cell[][] expectedCellsUp;

  private FieldModifier modifier;

  @Parameterized.Parameters
  public static List<Object[]> data() {
    List<Object[]> data = new ArrayList<>();
    data.add(new Object[]{
            new Cell[][]{
                    {instance(2), instance(0), instance(2)},
                    {instance(0), instance(2), instance(2)},
                    {instance(2), instance(2), instance(0)}
            },
            new Cell[][]{
                    {instance(4), instance(4), instance(4)},
                    {instance(0), instance(0), instance(0)},
                    {instance(0), instance(0), instance(0)}
            }
    });

    data.add(new Object[]{
            new Cell[][]{
                    {instance(2), instance(4), instance(2)},
                    {instance(0), instance(2), instance(2)},
                    {instance(2), instance(2), instance(0)}
            },
            new Cell[][]{
                    {instance(4), instance(4), instance(4)},
                    {instance(0), instance(4), instance(0)},
                    {instance(0), instance(0), instance(0)}
            }
    });

    data.add(new Object[]{
            new Cell[][]{
                    {instance(2), instance(2), instance(2)},
                    {instance(0), instance(2), instance(2)},
                    {instance(2), instance(2), instance(0)}
            },
            new Cell[][]{
                    {instance(4), instance(4), instance(4)},
                    {instance(0), instance(2), instance(0)},
                    {instance(0), instance(0), instance(0)}
            }
    });
    return data;
  }

  private static Cell instance(int value) {
    Cell cell = new Cell();
    if (value > 0) {
      cell.setCellValue(value);
      cell.setState(State.NO_EMPTY);
    }
    return cell;
  }

  @Test
  public void testExpectedUpModifiedWhenPressButtonUp() {
    modifier = new FieldModifier(new Field(actualCellsUp));
    modifier.modifiedUp();
    assertArrayEquals(expectedCellsUp, actualCellsUp);
  }
}
