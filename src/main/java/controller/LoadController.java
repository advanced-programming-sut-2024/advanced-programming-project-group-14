package controller;

import model.Card;
import model.Commander;
import model.Faction;
import model.Weather;
import model.abilities.*;

public class LoadController {

    public static void loadAll() {
        loadFactions();

        loadCommanders();

        loadWeatherCards();

        loadBerserkerCards();
        loadCommandersHornCards();
        loadDecoyCards();
        loadMardroemeCards();
        loadMedicCards();
        loadMoralBoostCards();
        loadMusterCards();
        loadScorchCards();
        loadSpyCards();
        loadTightBondCards();
        loadTransformersCards();

        loadNoActionSkelligeCards();
        loadNoActionScoiataellCards();
        loadNoActionNorthenCards();
        loadNoActionNilfgaardianCards();
        loadNoActionMonstersCards();
    }

    private static void loadFactions() {
        new Faction("Monsters");
        new Faction("Nilfgaard");
        new Faction("Northern Realms");
        new Faction("Scoia’tael");
        new Faction("Skellige");

    }

    private static void loadCommanders() {
        new Commander("Northern Realms", "The Siegemaster");
        new Commander("Northern Realms", "The Steel-Forged");
        new Commander("Northern Realms", "King of Temeria");
        new Commander("Northern Realms", "Lord Commander of the North");
        new Commander("Northern Realms", "Son of Medell");
        new Commander("Nilfgaard", "The White Flame");
        new Commander("Nilfgaard", "His Imperial Majesty");
        new Commander("Nilfgaard", "Emperor of Nilfgaard");
        new Commander("Nilfgaard", "The Relentless");
        new Commander("Nilfgaard", "Invader of the North");
        new Commander("Monsters", "Bringer of Death");
        new Commander("Monsters", "King of the wild Hunt");
        new Commander("Monsters", "Destroyer of Worlds");
        new Commander("Monsters", "Commander of the Red Riders");
        new Commander("Monsters", "The Treacherous");
        new Commander("Scoia’tael", "Queen of Dol Blathanna");
        new Commander("Scoia’tael", "The Beautiful");
        new Commander("Scoia’tael", "Daisy of the Valley");
        new Commander("Scoia’tael", "Pureblood Elf");
        new Commander("Scoia’tael", "Hope of the Aen Seidhe");
        new Commander("Skellige", "Crach an Craite");
        new Commander("Skellige", "King Bran");
    }

    private static void loadWeatherCards() {
        for (int i = 0; i < 3; i++) {
            new Weather("Biting Frost", 0, 3, "", "Weather", "Monsters", false, "Sets the power of all close combat units of both sides to 1");
            new Weather("Impenetrable fog", 0, 3, "", "Weather", "Neutral", false, "Sets the power of all ranged units of both sides to 1");
            new Weather("Torrential Rain", 0, 3, "", "Weather", "Neutral", false, "Sets the power of all siege units of both sides to 1");
            new Weather("Skellige Storm", 0, 3, "", "Weather", "Neutral", false, "Sets the power of all siege and ranged units of both sides to 1");
            new Weather("Clear Weather", 0, 3, "", "Weather", "Neutral", false, "Cancel all the weather cards");
        }
    }

    private static void loadBerserkerCards() {
        for (int i = 0; i < 3; i++)
            new Berserker("Young Berserker", 2, 3, "Berserker", "Ranged Unit", "Skellige", false, "Transforms into Young Vidkaarl when Mardroeme is used.Young Vidkaarl has Tight Bond effect.");
        new Berserker("Berserker", 4, 1, "Berserker", "Close Combat Unit", "Skellige", false, "Transforms into Vidkaarl when Mardroeme is used.Vidkaarl has Morale Boost effect.");
    }

    private static void loadCommandersHornCards() {
        for (int i = 0; i < 3; i++)
            new CommandersHorn("Commander’s horn", 0, 3, "CommandersHorn", "Special", "Neutral", false, "Doubles the power of the cards in the row that was placed. Only one of this type can be played in a row");
        new CommandersHorn("Draig Bon-Dhu", 2, 1, "CommandersHorn", "Siege Unit", "Skellige", false, "");
        new CommandersHorn("Dandelion", 2, 1, "CommandersHorn", "Close Combat Unit", "Neutral", false, "Same power as the commander’s horn");
    }

    private static void loadDecoyCards() {
        for (int i = 0; i < 3; i++)
            new Decoy("Decoy", 0, 3, "Decoy", "Special", "Neutral", false, ""); //ToDo add description
    }

