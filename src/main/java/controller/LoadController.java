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
        new Faction("Northern");
        new Faction("Scoiatael");
        new Faction("Skellige");

    }

    private static void loadCommanders() {
        new Commander("Northern", "TheSiegemaster");
        new Commander("Northern", "TheSteel-Forged");
        new Commander("Northern", "KingofTemeria");
        new Commander("Northern", "LordCommanderoftheNorth");
        new Commander("Northern", "SonofMedell");
        new Commander("Nilfgaard", "TheWhiteFlame");
        new Commander("Nilfgaard", "HisImperialMajesty");
        new Commander("Nilfgaard", "EmperorofNilfgaard");
        new Commander("Nilfgaard", "TheRelentless");
        new Commander("Nilfgaard", "InvaderoftheNorth");
        new Commander("Monsters", "BringerofDeath");
        new Commander("Monsters", "KingofthewildHunt");
        new Commander("Monsters", "DestroyerofWorlds");
        new Commander("Monsters", "CommanderoftheRedRiders");
        new Commander("Monsters", "TheTreacherous");
        new Commander("Scoiatael", "QueenofDolBlathanna");
        new Commander("Scoiatael", "TheBeautiful");
        new Commander("Scoiatael", "DaisyoftheValley");
        new Commander("Scoiatael", "PurebloodElf");
        new Commander("Scoiatael", "HopeoftheAenSeidhe");
        new Commander("Skellige", "CrachanCraite");
        new Commander("Skellige", "KingBran");
    }

    private static void loadWeatherCards() {
        for (int i = 0; i < 3; i++) {
            new Weather("BitingFrost", 0, 3, "", "Weather", "Monsters", false, "Sets the power of all close combat units of both sides to 1");
            new Weather("Impenetrablefog", 0, 3, "", "Weather", "Neutral", false, "Sets the power of all ranged units of both sides to 1");
            new Weather("TorrentialRain", 0, 3, "", "Weather", "Neutral", false, "Sets the power of all siege units of both sides to 1");
            new Weather("SkelligeStorm", 0, 3, "", "Weather", "Neutral", false, "Sets the power of all siege and ranged units of both sides to 1");
            new Weather("ClearWeather", 0, 3, "", "Weather", "Neutral", false, "Cancel all the weather cards");
        }
    }

    private static void loadBerserkerCards() {
        for (int i = 0; i < 3; i++)
            new Berserker("YoungBerserker", 2, 3, "Berserker", "Ranged Unit", "Skellige", false, "Transforms into Young Vidkaarl when Mardroeme is used.Young Vidkaarl has Tight Bond effect.");
        new Berserker("Berserker", 4, 1, "Berserker", "Close Combat Unit", "Skellige", false, "Transforms into Vidkaarl when Mardroeme is used.Vidkaarl has Morale Boost effect.");
    }

    private static void loadCommandersHornCards() {
        for (int i = 0; i < 3; i++)
            new CommandersHorn("CommandersHorn", 0, 3, "CommandersHorn", "Special", "Neutral", false, "Doubles the power of the cards in the row that was placed. Only one of this type can be played in a row");
        new CommandersHorn("DraigBon-Dhu", 2, 1, "CommandersHorn", "Siege Unit", "Skellige", false, "");
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
            new Medic("HavekarHealer", 0, 3, "Medic", "Ranged Unit", "Scoiatael", false, "");
        for (int i = 0; i < 2; i++)
            new Medic("EtolianAuxiliaryArchers", 1, 2, "Medic", "Ranged Unit", "Nilfgaard", false, "");
        new Medic("MennoCoehoorn", 10, 1, "Medic", "Close Combat Unit", "Nilfgaard", true, "");
        new Medic("BirnaBran", 2, 1, "Medic", "Close Combat Unit", "Skellige", false, "");
        new Medic("SiegeTechnician", 0, 1, "Medic", "Siege Unit", "Nilfgaard", false, "");
        new Medic("YenneferofVengerberg", 7, 1, "Medic", "Ranged Unit", "Neutral", true, "");
        new Medic("DunBannerMedic", 5, 1, "Medic", "Siege Unit", "Northern", false, "");
    ///////    new Medic("MennoCoehorn", 10, 1, "Medic", "Close Combat Unit", "Nilfgaard", true, "");

    }

    private static void loadMoralBoostCards() {
        for (int i = 0; i < 3; i++)
            new MoralBoost("KaedweniSiegeExpert", 1, 3, "MoralBoost", "Siege Unit", "Northern", false, "");
        new MoralBoost("OlgierdVonEverc", 6, 1, "MoralBoost", "Agile Unit", "Neutral", false, "");
        new MoralBoost("Olaf", 12, 1, "MoralBoost", "Agile Unit", "Skellige", false, "");
        new MoralBoost("Vidkaarl", 14, 0, "MoralBoost", "Close Combat Unit", "Skellige", false, "it can only be played when a berserker transform into it.");
    }

    private static void loadMusterCards() {
        for (int i = 0; i < 3; i++) {
            new Muster("LightLongship", 4, 3, "Muster", "Ranged Unit", "Skellige", false, "");
            new Muster("ElvenSkirmisher", 2, 3, "Muster", "Ranged Unit", "Scoiatael", false, "");
            new Muster("DwarvenSkirmisher", 3, 3, "Muster", "Close Combat Unit", "Scoiatael", false, "");
            new Muster("HavekarSmuggler", 5, 3, "Muster", "Close Combat Unit", "Scoiatael", false, "");
            new Muster("GaunterODImmDarkness", 4, 3, "Muster", "Ranged Unit", "Neutral", false, "");
            new Muster("Arachas", 4, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
            new Muster("Ghoul", 1, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
            new Muster("Nekker", 2, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
        }
        new Muster("Cerys", 10, 1, "Muster", "Close Combat Unit", "Skellige", true, "Its Muster effect will summon Shield Maiden cards and other musters.");
        new Muster("Crone-Brewess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Crone-Weavess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Crone-Whispess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire-Katakan", 5, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire-Bruxa", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire-Ekimmara", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire-Fleder", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire-Garkain", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
////////        new Muster("ArachasBehemoth", 6, 1, "Muster", "Siege Unit", "Monsters", false, "");
        new Muster("GaunterODimm", 2, 1, "Muster", "Siege Unit", "Neutral", false, "");
    }

    private static void loadScorchCards() {
        for (int i = 0; i < 3; i++)
            new Scorch("Scorch", 0, 3, "Scorch", "Weather", "Neutral", false, "Remove card(s) with the maximum power points in the field (ignores heroes)");
        new Scorch("ClanDimunPirate", 6, 1, "Scorch", "Ranged Unit", "Skellige", false, "kills opponent card(s) with most power(does not matter in which row in this card)");
        new Scorch("Schirru", 8, 1, "Scorch", "Siege Unit", "Scoiatael", false, "kills the opponent's card(s) with most power in enemy's Siege combat row if the sum of powers of none-hero cards in this row is 10 or more");
        new Scorch("Toad", 7, 1, "Scorch", "Ranged Unit", "Monsters", false, "kills the opponent's card(s) with most power in enemy's Ranged combat row if the sum of powers of none-hero cards in this row is 10 or more");
        new Scorch("Villentretenmerth", 7, 1, "Scorch", "Close Combat Unit", "Neutral", false, "Works only on opponent’s close combat");
    }

    private static void loadSpyCards() {
        new Spy("PrinceStennis", 5, 1, "Spy", "Close Combat Unit", "Northern", false, "");
        new Spy("SigismundDijkstra", 4, 1, "Spy", "Close Combat Unit", "Northern", false, "");
        new Spy("Thaler", 1, 1, "Spy", "Siege Unit", "Northern", false, "");
        new Spy("StefanSkellen", 9, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("ShilardFitz-Oesterlen", 7, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("VattierdeRideaux", 4, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("MysteriousElf", 0, 1, "Spy", "Close Combat Unit", "Neutral", true, "");
    }

    private static void loadTightBondCards() {
        for (int i = 0; i < 4; i++) {
            new TightBond("ImperaBrigadeGuard", 3, 4, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
            new TightBond("PoorInfantry", 1, 4, "TightBond", "Close Combat Unit", "Northern", false, "");
        }
        for (int i = 0; i < 3; i++) {
            new TightBond("ClanAnCraite", 6, 3, "TightBond", "Close Combat Unit", "Skellige", false, "");
            new TightBond("ClanDrummondShieldmaiden", 4, 3, "TightBond", "Close Combat Unit", "Skellige", false, "Can be Mustered by Cerys.");
            new TightBond("BlueStripesCommando", 4, 3, "TightBond", "Close Combat Unit", "Northern", false, "");
            new TightBond("DragonHunter", 5, 3, "TightBond", "Ranged Unit", "Northern", false, "");
            new TightBond("WarLongship", 6, 3, "TightBond", "Siege Unit", "Skellige", false, "");
            new TightBond("NausicaaCavalryRider", 2, 3, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
        }
        for (int i = 0; i < 2; i++) {
            new TightBond("Catapult", 8, 2, "TightBond", "Siege Unit", "Northern", false, "");
            new TightBond("YoungEmissary", 5, 2, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
        }
        new TightBond("YoungVidkaarl", 8, 0, "TightBond", "Ranged Unit", "Skellige", false, "it can only be played when a young berserker transform into it.");
    }

    private static void loadTransformersCards() {
        new Transformers("Kambi", 0, 1, "Transformers", "Close Combat Unit", "Skellige", true, "Turns into a card with a power of 11 after one round");
        new Transformers("Cow", 0, 1, "Transformers", "Ranged Unit", "Neutral", false, "Turns into a card with a power of 8 after one round");
    }

    private static void loadNoActionMonstersCards() {
        new Card("Draug", 10, 1, "", "Close Combat Unit", "Monsters", true, "");
        new Card("Imlerith", 10, 1, "", "Close Combat Unit", "Monsters", true, "");
        new Card("Leshen", 10, 1, "", "Close Combat Unit", "Monsters", true, "");
        new Card("EarthElemental", 6, 1, "", "Siege Unit", "Monsters", false, "");
        new Card("Fiend", 6, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("FireElemental", 6, 1, "", "Siege Unit", "Monsters", false, "");
        new Card("Forktail", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Frightener", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("GraveHag", 5, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Griffin", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("IceGiant", 5, 1, "", "Siege Unit", "Monsters", false, "");
        new Card("PlagueMaiden", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Werewolf", 5, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Botchling", 4, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("CelaenoHarpy", 2, 1, "", "Agile Unit", "Monsters", false, "");
        new Card("Cockatrice", 2, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Endrega", 2, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Foglet", 2, 1, "", "Close Combat Unit", "Monsters", false, "");
        new Card("Gargoyle", 2, 1, "", "Ranged Unit", "Monsters", false, "");
        new Card("Harpy", 2, 1, "", "Agile Unit", "Monsters", false, "");
        new Card("Wyvern", 2, 1, "", "Ranged Unit", "Monsters", false, "");
    }

    private static void loadNoActionNilfgaardianCards() {
        for (int i = 0; i < 2; i++)
            new Card("BlackInfantryArcher", 10, 2, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("CahirMawrDyffrynaepCeallach", 6, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
        new Card("Puttkammer", 3, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("AssirevarAnahid", 6, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("TiborEggebracht", 10, 1, "", "Ranged Unit", "Nilfgaard", true, "");
        new Card("RenualdaepMatsen", 5, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("FringillaVigo", 6, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("RottenMangonel", 3, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("HeavyZerrikanianFireScorpion", 10, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("ZerrikanianFireScorpion", 5, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("SiegeEngineer", 6, 1, "", "Siege Unit", "Nilfgaard", false, "");
        new Card("MorvranVoorhis", 10, 1, "", "Siege Unit", "Nilfgaard", true, "");
        new Card("Albrich", 2, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Cynthia", 4, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("LethoofGulet", 10, 1, "", "Close Combat Unit", "Nilfgaard", true, "");
        new Card("Morteisen", 3, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
        new Card("Rainfarn", 4, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
        new Card("Sweers", 2, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Vanhemar", 4, 1, "", "Ranged Unit", "Nilfgaard", false, "");
        new Card("Vreemde", 2, 1, "", "Close Combat Unit", "Nilfgaard", false, "");
    }

    private static void loadNoActionNorthenCards() {
        for (int i = 0; i < 2; i++) {
            new Card("Ballista", 6, 2, "", "Siege Unit", "Northern", false, "");
            new Card("RedanianFootSoldier", 1, 2, "", "Close Combat Unit", "Northern", false, "");
            new Card("Trebuchet", 6, 2, "", "Siege Unit", "Northern", false, "");
        }
        new Card("Dethmold", 6, 1, "", "Ranged Unit", "Northern", false, "");
        new Card("EsteradThyssen", 10, 1, "", "Close Combat Unit", "Northern", true, "");
        new Card("JohnNatalis", 10, 1, "", "Close Combat Unit", "Northern", true, "");
        new Card("KeiraMetz", 5, 1, "", "Ranged Unit", "Northern", false, "");
        new Card("PhilippaEilhart", 10, 1, "", "Ranged Unit", "Northern", true, "");
        new Card("SabrinaGlevissing", 4, 1, "", "Ranged Unit", "Northern", false, "");
        new Card("SheldonSkaggs", 4, 1, "", "Ranged Unit", "Northern", false, "");
        new Card("SiegeTower", 6, 1, "", "Siege Unit", "Northern", false, "");
        new Card("SiegfriedofDenesle", 5, 1, "", "Close Combat Unit", "Northern", false, "");
        new Card("SiledeTansarville", 5, 1, "", "Ranged Unit", "Northern", false, "");
        new Card("VernonRoche", 10, 1, "", "Close Combat Unit", "Northern", true, "");
        new Card("Ves", 5, 1, "", "Close Combat Unit", "Northern", false, "");
        new Card("YarpenZirgrin", 2, 1, "", "Close Combat Unit", "Northern", false, "");

    }

    private static void loadNoActionScoiataellCards() {
        for (int i = 0; i < 5; i++)
            new Card("MahakamanDefender", 5, 5, "", "Close Combat Unit", "Scoiatael", false, "");
        for (int i = 0; i < 3; i++)
            new Card("DolBlathannaScout", 6, 3, "", "Agile Unit", "Scoiatael", false, "");
        for (int i = 0; i < 2; i++)
            new Card("VriheddBrigadeVeteran", 5, 2, "", "Agile Unit", "Scoiatael", false, "");
        new Card("Iorveth", 10, 1, "", "Ranged Unit", "Scoiatael", true, "");
        new Card("Yaevinn", 6, 1, "", "Agile Unit", "Scoiatael", false, "");
        new Card("Ciaranaep", 3, 1, "", "Agile Unit", "Scoiatael", false, "");
        new Card("DennisCranmer", 6, 1, "", "Close Combat Unit", "Scoiatael", false, "");
        new Card("DolBlathannaArcher", 4, 1, "", "Ranged Unit", "Scoiatael", false, "");
        new Card("Filavandrel", 6, 1, "", "Agile Unit", "Scoiatael", false, "");
        new Card("IdaEmeanaep", 6, 1, "", "Ranged Unit", "Scoiatael", false, "");
        new Card("Riordain", 1, 1, "", "Ranged Unit", "Scoiatael", false, "");
        new Card("Toruviel", 2, 1, "", "Ranged Unit", "Scoiatael", false, "");
        new Card("VriheddBrigadeRecruit", 4, 1, "", "Ranged Unit", "Scoiatael", false, "");
        new Card("Seasenthessis", 10, 1, "", "Ranged Unit", "Scoiatael", true, "");
        new Card("BarclayEls", 6, 1, "", "Agile Unit", "Scoiatael", false, "");
        new Card("Eithne", 10, 1, "", "Ranged Unit", "Scoiatael", true, "");

    }

    private static void loadNoActionSkelligeCards() {
        for (int i = 0; i < 3; i++)
            new Card("ClanBrokvarArcher", 6, 3, "", "Ranged Unit", "Skellige", false, "");
        new Card("Hjalmar", 10, 1, "", "Ranged Unit", "Skellige", true, "");
        new Card("HolgerBlackhand", 4, 1, "", "Siege Unit", "Skellige", false, "");
        new Card("Svanrige", 4, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("Udalryk", 4, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("DonaranHindar", 4, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("BlueboyLugos", 6, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("MadmanLugos", 6, 1, "", "Close Combat Unit", "Skellige", false, "");
        new Card("ClanTordarrochArmorsmith", 4, 1, "", "Close Combat Unit", "Skellige", false, "");

    }
}