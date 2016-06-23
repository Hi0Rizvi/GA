/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rizvi
 */
public class Fitness {

    private ArrayList<Double> fitnessValues = new ArrayList<Double>();

    public ArrayList<Double> getFitnessAll(ArrayList<ArrayList<Integer>> allPopulation) {
        for (int i = 0; i < allPopulation.size(); i++) {
            double totalFitness = 0.0;
            for (int j = 0; j < allPopulation.get(i).size(); j++) {
                int slaveNumber = allPopulation.get(i).get(j);
                String[] slaveProperties = GeneticAlgorithm.hashedDataMap.get(slaveNumber);
                totalFitness += Double.parseDouble(slaveProperties[1]) / 4.0 + Double.parseDouble(slaveProperties[2]) / 100.0
                        + Double.parseDouble(slaveProperties[3]) / 100.0 + Double.parseDouble(slaveProperties[4]) / 24.0
                        + Double.parseDouble(slaveProperties[5]) / 100.0;
            }

            fitnessValues.add(totalFitness);
        }
        return fitnessValues;
    }

    public static double getFitnessOfIndividual(ArrayList<Integer> individual) {
        double fitness = 0.0;
        for (int i = 0; i < individual.size(); i++) {
            int slaveNumber = individual.get(i);
            String[] slaveProperties = GeneticAlgorithm.hashedDataMap.get(slaveNumber);
            fitness += Double.parseDouble(slaveProperties[1]) / 4.0 + Double.parseDouble(slaveProperties[2]) / 100.0
                    + Double.parseDouble(slaveProperties[3]) / 100.0 + Double.parseDouble(slaveProperties[4]) / 24.0
                    + Double.parseDouble(slaveProperties[5]) / 100.0;
        }
        
        return fitness;
    }
}
