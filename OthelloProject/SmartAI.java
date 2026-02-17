import java.util.ArrayList;

public class SmartAI  implements IOthelloAI{



    public Position decideMove(GameState s){
        ArrayList<Position> moves = s.legalMoves();
        if ( !moves.isEmpty() )
            return moves.get(0);
        else
            return new Position(-1,-1);
    }
}
