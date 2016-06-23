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
public class CrossoverAndMutation {

    public int crossoverFactor = 15;
    public int mutationFactor = 2;

    public ArrayList<ArrayList<Integer>> crossOver(ArrayList<ArrayList<Integer>> currentPopulation) {
        Random rnd = new Random(100);
        for (int i = 0; i < currentPopulation.size(); i += 2) {
            int randomValueForCrossOver = rnd.nextInt(16) + 20;

            for (int j = 0; j < randomValueForCrossOver; j++) {
                int temp = currentPopulation.get(i).get(j);
                currentPopulation.get(i).set(j, currentPopulation.get(i + 1).get(j));
                currentPopulation.get(i + 1).set(j, temp);
            }
        }

        return currentPopulation;
    }

    public ArrayList<ArrayList<Integer>> mutation(ArrayList<ArrayList<Integer>> currentPopulation) {
        Random rnd = new Random(101);
        for(int i = 0; i < currentPopulation.size(); i++){
            for(int j = 0; j < mutationFactor; j++){
                int randomSlave = rnd.nextInt(GeneticAlgorithm.chromosomeSize) + 0;
                int replacedBy = rnd.nextInt(99) + 0;
                currentPopulation.get(i).set(randomSlave, replacedBy);
            }
        }
        
        return currentPopulation;
    }
}
