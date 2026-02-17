import java.util.ArrayList;

public class SmartAI  implements IOthelloAI{

    public record EvalMove(int value, Position move) {}

    /**
     * Out Utility function. Looks at how many black - white tokens.
     * @param s
     * @return
     */
    public int evaluatePos(GameState s){
        var tokenArr = s.countTokens();
        var blackTokens = tokenArr[0];
        var whiteTokens = tokenArr[1];

        // just see who has most tokens
        return blackTokens - whiteTokens;
    }

    public boolean isCutoff(GameState s, int depth){
        return depth > 6;
    }

    public EvalMove MaxValue(GameState s, int depth, int alpha, int beta){
        ArrayList<Position> moves = s.legalMoves();
        if(isCutoff(s, depth) || s.isFinished()){
            return new EvalMove(evaluatePos(s),null);
        }

        int bestEval = Integer.MIN_VALUE;
        Position bestMove = new Position(-1,-1);

        for(int i = 0; i < moves.size(); i++){
            GameState gameSim = new GameState(s.getBoard(), s.getPlayerInTurn());
            var pos = moves.get(i);

            if (gameSim.insertToken(pos) && pos != null) {
                EvalMove ev = MinValue(gameSim, depth + 1, alpha, beta);
                if (ev.value > bestEval){
                    bestEval = ev.value;
                    bestMove = pos;
                    alpha = Math.max(alpha, ev.value);
                }
                if(bestEval >= beta) return new EvalMove(bestEval, bestMove);
            }
        }
        return new EvalMove(bestEval, bestMove);
    }

    public EvalMove MinValue(GameState s, int depth, int alpha, int beta){
        ArrayList<Position> moves = s.legalMoves();
        if(isCutoff(s, depth) || s.isFinished()){
            return new EvalMove(evaluatePos(s), null);
        }
        int bestEval = Integer.MAX_VALUE;
        Position bestMove = new Position(-1,-1);

        for(int i = 0; i < moves.size(); i++){
            GameState gameSim = new GameState(s.getBoard(), s.getPlayerInTurn());
            var pos = moves.get(i);

            if (gameSim.insertToken(pos)) {
                EvalMove ev = MaxValue(gameSim, depth + 1, alpha, beta);
                if (ev.value < bestEval){
                    bestEval = ev.value;
                    bestMove = pos;
                    beta = Math.min(beta, ev.value);
                }
            }
            if (bestEval <= alpha) return new EvalMove(bestEval, bestMove);
        }
        return new EvalMove(bestEval, bestMove);
    }


    public Position decideMove(GameState s){
       EvalMove best = MaxValue(s, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
       return best.move;
    }
}
