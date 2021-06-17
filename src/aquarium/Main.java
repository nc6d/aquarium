package aquarium;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var aquarium = new Aquarium("res/cfg.properties");
        var iterNum = 0;

        aquarium.checkState();

        System.out.println("ANY KEY TO START ITERATION, PRESS 'S' TO END");

        while (true) {
            System.out.print("Press your button: ");

            String n = br.readLine();
            if (!n.equalsIgnoreCase("s")){
                aquarium.nextIteration();
                iterNum += 1;
                System.out.println("Iteration #" + iterNum);
                aquarium.checkState();
                continue;

            }
            System.out.println("Game is over!");
            return;

        }
    }
}
