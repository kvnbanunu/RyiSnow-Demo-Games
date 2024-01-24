package package01;

import package02.Monster_Dragon;
import package02.Monster_Goblin;
import package02.SuperMonster;
import package02.Weapon_Knife;
import package02.Weapon_LongSword;

public class Story {
    
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperMonster monster;
    
    boolean silverRing;

    public Story(Game g, UI userInterface, VisibilityManager vManager) {
        
        game = g;
        ui = userInterface;
        vm = vManager;
    }
    
    public void defaultSetup() {
        
        player.hp = 10;
        ui.hpLabelNumber.setText("" + player.hp);
        
        player.currentWeapon = new Weapon_Knife();
        ui.weaponLabelName.setText(player.currentWeapon.name);
        
        silverRing = false;
    }
    
    public void selectPosition(String nextPosition) {
        
        switch (nextPosition) {
        case "townGate":
            townGate();
            break;
        case "talkGuard":
            talkGuard();
            break;
        case "attackGuard":
            attackGuard();
            break;
        case "crossRoad":
            crossRoad();
            break;
        case "north":
            north();
            break;
        case "east":
            east();
            break;
        case "west":
            west();
            break;
        case "fight":
            fight();
            break;
        case "playerAttack":
            playerAttack();
            break;
        case "monsterAttack":
            monsterAttack();
            break;
        case "win":
            win();
            break;
        case "lose":
            lose();
            break;
        case "ending":
            ending();
            break;
        case "toTitle":
            toTitle();
            break;
        }
    }
    
    public void townGate() {
        ui.mainTextArea.setText("You are at the gate of the town.\nA guard is standing in front of you.\n\nWhat do you do?");
        ui.choice1.setText("Talk to the Guard");
        ui.choice2.setText("Attack the Guard");
        ui.choice3.setText("Leave");
        ui.choice4.setText("");
        
        game.nextPosition1 = "talkGuard";
        game.nextPosition2 = "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }
    
    public void talkGuard() {
        
        if (silverRing) {
            ending();
        } else {
            ui.mainTextArea.setText("Guard: Hello stranger. I have never seen your face.\\nI'm sorry but we cannot let a stranger into our town.");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");
            
            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        
        
    }
    
    public void attackGuard() {
        ui.mainTextArea.setText("Guard: Hey don't be stupid!\nThe guard fought back and hit you hard.\n(You receive 3 damage)");
        player.hp -= 3;
        ui.hpLabelNumber.setText("" + player.hp);
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "townGate";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void crossRoad() {
        ui.mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go back to the town.");
        ui.choice1.setText("Go north");
        ui.choice2.setText("Go east");
        ui.choice3.setText("Go south");
        ui.choice4.setText("Go west");
        
        game.nextPosition1 = "north";
        game.nextPosition2 = "east";
        game.nextPosition3 = "townGate";
        game.nextPosition4 = "west";
    }
    
    public void north() {
        ui.mainTextArea.setText("There is a river.\nYou drink the water and rest at the riverside.\n(Your HP is recovered by 2");
        player.hp += 2;
        ui.hpLabelNumber.setText("" + player.hp);
        ui.choice1.setText("Go south");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void east() {
        ui.mainTextArea.setText("You walked into a forest and found a weapon.\n\n(You obtained a Long Sword)");
        player.currentWeapon = new Weapon_LongSword();
        ui.weaponLabelName.setText(player.currentWeapon.name);
        
        ui.choice1.setText("Go west");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void west() {
        
        int i = new java.util.Random().nextInt(100) + 1;
        
        if (i < 90) {
            monster = new Monster_Goblin();
        } else {
            monster = new Monster_Dragon();
        }
        
        
        ui.mainTextArea.setText("You encounter a " + monster.name + "!");
        
        
        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "fight";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void fight() {
        
        ui.mainTextArea.setText(monster.name + " HP: " + monster.hp + "\n\nWhat do you do?");
        
        ui.choice1.setText("Attack");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void playerAttack() {
        
        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);
        
        ui.mainTextArea.setText("You attacked the " + monster.name + " and dealt " + playerDamage + " damage!");
        
        monster.hp -= playerDamage;
        
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        if (monster.hp > 0) {
            game.nextPosition1 = "monsterAttack";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            game.nextPosition1 = "win";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }
    
    public void monsterAttack() {
        
        int monsterDamage = new java.util.Random().nextInt(monster.attack);
        
        ui.mainTextArea.setText(monster.attackMessage + "\nYou received " + monsterDamage + " damage!");
        
        player.hp -= monsterDamage;
        
        ui.hpLabelNumber.setText("" + player.hp);
        
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        if (player.hp > 0) {
            game.nextPosition1 = "fight";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            game.nextPosition1 = "lose";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }
    
    public void win() {
        
        ui.mainTextArea.setText("You have defeated the monster!\nThe monster dropped a ring!\n\n(You have obtained a Silver Ring)");
        silverRing = true;
        
        ui.choice1.setText("Go east");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void lose() {
        
        ui.mainTextArea.setText("You are dead!\n\n<GAME OVER>");
        
        ui.choice1.setText("Go back to Title Screen");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        
        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void ending() {
        ui.mainTextArea.setText("Guard: Oh you killed that goblin?!\nThank you so much. You are a true hero!\nWelcome to our town!\n\n<THE END>");
        
        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }
    
    public void toTitle() {
        
        defaultSetup();
        vm.showTitleScreen();
    }
}
