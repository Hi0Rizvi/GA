/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Rizvi
 */
public class GeneticAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static double selectionRateFromPrevious = 0.6;
    public static int populationSize = 20;
    public static int chromosomeSize = 50;
    public static HashMap<Integer, String[]> hashedDataMap = new HashMap<Integer, String[]>();

    private static void generateCsvFile(String sFileName) {
        try {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("Slave No");
            writer.append(',');
            writer.append("Computation power");
            writer.append(',');
            writer.append("Consumed energy");
            writer.append(',');
            writer.append("B/W");
            writer.append(',');
            writer.append("On time");
            writer.append(',');
            writer.append("Delay");
            writer.append('\n');

            Random rnd = new Random(100);
            for (int i = 0; i < 100; i++) {
                writer.append(String.valueOf(i));
                writer.append(',');
                writer.append(String.valueOf(rnd.nextDouble() * 4));
                writer.append(',');
                writer.append(String.valueOf(rnd.nextDouble() * 100));
                writer.append(',');
                writer.append(String.valueOf(rnd.nextDouble() * 100));
                writer.append(',');
                writer.append(String.valueOf(rnd.nextDouble() * 24));
                writer.append(',');
                writer.append(String.valueOf(rnd.nextDouble() * 100));
                writer.append('\n');
            }

            //generate whatever data you want
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        // generateCsvFile("c:\\sample.csv"); // This is to create the demo csv input file
        int i = 1;
        Population initialPopulation = new Population(populationSize, chromosomeSize);
        ArrayList<ArrayList<Integer>> allCreatedPopulation = initialPopulation.getAllPopulation();
        for (int count = 0; count < 20; count++) {
            String line = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader("c:\\sample.csv"));

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] lineElements = line.split(",");
                    if (!lineElements[0].matches("\\d+")) {
                        continue;
                    }

                    hashedDataMap.put(Integer.parseInt(lineElements[0]), lineElements);
                    String a = "";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            Fitness fitness = new Fitness();
            ArrayList<Double> fitnessValues = fitness.getFitnessAll(allCreatedPopulation);
            HashMap<Double, Integer> slavePositionBeforeSort = new HashMap<Double, Integer>();
            for (int s = 0; s < fitnessValues.size(); s++) {
                slavePositionBeforeSort.put(fitnessValues.get(s), s);
            }

            Collections.sort(fitnessValues, Collections.reverseOrder());
            double totalFitnessOfAll = 0.0;
            for (int s = 0; s < fitnessValues.size(); s++) {
                totalFitnessOfAll += fitnessValues.get(s);
            }

            System.out.println("Total fitness: " + totalFitnessOfAll + ", Highest: " + Collections.max(fitnessValues));
            // Selection part
            int selectedSample = (int) Math.round(fitnessValues.size() * selectionRateFromPrevious);
            int remainingSample = populationSize - selectedSample;
            ArrayList<ArrayList<Integer>> allSelectedPopulation = new ArrayList<ArrayList<Integer>>();
            for (int s = 0; s < selectedSample; s++) {
                allSelectedPopulation.add(allCreatedPopulation.get(slavePositionBeforeSort.get(fitnessValues.get(s))));
            }

            // Fill up the the population in a round robin approach
            for (int s = 0; s < remainingSample; s++) {
                allSelectedPopulation.add(allCreatedPopulation.get(slavePositionBeforeSort.get(fitnessValues.get(s))));
            }

            // Shuffle them
            Collections.shuffle(allSelectedPopulation);

            CrossoverAndMutation cm = new CrossoverAndMutation();
            allSelectedPopulation = cm.crossOver(allSelectedPopulation);
            allSelectedPopulation = cm.mutation(allSelectedPopulation);
            allCreatedPopulation = allSelectedPopulation;
            String a = "";
        }

    }

}
