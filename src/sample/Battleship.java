package sample;

import java.util.ArrayList;

public class Battleship {

    public ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();


    public Battleship() {
        generateGrid();
    }

    public void generateGrid() {
        for (int i = 0; i < 10; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                cells.get(i).add(new Cell());
                if (Math.random() < .5) cells.get(i).get(j).hit = true;
            }
        }
    }
}
