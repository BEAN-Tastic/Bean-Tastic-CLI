package com.beantastic.api;

import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.player.PlayerClass;
import com.beantastic.stats.StatBlock;

import java.util.List;

public class ApitOffline implements Apit {

    @Override
    public List<PlayerClass> getClasses() {
        return List.of(
                new PlayerClass("Green Bean Warrior", "A sturdy bean skilled in close combat, wielding the might of beans with brute force.", new StatBlock(100,73,41,13)),
                new PlayerClass("Magic Coffee Bean", "A magical bean adept at harnessing the mystical energies of beans to cast powerful spells.", new StatBlock(55,24,67,84)),
                new PlayerClass("Sneaky Black Bean", "A nimble bean with a knack for stealth and deception, striking enemies from the shadows.", new StatBlock(57,77,40,97)),
                new PlayerClass("Divine Lima Bean", "A benevolent bean with divine powers, healing allies and smiting foes with righteous fury.", new StatBlock(50,36,93,69)),
                new PlayerClass("Arrowhead Pinto Bean", "A skilled bean archer, raining down arrows upon enemies with deadly precision.", new StatBlock(84,15,75,27))
        );
    }

    @Override
    public List<ItemClass> getItems() {
        return List.of(
                new ItemClass("Mystic Carrot", "A mystical carrot that seems to be glowing green.", new StatBlock(15, -5, 0, 0), "Common"),
                new ItemClass("Enchanted Banana", "An enchanted banana covered in strange runes. It smells slightly off.", new StatBlock(0, 0, 15, -5), "Common"),
                new ItemClass("Sorcerer's Walnut", "A magical walnut that is very hard to crack.", new StatBlock(-5, 25, 0, 0), "Uncommon"),
                new ItemClass("Fairy Fig", "A sparkly fig that looks exceptionally tasty.", new StatBlock(0, 0, -5, 25), "Uncommon"),
                new ItemClass("Witch's Watermelon", "An exceptionally juicy and vibrant watermelon.", new StatBlock(15, 15, 15, 15), "Epic"),
                new ItemClass("Wizard's Strawberry", "An oddly coloured strawberry with multicoloured seeds.", new StatBlock(-5, -5, -5, -5), "Cursed"),
                new ItemClass("Enchanter's Eggplant", "A normal looking eggplant with the letters E.B. carved into its flesh.", new StatBlock(-15,0, 0, 0), "Cursed"),
                new ItemClass("Sorceress' Spinach", "A magical leaf with potent powers. Its consumption promises mystical insights and enchanting flavors.", new StatBlock(-10, -10, 20, 20), "Uncommon"),
                new ItemClass("Spellbound Apple", "A bewitched fruit with mystical allure.", new StatBlock(15, 15, -5, -5), "Uncommon")
        );
    }

    @Override
    public List<Enemy> getEnemies() {
        return List.of(
                new Enemy("Sorcerer Soup", new StatBlock(40, 25, 50, 50), "Easy", "The Sorcerer Soup hurls a fiery ladle at you.", "A mystical soup with potent magical properties."),
                new Enemy("Cursed Carrot", new StatBlock(100, 40, 75, 90), "Medium", "The Cursed Carrot slams its toxic roots into the ground.", "A carrot cursed by dark magic, capable of unleashing havoc."),
                new Enemy("Haunted Hamburger", new StatBlock(150, 90, 110, 60), "Hard", "The Haunted Hamburger whips you with one of its macon rashers.", "A haunted hamburger possessed by vengeful spirits."),
                new Enemy("Ghoulish Grapes", new StatBlock(75, 75, 10, 20), "Easy", "The Ghoulish Grapes deliver a bitter bite.", "Sinister grapes with an eerie glow, hungry for souls."),
                new Enemy("Sinister Sushi", new StatBlock(110, 60, 60, 50),  "Medium", "The Sinister Sushi unleashes a whirlwind of wasabi.", "A malevolent sushi roll infused with dark energy."),
                new Enemy("Menacing Muffin", new StatBlock(160, 75, 100, 70), "Hard", "The Menacing Muffin shoots you with one of it's raisins, yuck.", "A muffin with a menacing aura, tempting fate itself."),
                new Enemy("Wayward Waffle", new StatBlock(140, 80, 80, 120), "Hard", "The Wayward Waffle throws syrup everywhere", "A Waffle fueled by syrup that enhances all attributes."),
                new Enemy("Cursed Cauliflower", new StatBlock(105, 70, 105, 10), "Medium", "The Cursed Cauliflower beats you up with its might muscles", "A cauliflower cursed to wither its foes with dread."),
                new Enemy("Wicked Watermelon", new StatBlock(150, 90, 25, 250), "Hard", "The Wicked Watermelon squirts its juices in your eye.", "A watermelon imbued with wickedness, thirsting for chaos."),
                new Enemy("Spooky Spaghetti", new StatBlock(45, 75, 60, -25), "Easy", "The Spooky Spaghetti pokes you with its sharp head.", "Spaghetti possessed by spectral entities, haunting the kitchen.")
        );
    }
}
