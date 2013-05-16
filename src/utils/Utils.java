package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	public static String readInputFile(String inputFile) {
		BufferedReader br = null;
		String fileText = "";
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String currentLine;
			while((currentLine = br.readLine()) != null) {
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
}
