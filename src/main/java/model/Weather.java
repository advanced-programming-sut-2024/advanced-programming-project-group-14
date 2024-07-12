package model;

import controller.GameMenuController;

public class Weather extends Card implements Actionable {


    public Weather(String name, int power, int capacity, String ability, String type, String factionName, boolean isHero, String description) {
        super(name, power, capacity, ability, type, factionName, isHero, description);
    }

    @Override
    public void doAction(Object[] items) {
        Weather weather = (Weather) items[1];

        Player player1 = GameMenuController.currentPlayer;
        Player player2 = GameMenuController.opponentPlayer;

        switch (weather.getName()) {
            case "BitingFrost":
                activeWeather(player1.getCloseCombat(), player2.getCloseCombat());
                break;
            case "Impenetrablefog":
                activeWeather(player1.getRangedCombat(), player2.getRangedCombat());
                break;
            case "TorrentialRain":
                activeWeather(player1.getSiege(), player2.getSiege());
                break;
            case "SkelligeStorm":
                activeWeather(player1.getRangedCombat(), player2.getRangedCombat());
                activeWeather(player1.getSiege(), player2.getSiege());
                break;
            case "ClearWeather":
                deActiveWeather(player1.getCloseCombat(), player2.getCloseCombat());
                deActiveWeather(player1.getRangedCombat(), player2.getRangedCombat());
                deActiveWeather(player1.getSiege(), player2.getSiege());
                GameMenuController.currentGameTable.getWeather().clear();
                break;

        }
    }

    private static void activeWeather(Row player1Row, Row player2Row) {
        player1Row.setWeatherAction(true);
        player2Row.setWeatherAction(true);
    }
    private static void deActiveWeather(Row player1Row, Row player2Row) {
        player1Row.setWeatherAction(false);
        player2Row.setWeatherAction(false);
    }
}
