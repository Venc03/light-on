import java.util.Random;
import java.util.Scanner;



public class LightOnGame {
    private static final int P_GROUND = 5;

    
    
    private boolean[][] lights;

    
    
    public LightOnGame() {
        lights = new boolean[P_GROUND][P_GROUND];
        Random random = new Random();

        for (int i = 0; i < P_GROUND; i++) {
            for (int j = 0; j < P_GROUND; j++) {
                lights[i][j] = random.nextBoolean();
            }
        }
    }

    
    
    private void printGrid() {
        for (int i = 0; i < P_GROUND; i++) {
            for (int j = 0; j < P_GROUND; j++) {
                System.out.print(lights[i][j] ? "O " : "I ");
            }
            System.out.println();
        }
    }

    
    
    private void toggleLight(int row, int col) {
        lights[row][col] = !lights[row][col];
    }

    
    
    private void toggleAdjacentLights(int row, int col) {
        if (row > 0) toggleLight(row - 1, col); // Toggle light above
        if (row < P_GROUND - 1) toggleLight(row + 1, col); // Toggle light below
        if (col > 0) toggleLight(row, col - 1); // Toggle light to the left
        if (col < P_GROUND - 1) toggleLight(row, col + 1); // Toggle light to the right
    }

    
    
    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my game. Youre objective is to turn all the lights on.");
        System.out.println("\tRules:\nWhen you turn on a light the bordering lights will turn off.");
        System.out.println("O - Light is on");
        System.out.println("I - Light is off\n");
        
        boolean allLightsOn = true;
        for (int i = 0; i < P_GROUND; i++) {
            for (int j = 0; j < P_GROUND; j++) {
                if (!lights[i][j]) {
                    allLightsOn = false;
                    break;
                }
            }
        }
        
        while (allLightsOn == false) {
            printGrid();
            
            System.out.print("\nEnter the row number (1-" + (P_GROUND) + "): ");
            int row = scanner.nextInt() - 1;
            while (row > 4 || row < 0){
            System.out.println("WRONG NUMBER!!! Please reenter the row number (0-" + (P_GROUND) + "): ");
            row = scanner.nextInt() - 1;
            }
            
            System.out.println("Enter the column number (1-" + (P_GROUND) + "): ");
            int col = scanner.nextInt() - 1;
            while (col > 4 || col < 0){
                System.out.println("WRONG NUMBER!!! Please reenter the column number (0-" + (P_GROUND) + "): ");
                col = scanner.nextInt() - 1;
            }
            toggleAdjacentLights(row, col);
        }

        // Check if all lights are turned on
        

        if (allLightsOn) {
            System.out.println("Congratulations! You turned all lights on!");
        } else {
            System.out.println("Game over! Better luck next time.");
        }

        scanner.close();
    }
    
}

