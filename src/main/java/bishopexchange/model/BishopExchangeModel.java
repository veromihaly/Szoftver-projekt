package bishopexchange.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;

import java.util.HashSet;
import java.util.Set;

/**
 * Ez az osztály egy táblát reprezentál, ahol sötét és világos futók helyezkednek el.
 * Az osztály különböző műveleteket és ellenőrzéseket tesz lehetővé a tábla és a futók állapotával kapcsolatban.
 */
public class BishopExchangeModel implements TwoPhaseMoveState<Position> {

    public static final int ROW = 5;
    public static final int COLUMN = 4;

    private boolean isWhiteTurn = true;

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[ROW][COLUMN];

    public BishopExchangeModel() {
        for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(
                        switch (i) {
                            case 0 -> Square.BLACK;
                            case ROW - 1 -> Square.WHITE;
                            default -> Square.NONE;
                        }
                );
            }
        }
    }
public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    /**
     * Egy ReadOnlyObjectProperty típust ad vissza.
     * Ez a metódus a board nevű négyzetek mátrixának egy adott i és j indexű elemének csak olvasható tulajdonságát adja vissza.
     *
     * @param i sor koordinátája a négyzetnek
     * @param j oszlop koordinátája a négyzetnek
     * @return csak olvasható tulajdonságát adja vissza az indexek által jelölt négyzetnek
     */
    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    /**
     *Ez a metódus visszaadja a táblán található adott pozícióhoz (Position p) tartozó négyzetet.
     * @param p A négyzetet jelölő pozició
     * @return Az index által jelölt négyzet
     */
    public Square getSquare(Position p) {
        return board[p.row()][p.col()].get();
    }

    /**
     * Ez a metódus beállítja a táblán található adott pozícióhoz (Position p) tartozó négyzetet az adott Square értékre.
     * @param p A négyzetet jelölő pozició
     * @param square A pozició által jelölt négyzet
     */
    public void setSquare(Position p, Square square) {
        board[p.row()][p.col()].set(square);
    }

    /**
     * Ez a metódus végrehajt egy mozgást a táblán. Átmásolja a kezdő pozíció (from) négyzetét a célpozícióra (to), majd a kezdő pozíció négyzetét üresre állítja.
     * @param from A kezdő pozició
     * @param to A célpozició
     */
    public void move(Position from, Position to) {
        setSquare(to, getSquare(from));
        setSquare(from, Square.NONE);
        changeActiveColor();
    }

    /**
     * Ez a metódus ellenőrzi, hogy lehetséges-e egy mozgás a táblán az adott kezdő pozíciótól (from) a célpozícióig (to).
     * @param from A kiindulási pozició
     * @param to A célpozició
     * @return Logikai érték, mely tárolja, hogy lehetséges-e egy mozgás
     */
    public boolean canMove(Position from, Position to) {
        if (!isOnBoard(from) || !isOnBoard(to)) {
            return false;
        }

        if (isEmpty(from) || !isEmpty(to)) {
            return false;
        }

        Square fromSquare = getSquare(from);
        Square toSquare = getSquare(to);

        if (fromSquare == Square.BLACK && toSquare == Square.WHITE || fromSquare == Square.BLACK && isWhiteTurn) {
            return false; // Sötét futó nem léphet világos futó helyére, valamint nem léphet ha a fehér köre van.
        }

        if (fromSquare == Square.WHITE && toSquare == Square.BLACK || fromSquare == Square.WHITE && !isWhiteTurn) {
            return false; // Világos futó nem léphet sötét futó helyére, valamint nem léphet ha a fekete köre van.
        }

        if (!cellIsNotTargeted(from, to)) {
            return false;
        }

        // Ellenőrzés, hogy nincs-e másik figura az útjában az adott irányban
        int dx = to.row() - from.row();
        int dy = to.col() - from.col();

        // Az út hossza az x és y irányban azonos kell legyen
        if (Math.abs(dx) != Math.abs(dy)) {
            return false;
        }

        int xDirection = dx > 0 ? 1 : -1; // X irányú mozgásirány
        int yDirection = dy > 0 ? 1 : -1; // Y irányú mozgásirány

        int currentRow = from.row() + xDirection;
        int currentCol = from.col() + yDirection;

        // Az út ellenőrzése
        while (currentRow != to.row() && currentCol != to.col()) {
            if (!isEmpty(new Position(currentRow, currentCol))) {
                return false; // Mező már foglalt
            }
            currentRow += xDirection;
            currentCol += yDirection;
        }

        return true;
    }

    /**
     * Ez a metódus ellenőrzi, hogy az adott mező ütésben áll-e egy másik színü bábu által, vagy sem.
     * @param start A kiindulási mező poziciója
     * @param target A cél mező poziciója
     * @return logikai érték, mely visszaadja, hogy az adott mezőt üti-e egy másik színü bábu
     */
    public boolean cellIsNotTargeted(Position start, Position target) {
        return correctDiagonal(start, target, 1, 1)
                && correctDiagonal(start, target, 1, -1)
                && correctDiagonal(start, target, -1, 1)
                && correctDiagonal(start, target, -1 , -1);
    }

    public ReadOnlyObjectWrapper<Square>[][] getBoard() {
        return board;
    }

    /**
     *Ez a metódus ellenőrzi, hogy a start pozíciótól az target pozícióig helyes átló van-e a megadott irányok alapján.
     * @param start A kiindulási mező poziciója
     * @param target A cél mező poziciója
     * @param x_incr_val A sor növekedési értéke iterációként
     * @param y_incr_val Az oszlop növekedési értéke iterációként
     * @return Logikai érték, mely megadja, hogy megfelelő-e az adott átló
     */
    public boolean correctDiagonal(Position start, Position target, int x_incr_val, int y_incr_val) {
        int current_x = target.row() + x_incr_val;
        int current_y = target.col() + y_incr_val;

        Square enemyState = board[start.row()][start.col()].get() == Square.WHITE
                ? Square.BLACK : Square.WHITE;
        Square myState = board[start.row()][start.col()].get() == Square.WHITE
                ? Square.WHITE : Square.BLACK;

        Square state;

        while (-1 < current_x && current_x < ROW && -1 < current_y && current_y < COLUMN) {

            state = board[current_x][current_y].get();

            if (state == enemyState) {
                return false;
            }

            if (state == myState) {
                return true;
            }

            current_x += x_incr_val;
            current_y += y_incr_val;
        }

        return true;
    }

    /**
     * Ez a metódus ellenőrzi, hogy az adott pozícióhoz (Position p) tartozó négyzet üres-e.
     * @param p Az ellenőrizendő pozició
     * @return Logikai érték, mely tárolja, hogy üres-e az adott négyzet.
     */
    public boolean isEmpty(Position p) {
        return getSquare(p) == Square.NONE;
    }

    /**
     * Ez a statikus metódus ellenőrzi, hogy az adott pozíció (Position p) a táblán belül van-e.
     * @param p Az ellenőrizendő pozició
     * @return Logikai érták, mely tárolja, hogy a pozició megfelelő-e.
     */
    public static boolean isOnBoard(Position p) {
        return 0 <= p.row() && p.row() < ROW && 0 <= p.col() && p.col() < COLUMN;
    }

    public static boolean isPawnMove(Position from, Position to) {
        var dx = Math.abs(to.row() - from.row());
        var dy = Math.abs(to.col() - from.col());
        return dx == dy;
    }

    /**
     * Ez az osztály felüldefiniált toString() metódusa, amely visszaadja a tábla állapotát szöveges formában.
     * @return tábla állapota szöveges formában
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var i = 0; i < ROW; i++) {
            for (var j = 0; j < COLUMN; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**Ez metódus ellenőrzi, hogy a játék véget ért-e.
     * @return Logikai érték, mely tárolja, hogy a játék végetért, vagy sem.
     */
    public boolean isGameOver() {
        for (int col = 0; col < COLUMN; col++) {
            if (getSquare(new Position(0, col)) != Square.WHITE || getSquare(new Position(ROW - 1, col)) != Square.BLACK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSolved() {
        return isGameOver();
    }

    private static final Set<TwoPhaseMove<Position>> MOVES = new HashSet<>();

    @Override
    public boolean isLegalToMoveFrom(Position from) {
        return isOnBoard(from) && !isEmpty(from);
    }

    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> move) {
        Position from = move.from();
        Position to = move.to();
        return isLegalToMoveFrom(from) && canMove(from, to) && !from.equals(to);
    }

    @Override
    public void makeMove(TwoPhaseMove<Position> move) {
        move(move.from(), move.to());
    }

    private void changeActiveColor() {
        isWhiteTurn = !isWhiteTurn;
    }

    @Override
    public Set<TwoPhaseMove<Position>> getLegalMoves() {
        Set<TwoPhaseMove<Position>> legalMoves = new HashSet<>();
        for (int rowFrom = 0; rowFrom < ROW; rowFrom++) {
            for (int colFrom = 0; colFrom < COLUMN; colFrom++) {
                Position from = new Position(rowFrom, colFrom);
                if (isLegalToMoveFrom(from)) {
                    for (int rowTo = 0; rowTo < ROW; rowTo++) {
                        for (int colTo = 0; colTo < COLUMN; colTo++) {
                            Position to = new Position(rowTo, colTo);
                            TwoPhaseMove<Position> move = new TwoPhaseMove<>(from, to);
                            if (isLegalMove(move)) {
                                legalMoves.add(move);
                            }
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public BishopExchangeModel clone() {
        try {
            BishopExchangeModel cloned = (BishopExchangeModel) super.clone();
            cloned.board = new ReadOnlyObjectWrapper[ROW][COLUMN];
            for (var i = 0; i < ROW; i++) {
                for (var j = 0; j < COLUMN; j++) {
                    cloned.board[i][j] = new ReadOnlyObjectWrapper<>(this.board[i][j].get());
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean checkIfObjectWrappersAreEqual(ReadOnlyObjectWrapper<Square>[][] wrapper1, ReadOnlyObjectWrapper<Square>[][] wrapper2) {
        for (var i = 0; i < wrapper1.length; i++) {
            for (int j = 0; j < wrapper1[i].length; j++) {
                Square wrapper1Val = wrapper1[i][j].get();
                Square wrapper2Val = wrapper2[i][j].get();

                if (!wrapper1Val.equals(wrapper2Val)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BishopExchangeModel that = (BishopExchangeModel) o;
        return checkIfObjectWrappersAreEqual(board, that.board);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static void main(String[] args) {
        new BreadthFirstSearch<TwoPhaseMove<Position>>()
                .solveAndPrintSolution(new BishopExchangeModel());
    }


}
