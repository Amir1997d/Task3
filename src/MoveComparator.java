import java.util.List;

public class MoveComparator {

    private List<String> moves;

    public MoveComparator(List<String> moves) {
        this.moves = moves;
    }

    public String determineWinner(int userMove, int computerMove) {
        int n = moves.size();
        int half = n / 2;

        int diff = (computerMove - userMove + n) % n;
        if (diff == 0) {
            return "Draw";
        } else if (diff <= half) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }
}