    private static void loadMardroemeCards() {
        for (int i = 0; i < 3; i++)
            new Mardroeme("Mardoeme", 0, 3, "Mardroeme", "Special", "Skellige", true, "This Spell card has Mardoeme Ability and can be placed in the commmander's Horn spot . it effects the row that it was placed in");
        new Mardroeme("Ermion", 8, 1, "Mardroeme", "Ranged Unit", "Skellige", true, "");
    }

    private static void loadMedicCards() {
        for (int i = 0; i < 3; i++)
            new Medic("Havekar Healer", 0, 3, "Medic", "Ranged Unit", "Scoia’tael", false, "");
        for (int i = 0; i < 2; i++)
            new Medic("Etolian Auxiliary Archers", 1, 2, "Medic", "Ranged Unit", "Nilfgaard", false, "");
        new Medic("Menno Coehoorn", 10, 1, "Medic", "Close Combat Unit", "Nilfgaard", true, "");
        new Medic("Birna Bran", 2, 1, "Medic", "Close Combat Unit", "Skellige", false, "");
        new Medic("Siege Technician", 0, 1, "Medic", "Siege Unit", "Nilfgaard", false, "");
        new Medic("Yennefer of Vengerberg", 7, 1, "Medic", "Ranged Unit", "Neutral", true, "");
        new Medic("Dun Banner Medic", 5, 1, "Medic", "Siege Unit", "Northern Realms", false, "");
        new Medic("Menno Coehorn", 10, 1, "Medic", "Close Combat Unit", "Nilfgaard", true, "");

    }

    private static void loadMoralBoostCards() {
        for (int i = 0; i < 3; i++)
            new MoralBoost("Kaedweni Siege Expert", 1, 3, "MoralBoost", "Siege Unit", "Northern Realms", false, "");
        new MoralBoost("Olgierd Von Everc", 6, 1, "MoralBoost", "Agile Unit", "Neutral", false, "");
        new MoralBoost("Olaf", 12, 1, "MoralBoost", "Agile Unit", "Skellige", false, "");
        new MoralBoost("Vidkaarl", 14, 0, "MoralBoost", "Close Combat Unit", "Skellige", false, "it can only be played when a berserker transform into it.");
    }

