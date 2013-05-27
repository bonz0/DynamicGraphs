package utils;

import graph.Region;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.BitSet;

public class Utils {
	
	public static int[][] getIntArray(String inputString) {
		String[] singleLines = inputString.split("\n");
		int[][] outputArray = new int[singleLines.length][3];
		for(int iii = 0; iii < singleLines.length; iii++) {
			String[] singleLine = singleLines[iii].split("\t");
			outputArray[iii][0] = Integer.parseInt(singleLine[0]);
			outputArray[iii][1] = Integer.parseInt(singleLine[1]);
			outputArray[iii][2] = Integer.parseInt(singleLine[2]);
		}
		return outputArray;
	}
	
	public static int getHammingSimilarity(BitSet set1, BitSet set2) {
		int count = 0;
		for(int iii = 0; iii < set1.size(); iii++) {
			if(set1.get(iii) == set2.get(iii)) {
				System.out.println(count);
				count++;
			}
		}
		return count;
	}
	
	public static String readInputFile(String inputFile) {
		int count = 0;
		BufferedReader br = null;
		String fileText = "";
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				System.out.println(++count);
				fileText += (currentLine + "\n");
			}
		} catch(IOException e) {
			System.out.println("File not found: " + inputFile);
			System.exit(-1);
		} finally {
			try {
				if(br != null)	br.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return fileText;
	}
	
	public static ArrayList<int[]> readParseInputFile(String inputFile) {
		ArrayList<int[]> outputArray = new ArrayList<int[]>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] temp = currentLine.split("\t");
				int[] tempInt = new int[temp.length];
				tempInt[0] = Integer.parseInt(temp[0]);
				tempInt[1] = Integer.parseInt(temp[1]);
				tempInt[2] = Integer.parseInt(temp[2]);
				outputArray.add(tempInt);
			}
		} catch(IOException e) {
			System.out.println("File not found: " + inputFile);
			System.exit(-1);
		} finally {
			try {
				if(br != null)	br.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return outputArray;
	}
	
	public static void writeOutputFile(String fileName, String fileContents) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(fileContents);
		} catch (IOException e) {
			System.out.println("Unable to write to file: " + fileName + "!");
			e.printStackTrace();
			System.exit(-1);
		} finally {
			try {
				if(bw != null) bw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writeOutputFile(String fileName, List<List<Region>> fileContents) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			for(List<Region> tempList: fileContents) {
				for(Region tempRegion: tempList) {
					if(tempRegion.nodes.size() > 1)
					bw.write(tempRegion.toString() + "\n");
				}
				bw.write("-------\n");
			}
		} catch (IOException e) {
			System.out.println("Unable to write to file: " + fileName + "!");
			e.printStackTrace();
			System.exit(-1);
		} finally {
			try {
				if(bw != null) bw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getNumberOfNodes(List<int[]> inputArray) {
		int maxNodeNumber = Integer.MIN_VALUE;
		for(int iii = 0; iii < inputArray.size(); iii++) {
			int maxNode = Math.max(inputArray.get(iii)[0], inputArray.get(iii)[1]);
			if(maxNode > maxNodeNumber) {
				maxNodeNumber = maxNode;
			}
		}
		return maxNodeNumber + 1;
	}
	
	public static int getNumberOfNodes(int[][] inputArray) {
		int maxNodeNumber = Integer.MIN_VALUE;
		for(int iii = 0; iii < inputArray.length; iii++) {
			int maxNode = Math.max(inputArray[iii][0], inputArray[iii][1]);
			if(maxNode > maxNodeNumber) {
				maxNodeNumber = maxNode;
			}
		}
		return maxNodeNumber + 1;
	}
	
	public static void printRegionsList(ArrayList<ArrayList<Region>> regions) {
		for(int iii = 0; iii < regions.size(); iii++) {
			ArrayList<Region> currentTimeRegions = regions.get(iii);
			for(int jjj = 0; jjj < currentTimeRegions.size(); jjj++) {
				System.out.println(currentTimeRegions.get(jjj));
			}
		}
	}
}
