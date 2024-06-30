package controller;

import model.abilities.*;

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
        new Berserker("Berserker", 4, 1, "Close Combat Unit", "Skellige", false, "Transforms into Vidkaarl when Mardroeme is used.Vidkaarl has Morale Boost effect.");
        new Berserker("Young Berserker", 2, 3, "Ranged Unit", "Skellige", false, "Transforms into Young Vidkaarl when Mardroeme is used.Young Vidkaarl has Tight Bond effect.");
    }

    private void loadCommandersHornCards() {
        new CommandersHorn("Commander’s horn", 0, 3, "Special", "Neutral", false, "Doubles the power of the cards in the row that was placed. Only one of this type can be played in a row");
        new CommandersHorn("Draig Bon-Dhu", 2, 1, "Siege Unit", "Skellige", false, "");
        new CommandersHorn("Dandelion", 2, 1, "Close Combat Unit", "Neutral", false, "Same power as the commander’s horn");
    }

    private void loadDecoyCards() {
        new CommandersHorn("Decoy", 0, 3, "Special", "Neutral", false, ""); //ToDo add description
    }

    private void loadMardroemeCards() {
        new Mardroeme("Ermion", 8, 1, "Ranged Unit", "Skellige", true, "");
    }

    private void loadMedicCards() {
        new Medic("Birna Bran", 2, 1, "Close Combat Unit", "Skellige", false, "");
        new Medic("Havekar Healer", 0, 3, "Ranged Unit", "Scoia'tael", false, "");
        new Medic("Dun Banner Medic", 5, 1, "Siege Unit", "Northern Realms", false, "");
        new Medic("Menno Coehorn", 10, 1, "Close Combat Unit", "Nilfgaard", true, "");
        new Medic("Etolian Auxiliary Archers", 1, 2, "Ranged Unit", "Nilfgaard", false, "");
        new Medic("Menno Coehoorn", 10, 1, "Close Combat Unit", "Nilfgaard", true, "");
        new Medic("Siege Technician", 0, 1, "Siege Unit", "Nilfgaard", false, "");
        new Medic("Yennefer of Vengerberg", 7, 1, "Ranged Unit", "Neutral", true, "");

    }

    private void loadMoralBoostCards() {
        new MoralBoost("Vidkaarl",14,0, "Close Combat Unit","Skellige",false,"it can only be played when a berserker transform into it.");
        new MoralBoost("Olaf",12,1, "Agile Unit","Skellige",false,"");
        new MoralBoost("Kaedweni Siege Expert",1,3, "Siege Unit","Northern Realms",false,"");
        new MoralBoost("Olgierd Von Everc",6,1, "Agile Unit","Neutral",false,"");
    }

    private void loadMusterCards() {
    }

    private void loadScorchCards() {
    }

    private void loadSpyCards() {
    }

    private void loadTightBondCards() {
    }

    private void loadTransformersCards() {
    }


}
