/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Rizvi
 */
public class Population {

    private ArrayList<Integer> individualPopulation = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> allPopulation = new ArrayList<ArrayList<Integer>>();

    public Population(int populationSize, int perPopulationSize) {
        for (int j = 0; j < populationSize; j++) {
            Random rnd = new Random(j);
            individualPopulation = new ArrayList<Integer>();
            for (int i = 0; i < perPopulationSize; i++) {
                int slaveNo = rnd.nextInt() % 99;
                if(slaveNo < 0) {
                    slaveNo *= -1;
                }
                    
                if (individualPopulation.contains(slaveNo)) {
                    i--;
                    continue;
                }

                individualPopulation.add(slaveNo);
            }

            allPopulation.add(individualPopulation);
        }

    }

    public ArrayList<ArrayList<Integer>> getAllPopulation() {
        return allPopulation;
    }

}
