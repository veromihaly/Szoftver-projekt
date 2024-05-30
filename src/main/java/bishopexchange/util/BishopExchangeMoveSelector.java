package bishopexchange.util;


import bishopexchange.model.BishopExchangeModel;
import bishopexchange.model.Position;
import bishopexchange.model.Square;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.application.Platform;
import org.tinylog.Logger;

public class BishopExchangeMoveSelector {
    public enum Phase{
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE

    }

    private BishopExchangeModel model;

    private ReadOnlyObjectWrapper<Phase> phase = new ReadOnlyObjectWrapper<>(Phase.SELECT_FROM);
    private boolean invalidSelection = false;
    private Position from;
    private Position to;

    private Square currentPlayerSquare = Square.WHITE;

    public BishopExchangeMoveSelector(BishopExchangeModel model) {
        this.model = model;
    }

    public Phase getPhase() {
        return phase.get();
    }


    public ReadOnlyObjectProperty<Phase> phaseProperty() {
        return phase.getReadOnlyProperty();
    }

    public boolean isReadyToMove() {
        return phase.get() == Phase.READY_TO_MOVE;
    }

    public void select(Position position) {
        switch (phase.get()) {
            case SELECT_FROM -> selectFrom(position);
            case SELECT_TO -> selectTo(position);
            case READY_TO_MOVE -> throw new IllegalStateException();
        }
    }
    private void selectFrom(Position position) {
        if (model.getSquare(position) == currentPlayerSquare) {
            from = position;
            phase.set(Phase.SELECT_TO);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }


    private void selectTo(Position position) {
        if (model.isEmpty(position)) {

            if (model.canMove(from, position)) {
                to = position;
                phase.set(Phase.READY_TO_MOVE);
                invalidSelection = false;
            } else {
                invalidSelection = true;
            }
        } else if (model.getSquare(position) == currentPlayerSquare) {
            phase.set(Phase.SELECT_FROM);
            selectFrom(position);
        }
    }

    public Position getFrom() {
        if (phase.get() == Phase.SELECT_FROM) {
            throw new IllegalStateException();
        }
        return from;
    }


    public Position getTo() {
        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        return to;
    }

    public boolean isInvalidSelection() {
        return invalidSelection;
    }

    public void makeMove() {
        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }

        model.move(from, to);
        reset();
        if (model.isGameOver()) {
            // Perform game over actions
            Logger.info("Game Over!");
            Platform.exit();

        }
        currentPlayerSquare = currentPlayerSquare == Square.WHITE ? Square.BLACK : Square.WHITE;
    }

    public void reset() {
        from = null;
        to = null;
        phase.set(Phase.SELECT_FROM);
        invalidSelection = false;
    }

    public Square getCurrentPlayerSquare() {
        return currentPlayerSquare;
    }


}
