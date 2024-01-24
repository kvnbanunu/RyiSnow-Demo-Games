import java.util.Scanner;

public class Game {

    Scanner scan = new Scanner(System.in);
    Scanner enterScan = new Scanner(System.in);
    int playerHP;
    int monsterHP;
    String playerName;
    String playerWeapon;
    int choice;
    boolean silverRing = false;
    
    public static void main(String[] args) {
        
        Game game;
        game = new Game();
        
        game.playerSetup();
        game.townGate();
        
    }
    
    public void lineBreak() {
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    public void playerSetup() {
        
        playerHP = 10;
        monsterHP = 15;
        
        
        playerWeapon = "Dagger";
        
        System.out.println("Your HP: " + playerHP);
        System.out.println("Your Weapon: " + playerWeapon);
        
        System.out.println("Please enter your name: ");
        
        playerName = scan.nextLine();
        
        System.out.println("Hello " + playerName + ", let's start your adventure!");
        
    }
    
    public void townGate() {
        

        
        do {
            lineBreak();
            System.out.println("You are at the gate of the town.");
            System.out.println("A guard is standing inn front of you");
            System.out.println("What do you want to do?\n");
            System.out.println("1: Talk to the guard");
            System.out.println("2: Attack the guard");
            System.out.println("3: Leave");
            lineBreak();
            
            choice = scan.nextInt();
            
            switch(choice) {
                case 1:
                    
                    if (silverRing) {
                        ending();
                    } else {
                        System.out.println("Guard: Hello there stranger. So your name is " + playerName + "?\nSorry, but we cannot let strangers enter our town.");
                    System.out.println("\nPress ENTER to continue...");
                    enterScan.nextLine();
                    break;
                    }
                case 2:
                    playerHP -= 1;
                    System.out.println("Guard: Hey don't be stupid.\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)");
                    System.out.println("Your HP: " + playerHP);
                    System.out.println("\nPress ENTER to continue...");
                    enterScan.nextLine();
                    break;
                case 3:
                    System.out.println("");
                    crossRoad();
                    break;
                default:
                    System.out.println("Please pick a valid number.");
                    System.out.println("\nPress ENTER to continue...");
                    enterScan.nextLine();
                    break;
            }
        } while (choice != 3);

    }
    
    public void ending() {
        
        lineBreak();
        System.out.println("Guard: Oh you killed that goblin?! Great!");
        System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
        System.out.println("\n\nTHE END");
        lineBreak();
    }

    public void crossRoad() {
        lineBreak();
        System.out.println("You are at a crossroad. If you go south, you will go back to town.\n");
        System.out.println("1: Go north");
        System.out.println("2: Go east");
        System.out.println("3: Go west");
        System.out.println("4: Go south");
        lineBreak();

        choice = scan.nextInt();
        
        if (choice == 1) {
            north();
        } else if (choice == 2) {
            east();
        } else if (choice == 3) {
            west();
        } else if (choice == 4) {
            townGate();
        } else {
            System.out.println("Please pick a valid option");
            crossRoad();
        }

    }
    
    public void north() {
        
        lineBreak();
        System.out.println("There is a river. You drink the water and rest at the riverside.");
        System.out.println("Your HP is recovered.");
        playerHP += 1;
        System.out.println("Your HP: " + playerHP);
        System.out.println("\n1: Go back to the crossroad");
        lineBreak();
        
        choice = scan.nextInt();
        
        if (choice == 1) {
            crossRoad();
        } else {
            north();
        }
    }
    
    public void east() {
        
        lineBreak();
        System.out.println("You walked into a forest and found a Long Sword!");
        playerWeapon = "Long Sword";
        System.out.println("Your Weapon: " + playerWeapon);
        System.out.println("\n1: Go back to the crossroad");
        lineBreak();
        
        choice = scan.nextInt();
        
        if (choice == 1) {
            crossRoad();
        } else {
            east();
        }
    }
    
    public void west() {
        
        lineBreak();
        System.out.println("You encounter a goblin!");
        System.out.println("1: Fight");
        System.out.println("2: Run");
        lineBreak();
        
        choice = scan.nextInt();
        
        if (choice == 1) {
            fight();
        } else if (choice == 2) {
            crossRoad();
        } else {
            west();
        }
    }

    public void fight() {
        
        lineBreak();
        System.out.println("Your HP: " + playerHP);
        System.out.println("Monster HP: " + monsterHP);
        System.out.println("\n1: Attack");
        System.out.println("2: Run");
        lineBreak();
        
        choice = scan.nextInt();
        
        if (choice == 1) {
            attack();
        } else if (choice == 2) {
            crossRoad();
        } else {
            fight();
        }
        
    }

    public void attack() {
        int playerDamage;
        
        int weaponDamage;
        
        if (playerWeapon.equals("Knife")) {
            weaponDamage = 5;
        } else if (playerWeapon.equals("Long Sword")) {
            weaponDamage = 8;
        } else {
            weaponDamage = 1;
        }
        
        playerDamage = new java.util.Random().nextInt(weaponDamage);
        
        System.out.println("You attacked the monster and dealt " + playerDamage + " damage!");
        monsterHP -= playerDamage;
        System.out.println("Monster HP: " + monsterHP);
        
        if (monsterHP <= 0) {
            win();
        } else if (monsterHP > 0) {
            
            int monsterDamage;
            monsterDamage = new java.util.Random().nextInt(4);
            
            System.out.println("The monster attacked you for " + monsterDamage + " damage");
            
            playerHP -= monsterDamage;
            
            System.out.println("Player HP: " + playerHP);
            
            if (playerHP <= 0) {
                dead();
            } else {
                fight();
            }
        }
        
    }

    public void win() {
        
        lineBreak();
        System.out.println("You killed the monster!");
        System.out.println("The monster dropped a silver ring");
        System.out.println("You obtained a silver ring\n");
        silverRing = true;
        System.out.println("1: Go east");
        lineBreak();
        
        do {
            choice = scan.nextInt();
            
            if (choice == 1) {
                crossRoad();
            } else {
                lineBreak();
                System.out.println("1: Go east");
                lineBreak();
            }
        } while (choice != 1);
        
    }

    public void dead() {
        lineBreak();
        System.out.println("You are dead!!!");
        System.out.println("\nGAME OVER");
        lineBreak();
        
    }
    

}
