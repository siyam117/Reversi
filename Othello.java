import java.util.ArrayList;

/**
 *
 * @author Mbassip2
 */
public class Othello {

    public static final int SQUARESIZE= 60;   // Basic dimensions of board
    public static final double PIECERATIO= 0.4; // ration of radius of piece to square size
    public static final int xBOARDpos= 100;   // Position of board
    public static final int yBOARDpos= 100;   // Position of board
    public static final int xMARGIN= 50;   // Position of board
    public static final int yMARGIN= 50;   // Position of board
    public static final int searchDepth= 8;   // Depth of minimax search
    

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoardState stateObject= new BoardState();
        stateObject.setContents(3, 3, 1);
        stateObject.setContents(3, 4, -1);
        stateObject.setContents(4, 3, -1);
        stateObject.setContents(4, 4, 1);
        stateObject.colour= -1;              // Black to start
        
        OthelloDisplay display= new OthelloDisplay();
        display.boardState= stateObject;
        display.repaint();


    }
    
    
    
   public static Move chooseMove(BoardState boardState){

        ArrayList<Move> moves= boardState.getLegalMoves();
        if(moves.isEmpty())
            return null;
        // participant version: replace this line with the following code
	// and provide the routines as directed in the lab exercise script.
        return minimax(boardState, searchDepth, -900, 900);
        
    }

   static Move minimax(BoardState boardState, int searchDepth, int alpha, int beta){
        ArrayList<Move> moves = boardState.getLegalMoves();
        BoardState boardStateCopy = boardState.deepCopy(); //makes a copy of main board

        if (moves.size() < 1){ //check if end game
            return null;
        }
        if (searchDepth == 0){
            return moves.get(0);
        }
        if (boardState.colour == 1){ //white
            Move leadMove = moves.get(0); 
            for(int i = 0; i < moves.size(); i++){ //loops thorugh all moves
                Move pos = moves.get(i);
                boardStateCopy.makeLegalMove(pos.x, pos.y); //child move added to copy
                Move evaluate = minimax(boardStateCopy, searchDepth - 1, alpha, beta); //recursive called made for evaluation of daughters

                if (evaluate != null){ 
                    int mEval = score(evaluate);
                    if (score(leadMove) < mEval){ //Optimal move stored
                        leadMove = moves.get(i);
                    }
                    if (alpha < mEval){ 
                        alpha = mEval;
                    }
                }
                if (alpha >= beta){
                    
                    break;
                }
            }
            return leadMove;
        }
        else{
            Move leadMove = moves.get(0);
            for(int i = 0; i < moves.size(); i++){
                Move pos = moves.get(i);
                boardStateCopy.makeLegalMove(pos.x, pos.y);
                Move evaluate = minimax(boardStateCopy, searchDepth - 1, alpha, beta);

                if (evaluate != null){
                    int mEval = score(evaluate);
                    if (score(leadMove) > mEval){
                        leadMove = moves.get(i);
                        
                    }
                    if (beta > mEval){
                        beta = mEval;
                    }
                }
                if (alpha >= beta){

                    break;
                }
            }
            return leadMove;
        }
    }
    
//Static Evaluation function
    static int score(Move pos) {
    
        if (pos.x == 1 || pos.x == 6){
            if (pos.y == 0 || pos.y == 7){            //if position is at x = 1 or x = 6
                return -20;
            }
            if (pos.y == 1 || pos.y == 6){
                return -40;
            }
            if (pos.y == 2 || pos.y == 5){
                return -5;
            }
            else{
                return -5;
            }

        }
        else if (pos.x == 2 || pos.x == 5){ //if position is at x = 2 or 5
            if (pos.y == 0 || pos.y == 7){
                return 20;
            }
            if (pos.y == 1 || pos.y == 6){
                return -5;
            }
            if (pos.y == 2 || pos.y == 5){
                return 15;
            }
            else{
                return 3;
            }
        }
        else  if (pos.x == 0 || pos.x == 7){ //if position is at x = 0 or 7
            if (pos.y == 0 || pos.y == 7){
                return 120;
            }
            if (pos.y == 1 || pos.y == 6){
                return -20;
            }
            if (pos.y == 2 || pos.y == 5){
                return 20;
            }
            else{
                return 5;
            }
            
        }
            
        else{
            if (pos.y == 0 || pos.y == 7){ //if position is at x = 3 or 4
                return 5;
            }
            if (pos.y == 1 || pos.y == 6){
                return -5;
            }
            else{
                return 3;
            }
            
        }

    }      

    
}
