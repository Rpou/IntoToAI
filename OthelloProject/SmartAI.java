import java.util.ArrayList;

public class SmartAI  implements IOthelloAI{


    public int evaluatePos(GameState s){
        var tokenArr = s.countTokens();
        var blackTokens = tokenArr[0];
        var whiteTokens = tokenArr[1];

        // just see who has most tokens
        return blackTokens - whiteTokens;
    }


    public Position decideMove(GameState s){
        ArrayList<Position> moves = s.legalMoves();

        int bestEval = Integer.MIN_VALUE;
        Position bestPos = null;

        if(!moves.isEmpty()){
            for(int i = 0; i < moves.size(); i++){
                GameState gameSim = new GameState(s.getBoard(), s.getPlayerInTurn());

                var pos = moves.get(i);
                if (gameSim.insertToken(pos) && pos != null){
                    var eval = evaluatePos(gameSim);
                    if(eval > bestEval) {
                        bestEval = eval;
                        bestPos = pos;
                    }
                }

            }
        }
        //else {
          //  return new Position(-1,-1);
        //}

        return bestPos;
    }
}
