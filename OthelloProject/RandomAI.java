import java.util.ArrayList;
import java.util.*;

public class RandomAI implements IOthelloAI{

    /**
     * Returns random move
     */
    public Position decideMove(GameState s){
        ArrayList<Position> moves = s.legalMoves();
        Random r = new Random();
        if ( !moves.isEmpty() )
            return moves.get (r.nextInt(moves.size()));
        else
            return new Position(-1,-1);
    }

}
