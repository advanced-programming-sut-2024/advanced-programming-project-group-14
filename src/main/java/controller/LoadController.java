package controller;

import model.abilities.*;

import java.sql.Array;
import java.util.ArrayList;

public class LoadController {

    public void loadAllCards() {
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
    }

    private void loadBerserkerCards() {
        new Berserker("Berserker", 4, 1, "Berserker", "Close Combat Unit", "Skellige", false, "Transforms into Vidkaarl when Mardroeme is used.Vidkaarl has Morale Boost effect.");
        new Berserker("Young Berserker", 2, 3, "Berserker", "Ranged Unit", "Skellige", false, "Transforms into Young Vidkaarl when Mardroeme is used.Young Vidkaarl has Tight Bond effect.");
    }

    private void loadCommandersHornCards() {
        new CommandersHorn("Commander’s horn", 0, 3, "CommandersHorn", "Special", "Neutral", false, "Doubles the power of the cards in the row that was placed. Only one of this type can be played in a row");
        new CommandersHorn("Draig Bon-Dhu", 2, 1, "CommandersHorn", "Siege Unit", "Skellige", false, "");
        new CommandersHorn("Dandelion", 2, 1, "CommandersHorn", "Close Combat Unit", "Neutral", false, "Same power as the commander’s horn");
    }

    private void loadDecoyCards() {
        Decoy d =new Decoy("Decoy", 0, 3, "Decoy", "Special", "Neutral", false, ""); //ToDo add description
    }

    private void loadMardroemeCards() {
        new Mardroeme("Ermion", 8, 1, "Mardroeme", "Ranged Unit", "Skellige", true, "");
    }

    private void loadMedicCards() {
        new Medic("Birna Bran", 2, 1, "Medic", "Close Combat Unit", "Skellige", false, "");
        new Medic("Havekar Healer", 0, 3, "Medic", "Ranged Unit", "Scoia'tael", false, "");
        new Medic("Dun Banner Medic", 5, 1, "Medic", "Siege Unit", "Northern Realms", false, "");
        new Medic("Menno Coehorn", 10, 1, "Medic", "Close Combat Unit", "Nilfgaard", true, "");
        new Medic("Etolian Auxiliary Archers", 1, 2, "Medic", "Ranged Unit", "Nilfgaard", false, "");
        new Medic("Menno Coehoorn", 10, 1, "Medic", "Close Combat Unit", "Nilfgaard", true, "");
        new Medic("Siege Technician", 0, 1, "Medic", "Siege Unit", "Nilfgaard", false, "");
        new Medic("Yennefer of Vengerberg", 7, 1, "Medic", "Ranged Unit", "Neutral", true, "");

    }

    private void loadMoralBoostCards() {
        new MoralBoost("Vidkaarl",14,0, "MoralBoost", "Close Combat Unit","Skellige",false,"it can only be played when a berserker transform into it.");
        new MoralBoost("Olaf",12,1, "MoralBoost", "Agile Unit","Skellige",false,"");
        new MoralBoost("Kaedweni Siege Expert",1,3, "MoralBoost", "Siege Unit","Northern Realms",false,"");
        new MoralBoost("Olgierd Von Everc",6,1, "MoralBoost", "Agile Unit","Neutral",false,"");
    }

    private void loadMusterCards() {
        new Muster("Cerys", 10, 1, "Muster", "Close Combat Unit", "Skellige", true, "Its Muster effect will summon Shield Maiden cards and other musters.");
        new Muster("Light Longship", 4, 3, "Muster", "Ranged Unit", "Skellige", false, "");
        new Muster("Elven Skirmisher", 2, 3, "Muster", "Ranged Unit", "Scoia'tael", false, "");
        new Muster("Dwarven Skirmisher", 3, 3, "Muster", "Close Combat Unit", "Scoia'tael", false, "");
        new Muster("Havekar Smuggler", 5, 3, "Muster", "Close Combat Unit", "Scoia'tael", false, "");
        new Muster("Arachas Behemoth", 6, 1, "Muster", "Siege Unit", "Monsters", false, "");
        new Muster("Crone: Brewess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Crone: Weavess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Crone: Whispess", 6, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Katakan", 5, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Arachas", 4, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Bruxa", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Ekimmara", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Fleder", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Vampire: Garkain", 4, 1, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Nekker", 2, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Ghoul", 1, 3, "Muster", "Close Combat Unit", "Monsters", false, "");
        new Muster("Gaunter O’Dimm", 2, 1, "Muster", "Siege Unit", "Neutral", false, "");
        new Muster("Gaunter O’DImm Darkness", 4, 3, "Muster", "Ranged Unit", "Neutral", false, "");
    }