    private static void loadMusterCards() {
        for (int i = 0; i < 3; i++) {
            new Muster("Light Longship", 4, 3, "Muster", "Ranged Unit", "Skellige", false, "");
            new Muster("Elven Skirmisher", 2, 3, "Muster", "Ranged Unit", "Scoia’tael", false, "");
            new Muster("Dwarven Skirmisher", 3, 3, "Muster", "Close Combat Unit", "Scoia’tael", false, "");
            new Muster("Havekar Smuggler", 5, 3, "Muster", "Close Combat Unit", "Scoia’tael", false, "");
            new Muster("Gaunter O’DImm Darkness", 4, 3, "Muster", "Ranged Unit", "Neutral", false, "");
            new Muster("Arachas", 4, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
            new Muster("Ghoul", 1, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
            new Muster("Nekker", 2, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
        }
        new Muster("Cerys", 10, 1, "Muster", "Close Combat Unit", "Skellige", true, "Its Muster effect will summon Shield Maiden cards and other musters.");
        new Muster("Crone: Brewess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Crone: Weavess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Crone: Whispess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Katakan", 5, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Bruxa", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Ekimmara", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Fleder", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Garkain", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Arachas Behemoth", 6, 1, "Muster", "Siege Unit", "Monsters", false, "");
        new Muster("Gaunter O’Dimm", 2, 1, "Muster", "Siege Unit", "Neutral", false, "");
    }

    private static void loadScorchCards() {
        for (int i = 0; i < 3; i++)
            new Scorch("Scorch", 0, 3, "Scorch", "Weather", "Neutral", false, "Remove card(s) with the maximum power points in the field (ignores heroes)");
        new Scorch("Clan Dimun Pirate", 6, 1, "Scorch", "Ranged Unit", "Skellige", false, "kills opponent card(s) with most power(does not matter in which row in this card)");
        new Scorch("Schirru", 8, 1, "Scorch", "Siege Unit", "Scoia’tael", false, "kills the opponent's card(s) with most power in enemy's Siege combat row if the sum of powers of none-hero cards in this row is 10 or more");
        new Scorch("Toad", 7, 1, "Scorch", "Ranged Unit", "Monsters", false, "kills the opponent's card(s) with most power in enemy's Ranged combat row if the sum of powers of none-hero cards in this row is 10 or more");
        new Scorch("Villentretenmerth", 7, 1, "Scorch", "Close Combat Unit", "Neutral", false, "Works only on opponent’s close combat");
    }

    private static void loadSpyCards() {
        new Spy("Prince Stennis", 5, 1, "Spy", "Close Combat Unit", "Northern Realms", false, "");
        new Spy("Sigismund Dijkstra", 4, 1, "Spy", "Close Combat Unit", "Northern Realms", false, "");
        new Spy("Thaler", 1, 1, "Spy", "Siege Unit", "Northern Realms", false, "");
        new Spy("Stefan Skellen", 9, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("Shilard Fitz-Oesterlen", 7, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("Vattier de Rideaux", 4, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("Mysterious Elf", 0, 1, "Spy", "Close Combat Unit", "Neutral", true, "");
    }

    private static void loadTightBondCards() {
        for (int i = 0; i < 4; i++) {
            new TightBond("Impera Brigade Guard", 3, 4, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
            new TightBond("Poor Infantry", 1, 4, "TightBond", "Close Combat Unit", "Northern Realms", false, "");
        }
        for (int i = 0; i < 3; i++) {
            new TightBond("Clan An Craite", 6, 3, "TightBond", "Close Combat Unit", "Skellige", false, "");
            new TightBond("Clan Drummond Shieldmaiden", 4, 3, "TightBond", "Close Combat Unit", "Skellige", false, "Can be Mustered by Cerys.");
            new TightBond("Blue Stripes Commando", 4, 3, "TightBond", "Close Combat Unit", "Northern Realms", false, "");
            new TightBond("Dragon Hunter", 5, 3, "TightBond", "Ranged Unit", "Northern Realms", false, "");
            new TightBond("War Longship", 6, 3, "TightBond", "Siege Unit", "Skellige", false, "");
            new TightBond("Nausicaa Cavalry Rider", 2, 3, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
        }
        for (int i = 0; i < 2; i++) {
            new TightBond("Catapult", 8, 2, "TightBond", "Siege Unit", "Northern Realms", false, "");
            new TightBond("Young Emissary", 5, 2, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
        }
        new TightBond("Young Vidkaarl", 8, 0, "TightBond", "Ranged Unit", "Skellige", false, "it can only be played when a young berserker transform into it.");
    }

    private static void loadTransformersCards() {
        new Transformers("Kambi", 0, 1, "Transformers", "Close Combat Unit", "Skellige", true, "Turns into a card with a power of 11 after one round");
        new Transformers("Cow", 0, 1, "Transformers", "Ranged Unit", "Neutral", false, "Turns into a card with a power of 8 after one round");
    }

    private static void loadNoActionMonstersCards() {
        new Card("Draug", 10, 1, "", "Close Combat Unit", "Monsters", true, "");
        new Card("Imlerith", 10, 1, "", "Close Combat Unit", "Monsters", true, "");
        new Card("Leshen", 10, 1, "", "Close Combat Unit", "Monsters", true, "");
        new Card("Earth Elemental", 6, 1, "", "Siege Unit", "Monsters", false, "");
        new Card("Fiend", 6, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Fire Elemental", 6, 1, "", "Siege Unit", "Monsters", false, "");
        new Card("Forktail", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Frightener", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Forktail", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Grave Hag", 5, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Griffin", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Ice Giant", 5, 1, "", "Siege Unit", "Monsters", false, "");
        new Card("Plague Maiden", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Werewolf", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Botchling", 4, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Celaeno Harpy", 2, 1, "", "Agile", "Monsters", false, "");
        new Card("Cockatrice", 2, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Endrega", 2, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Foglet", 2, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Gargoyle", 2, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Harpy", 2, 1, "", "Agile", "Monsters", false, "");
        new Card("Wyvern", 2, 1, "", "Ranged Unit", "Monsters", false, "");
    }

    private static void loadNoActionNilfgaardianCards() {
        for (int i = 0; i < 2; i++)
            new Card("Black Infantry Archer", 10, 2, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Cahir Mawr Dyffryn aep Ceallach", 6, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
        new Card("Puttkammer", 3, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Assire var Anahid", 6, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Tibor Eggebracht", 10, 1, "", "Ranged Unit", "Nilfgaard", true, "");
        new Card("Renuald aep Matsen", 5, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Fringilla Vigo", 6, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Rotten Mangonel", 3, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("Heavy Zerrikanian Fire Scorpion", 10, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("Zerrikanian Fire Scorpion", 5, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("Siege Engineer", 6, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("Morvran Voorhis", 10, 1, "", "Siege Unit", "Nilfgaard", true, "");
        new Card("Albrich", 2, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Cynthia", 4, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Letho of Gulet", 10, 1, "", "Close Combat Unit", "Nilfgaard", true, "");
        new Card("Morteisen", 3, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
        new Card("Morvran Voorhis", 10, 1, "", "Siege Unit", "Nilfgaard", true, "");
        new Card("Rainfarn ", 4, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
        new Card("Sweers", 2, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Vanhemar", 4, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Vreemde", 2, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
    }

    private static void loadNoActionNorthenCards() {
        for (int i = 0; i < 2; i++) {
            new Card("Ballista", 6, 2, "", "Siege Unit", "Northern Realms", false, "");
            new Card("Redanian Foot Soldier", 1, 2, "", "Close Combat Unit", "Northern Realms", false, "");
            new Card("Trebuchet", 6, 2, "", "Siege Unit", "Northern Realms", false, "");
        }
        new Card("Dethmold", 6, 1, "", "Ranged Unit", "Northern Realms", false, "");
        new Card("Esterad Thyssen", 10, 1, "", "Close Combat Unit", "Northern Realms", true, "");
        new Card("John Natalis", 10, 1, "", "Close Combat Unit", "Northern Realms", true, "");
        new Card("Keira Metz", 5, 1, "", "Ranged Unit", "Northern Realms", false, "");
        new Card("Philippa Eilhart", 10, 1, "", "Ranged Unit", "Northern Realms", true, "");
        new Card("Sabrina Glevissing", 4, 1, "", "Ranged Unit", "Northern Realms", false, "");
        new Card("Sheldon Skaggs", 4, 1, "", "Ranged Unit", "Northern Realms", false, "");
        new Card("Siege Tower", 6, 1, "", "Siege Unit", "Northern Realms", false, "");
        new Card("Siegfried of Denesle", 5, 1, "", "Close Combat Unit", "Northern Realms", false, "");
        new Card("Síle de Tansarville", 5, 1, "", "Ranged Unit", "Northern Realms", false, "");
        new Card("Vernon Roche", 10, 1, "", "Close Combat Unit", "Northern Realms", true, "");
        new Card("Ves", 5, 1, "", "Close Combat Unit", "Northern Realms", false, "");
        new Card("Yarpen Zirgrin", 2, 1, "", "Close Combat Unit", "Northern Realms", false, "");

    }

    private static void loadNoActionScoiataellCards() {
        for (int i = 0; i < 5; i++)
            new Card("Mahakaman Defender", 5, 5, "", "Close Combat Unit", "Scoia’tael", false, "");
        for (int i = 0; i < 3; i++)
            new Card("Dol Blathanna Scout", 6, 3, "", "Agile Unit", "Scoia’tael", false, "");
        for (int i = 0; i < 2; i++)
            new Card("Vrihedd Brigade Veteran", 5, 2, "", "Agile Unit", "Scoia’tael", false, "");
        new Card("Iorveth", 10, 1, "", "Ranged Unit", "Scoia’tael", true, "");
        new Card("Yaevinn", 6, 1, "", "Agile Unit", "Scoia’tael", false, "");
        new Card("Ciaran aep", 3, 1, "", "Agile Unit", "Scoia’tael", false, "");
        new Card("Dennis Cranmer", 6, 1, "", "Close Combat Unit", "Scoia’tael", false, "");
        new Card("Dol Blathanna Archer", 4, 1, "", "Ranged Unit", "Scoia’tael", false, "");
        new Card("Filavandrel", 6, 1, "", "Agile Unit", "Scoia’tael", false, "");
        new Card("Ida Emean aep", 6, 1, "", "Ranged Unit", "Scoia’tael", false, "");
        new Card("Riordain", 1, 1, "", "Ranged Unit", "Scoia’tael", false, "");
        new Card("Toruviel", 2, 1, "", "Ranged Unit", "Scoia’tael", false, "");
        new Card("Vrihedd Brigade Recruit", 4, 1, "", "Ranged Unit", "Scoia’tael", false, "");
        new Card("Seasenthessis", 10, 1, "", "Ranged Unit", "Scoia’tael", true, "");
        new Card("Barclay Els", 6, 1, "", "Agile Unit", "Scoia’tael", false, "");
        new Card("Eithne", 10, 1, "", "Ranged Unit", "Scoia’tael", true, "");

    }

    private static void loadNoActionSkelligeCards() {
        for (int i = 0; i < 3; i++)
            new Card("Clan Brokvar Archer", 6, 3, "", "Ranged Unit", "Skellige", false, "");
        new Card("Hjalmar", 10, 1, "", "Ranged Unit", "Skellige", true, "");
        new Card("Holger Blackhand", 4, 1, "", "Siege Unit", "Skellige", false, "");
        new Card("Svanrige", 4, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("Udalryk", 4, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("Donar an Hindar", 4, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("Blueboy Lugos", 6, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("Madman Lugos", 6, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("Clan Tordarroch Armorsmith", 4, 1, "", "Close Combat Unit", "Skellige", false, "");

    }
}