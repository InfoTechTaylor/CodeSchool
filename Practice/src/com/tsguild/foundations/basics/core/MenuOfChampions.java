package com.tsguild.foundations.variables;

public class MenuOfChampions {
    public static void main(String[] args) {

        String ricoSlice = "Slice of Big Rico Pizza";
        String strawberryPie = "Invisible Strawberry Pie";
        String denverOmlet = "Denver Omlet";

        //menu prices
        double ricoSlicePrice = 500.00;
        double strawberryPiePrice = 2.00;
        double denverOmletPrice = 1.50;

        String menuArt = ".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo";

        System.out.println(menuArt);
        System.out.println();
        System.out.println("                    WELCOME TO RESTAURANT NIGHT VALE!");
        System.out.println("                            Today's Menu Is ...");
        System.out.println();
        System.out.println(menuArt);
        System.out.println();
        System.out.println("\t\t" + String.format("%-30s",ricoSlice) + String.format("%.2f", ricoSlicePrice));
        System.out.println("\t\t" + String.format("%-30s", strawberryPie) + String.format("%.2f", strawberryPiePrice));
        System.out.println("\t\t" + String.format("%-30s",denverOmlet) + String.format("%.2f", denverOmletPrice));
    }
}
