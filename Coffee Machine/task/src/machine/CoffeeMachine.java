package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private enum States {
        IDLE,
        BUY,
        FILLWATER,
        FILLMILK,
        FILLBEANS,
        FILLGOBELETS,
        QUIT
    }

    private void userInput(String input) {
        switch (this.state) {
            case IDLE: {
                switch (input) {
                    case "buy": {
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                        this.state = States.BUY;
                        break;
                    }
                    case "fill": {
                        System.out.println("Write how many ml of water do you want to add:");
                        this.state = States.FILLWATER;
                        break;
                    }
                    case "take": {
                        this.take();
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    }
                    case "remaining": {
                        this.printState();
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    }
                    case "exit": {
                        this.state = States.QUIT;
                        break;
                    }
                }
                break;
            }
            case BUY: {
                this.buy(input);
                this.state = States.IDLE;
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            }
            case FILLWATER: {
                fillWater(input);
                this.state = States.FILLMILK;
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            }
            case FILLMILK: {
                fillMilk(input);
                this.state = States.FILLBEANS;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            }
            case FILLBEANS: {
                fillBeans(input);
                this.state = States.FILLGOBELETS;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            }
            case FILLGOBELETS: {
                fillGobelets(input);
                this.state = States.IDLE;
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            }
        }

    }

    private final int[][] recepies = {{250, 0, 16, 4}, {350, 75, 20, 7}, {200, 100, 12, 6}};

    private int waterStock;
    private int milkStock;
    private int beansStock;
    private int gobeletsStock;

    private int moneyStock;

    private States state;

    public CoffeeMachine(int waterStock, int milkStock, int beansStock, int gobeletsStock, int moneyStock) {
        this.waterStock = waterStock;
        this.milkStock = milkStock;
        this.beansStock = beansStock;
        this.gobeletsStock = gobeletsStock;
        this.moneyStock = moneyStock;
        this.state = States.IDLE;
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(this.waterStock + " of water");
        System.out.println(this.milkStock + " of milk");
        System.out.println(this.beansStock + " of coffee beans");
        System.out.println(this.gobeletsStock + " of disposable cups");
        System.out.println("$" + this.moneyStock + " of money");
        System.out.println();
    }

    private void fillWater(String input) {
        this.waterStock += Integer.parseInt(input);
    }

    private void fillMilk(String input) {
        this.milkStock += Integer.parseInt(input);
    }

    private void fillBeans(String input) {
        this.beansStock += Integer.parseInt(input);
    }

    private void fillGobelets(String input) {
        this.gobeletsStock += Integer.parseInt(input);
    }

    private void take() {
        System.out.println("I gave you $" + this.moneyStock);
        this.moneyStock = 0;
    }

    private void buy(String choiceInput) {
        if (choiceInput.equals("back")) {
            return;
        }
        int choice = Integer.parseInt(choiceInput);
        if (this.waterStock > recepies[choice - 1][0]) {
            this.waterStock -= recepies[choice - 1][0];
        } else {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (this.milkStock >= recepies[choice - 1][1]) {
            this.milkStock -= recepies[choice - 1][1];
        } else {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (this.beansStock >= recepies[choice - 1][2]) {
            this.beansStock -= recepies[choice - 1][2];
        } else {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }

        if (this.gobeletsStock >= 1) {
            this.gobeletsStock--;
        } else {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        this.moneyStock += recepies[choice - 1][3];

        System.out.println("I have enough resources, making you a coffee!");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CoffeeMachine cm = new CoffeeMachine(400, 540, 120, 9, 550);

        String action;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        do {
            action = sc.next();
            cm.userInput(action);
        } while (cm.state != States.QUIT);

        sc.close();
    }
}
