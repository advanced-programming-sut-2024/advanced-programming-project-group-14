package model;

import controller.GameMenuController;

public class Weather extends Card implements Actionable {


    public Weather(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Weather weather = (Weather) items[0];

        Player player1 = GameMenuController.currentPlayer;
        Player player2 = GameMenuController.opponentPlayer;

        switch (weather.getName()) {
            case "BitingFrost":
                changePowerToOne(player1.getCloseCombat(), player2.getCloseCombat());
                break;
            case "Impenetrablefog":
                changePowerToOne(player1.getRangedCombat(), player2.getRangedCombat());
                break;
            case "TorrentialRain":
                changePowerToOne(player1.getSiege(), player2.getSiege());
                break;
            case "SkelligeStorm":
                changePowerToOne(player1.getRangedCombat(), player2.getRangedCombat());
                changePowerToOne(player1.getSiege(), player2.getSiege());
                break;
            case "ClearWeather":
                GameMenuController.currentGameTable.setWeather(null);
                break;

        }
    }

    private static void changePowerToOne(Row player1, Row player2) {
        for (Card card : player1.getCards()) {
            if (!card.isHero())
                card.setPower(1);
        }
        for (Card card : player2.getCards()) {
            if (!card.isHero())
                card.setPower(1);
        }
    }
}