    private void loadScorchCards() {
        new Scorch("Clan Dimun Pirate", 6, 1, "Scorch", "Ranged Unit", "Skellige", false, "kills opponent card(s) with most power(does not matter in which row in this card)");
        new Scorch("Schirru", 8, 1, "Scorch", "Siege Unit", "Scoia'tael", false, "kills the opponent's card(s) with most power in enemy's Siege combat row if the sum of powers of none-hero cards in this row is 10 or more");
        new Scorch("Toad", 7, 1, "Scorch", "Ranged Unit", "Monsters", false, "kills the opponent's card(s) with most power in enemy's Ranged combat row if the sum of powers of none-hero cards in this row is 10 or more");
        new Scorch("Scorch", 0, 3, "Scorch", "Spell", "Neutral", false, "Remove card(s) with the maximum power points in the field (ignores heroes)");
        new Scorch("Villentretenmerth", 7, 1, "Scorch", "Close Combat Unit", "Neutral", false, "Works only on opponent’s close combat");
    }

    private void loadSpyCards() {
        new Spy("Prince Stennis", 5, 1, "Spy", "Close Combat Unit", "Northern Realms", false, "");
        new Spy("Sigismund Dijkstra", 4, 1, "Spy", "Close Combat Unit", "Northern Realms", false, "");
        new Spy("Thaler", 1, 1, "Spy", "Siege Unit", "Northern Realms", false, "");
        new Spy("Stefan Skellen", 9, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("Shilard Fitz-Oesterlen", 7, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("Vattier de Rideaux", 4, 1, "Spy", "Close Combat Unit", "Nilfgaard", false, "");
        new Spy("Mysterious Elf", 0, 1, "Spy", "Close Combat Unit", "Neutral", true, "");
    }

    private void loadTightBondCards() {
        new TightBond("Clan An Craite", 6, 3, "TightBond", "Close Combat Unit", "Skellige", false, "");
        new TightBond("Clan Drummond Shieldmaiden", 4, 3, "TightBond", "Close Combat Unit", "Skellige", false, "Can be Mustered by Cerys.");
        new TightBond("Young Vidkaarl", 8, 0, "TightBond", "Ranged Unit", "Skellige", false, "it can only be played when a young berserker transform into it.");
        new TightBond("War Longship", 6, 3, "TightBond", "Siege Unit", "Skellige", false, "");
        new TightBond("Blue Stripes Commando", 4, 3, "TightBond", "Close Combat Unit", "Northern Realms", false, "");
        new TightBond("Catapult", 8, 2, "TightBond", "Siege Unit", "Northern Realms", false, "");
        new TightBond("Dragon Hunter", 5, 3, "TightBond", "Ranged Unit", "Northern Realms", false, "");
        new TightBond("Poor Infantry", 1, 4, "TightBond", "Close Combat Unit", "Northern Realms", false, "");
        new TightBond("Impera Brigade Guard" , 3, 4, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
        new TightBond("Young Emissary", 5, 2, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
        new TightBond("Nausicaa Cavalry Rider", 2, 3, "TightBond", "Close Combat Unit", "Nilfgaard", false, "");
    }

    private void loadTransformersCards() {
        new Transformers("Kambi", 0, 1, "Transformers", "Close Combat Unit", "Skelliege", true, "Turns into a card with a power of 11 after one round");
        new Transformers("Cow", 0, 1, "Transformers", "Ranged Unit", "Neutral", false, "Turns into a card with a power of 8 after one round");
    }


}
