import bishopexchange.model.BishopExchangeModel;
import bishopexchange.model.Position;
import bishopexchange.model.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BishopExchangeModelTest {
    private BishopExchangeModel model;

    @BeforeEach
    void setUp() {
        model = new BishopExchangeModel();
    }

    @Test
    void squareProperty() {
        Assertions.assertEquals(Square.BLACK, model.squareProperty(0, 0).get());
        Assertions.assertEquals(Square.WHITE, model.squareProperty(BishopExchangeModel.ROW - 1, 0).get());
        Assertions.assertEquals(Square.NONE, model.squareProperty(2, 2).get());
    }

    @Test
    void move() {
        Position from = new Position(0, 0);
        Position to = new Position(1, 1);
        model.move(from, to);
        Assertions.assertEquals(Square.NONE, model.getSquare(from));
        Assertions.assertEquals(Square.BLACK, model.getSquare(to));
    }

    @Test
    void canMove() {
        // Create a new model
        BishopExchangeModel model = new BishopExchangeModel();

        // Test valid moves
        Position validFrom = new Position(1, 1);
        Position validTo = new Position(2, 2);
        Assertions.assertFalse(model.canMove(validFrom, validTo));

        // Test invalid moves
        Position invalidFrom = new Position(0, 0);
        Position invalidTo = new Position(2, 3);
        Assertions.assertFalse(model.canMove(invalidFrom, invalidTo));

        // Test moving to a position occupied by the same color piece
        model.setSquare(validTo, Square.WHITE); // Place a piece on the destination square
        Position occupiedFrom = new Position(3, 3);
        Position occupiedTo = new Position(4, 3);
        Assertions.assertFalse(model.canMove(occupiedFrom, occupiedTo));

        // Test moving diagonally but with obstacles in the path
        Position obstacleFrom = new Position(2, 2);
        Position obstacleTo = new Position(4, 3);
        model.setSquare(new Position(3, 3), Square.BLACK); // Place an obstacle on the path
        Assertions.assertFalse(model.canMove(obstacleFrom, obstacleTo));

        Position from = new Position(0, 0);
        Position to = new Position(4, 3);

        // Act
        boolean result = model.canMove(from, to);


        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void isEmpty() {
        Position emptyPosition = new Position(2, 2);
        Assertions.assertTrue(model.isEmpty(emptyPosition));

        Position occupiedPosition = new Position(0, 0);
        Assertions.assertFalse(model.isEmpty(occupiedPosition));
    }

    @Test
    void isOnBoard() {
        Position validPosition = new Position(3, 2);
        Assertions.assertTrue(BishopExchangeModel.isOnBoard(validPosition));

        Position invalidPosition = new Position(-1, 5);
        Assertions.assertFalse(BishopExchangeModel.isOnBoard(invalidPosition));
    }

    @Test
    void isGameOver() {
        Assertions.assertFalse(model.isGameOver());

        for (int col = 0; col < BishopExchangeModel.COLUMN; col++) {
            model.setSquare(new Position(0, col), Square.WHITE);
            model.setSquare(new Position(BishopExchangeModel.ROW - 1, col), Square.BLACK);
        }

        Assertions.assertTrue(model.isGameOver());
    }

    @Test
    void cellIsNotTargeted(){
        Position start = new Position(2, 2);
        Position target = new Position(4, 4);
        BishopExchangeModel bishopExchangeModel = new BishopExchangeModel(); // Cseréld le a YourClass azonosítóra a saját osztályodra

        // Act
        boolean result = bishopExchangeModel.cellIsNotTargeted(start, target);

        // Assert
        Assertions.assertTrue(result, "The cell should not be targeted.");
    }


    @Test
    void testCorrectDiagonal() {
        // Előkészületek
        BishopExchangeModel bishopExchangeModel = new BishopExchangeModel();

        // Teszt esetek
        Position start = new Position(2, 2);
        Position target1 = new Position(4, 4);
        Position target2 = new Position(1, 3);
        Position target3 = new Position(3, 1);
        Position target4 = new Position(0, 0);

        boolean result1 = bishopExchangeModel.correctDiagonal(start, target1, 1, 1);
        boolean result2 = bishopExchangeModel.correctDiagonal(start, target2, 1, -1);
        boolean result3 = bishopExchangeModel.correctDiagonal(start, target3, -1, 1);
        boolean result4 = bishopExchangeModel.correctDiagonal(start, target4, -1, -1);

        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
        Assertions.assertTrue(result3);
        Assertions.assertTrue(result4);
    }
}
