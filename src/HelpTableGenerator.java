import java.util.List;

public class HelpTableGenerator {
    private List<String> moves;

    public HelpTableGenerator(List<String> moves) {
        this.moves = moves;
    }

    public void generateHelpTable() {
        int n = moves.size();

        System.out.print("+-------------+");

        for (String move : moves) {
            System.out.print("--------");
        }

        System.out.println("+");
        System.out.print("| v User\\PC > |");

        for (String move : moves) {
            System.out.printf(" %5s |", move);
        }

        System.out.println();
        System.out.print("+-------------+");

        for (String move : moves) {
            System.out.print("--------");
        }

        System.out.println("+");

        for (int i = 0; i < n; i++) {
            System.out.printf("| %-11s |", moves.get(i));
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.out.print(" Draw  |");
                } else if ((j - i + n) % n <= n / 2) {
                    System.out.print(" Win   |");
                } else {
                    System.out.print(" Lose  |");
                }
            }

            System.out.println();
            System.out.print("+-------------+");

            for (String move : moves) {
                System.out.print("--------");
            }

            System.out.println("+");
        }
    }
}
