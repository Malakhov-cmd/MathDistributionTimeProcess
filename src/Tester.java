public class Tester {
    private final int programIteration = 100;
    private final int columNumber = 8;

    private boolean broodForce(int digit) {
        int i = 2;
        while (i < Math.sqrt(digit) && (digit % i != 0)) {
            i++;
        }
        return i == Math.sqrt(digit);
    }

    private double[] bubbleSort(double[] num) {
        int j;
        boolean flag = true;
        double temp;

        while (flag) {
            flag = false;
            for (j = 0; j < num.length - 1; j++) {
                if (num[j] < num[j + 1]) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    flag = true;
                }
            }
        }
        return num;
    }

    private double[][] multiplyByMatrix(double[][] matrixFirst, double[][] matrixSecond) {
        int matrixFirstColLength = matrixFirst[0].length;
        int matrixSecondRowLength = matrixSecond.length;
        if (matrixFirstColLength != matrixSecondRowLength) return null;
        int resultMatrixRowLength = matrixFirst.length;
        int resultMatrixColLength = matrixSecond[0].length;
        double[][] resultMatrix = new double[resultMatrixRowLength][resultMatrixColLength];
        for (int i = 0; i < resultMatrixRowLength; i++) {
            for (int j = 0; j < resultMatrixColLength; j++) {
                for (int k = 0; k < matrixFirstColLength; k++) {
                    resultMatrix[i][j] += matrixFirst[i][k] * matrixSecond[k][j];
                }
            }
        }
        return resultMatrix;
    }

    private double[] inputArray() {
        double[] arrayToSort = new double[1000];

        for (int i = 0; i < 1000; i++) {
            arrayToSort[i] = Math.random();
        }

        return arrayToSort;
    }

    private double arrayMaxMatrix(double[] array, int startIndex) {
        double max = array[startIndex + 1];
        for (int i = startIndex + 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

    private double[][] inputMatrix() {
        double[][] matrix = new double[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                matrix[i][j] = Math.random();
            }
        }

        return matrix;
    }

    private double[] averageSorting(double[] array, double normaly) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] > normaly * 5) {
                array[i] = normaly;
            }
            if (array[i] == 0) {
                double[] result = new double[array.length - 1];
                System.arraycopy(array, 0, result, 0, i);
                System.arraycopy(array, i + 1, result, i, array.length - i - 1);
                array = result;
                i--;
            }
        }
        return array;
    }

    private double arrayMax(double[] array) {
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

    private double arrayMin(double[] array) {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
        }
        return min;
    }

    public void analyzer() {
        int numberToBrood = 909091;

        double[] arrayToSort = inputArray();

        double[][] matrixFirst = inputMatrix();
        double[][] matrixSecond = inputMatrix();

        double[] timeDataToBroot = new double[programIteration];
        double[] timeDataToBubbleSort = new double[programIteration];
        double[] timeDataToMultiMatrix = new double[programIteration];
        double[] timeDataProgram = new double[programIteration];

        int startBubble = 0;
        boolean isFirstBubble = false;
        int startMatrix = 0;
        boolean isFirstMatrixMulti = false;

        for (int i = 0; i < programIteration; i++) {
            long timeStartCycleProg = System.nanoTime();

            long timeStartBroot = System.nanoTime();
            boolean isBroot = broodForce(numberToBrood);
            long timeEndBroot = System.nanoTime();

            timeDataToBroot[i] = (timeEndBroot - timeStartBroot)/1000000000.0;

            double chooser = Math.random();
            if (chooser < 0.5) {
                if (!isFirstBubble) {
                    isFirstBubble = true;
                    startBubble = i;
                }
                long timeStartBubble = System.nanoTime();
                bubbleSort(arrayToSort);
                long timeEndBubble = System.nanoTime();

                timeDataToBubbleSort[i] = (timeEndBubble - timeStartBubble)/1000000000.0;
            } else {
                if (!isFirstMatrixMulti) {
                    isFirstMatrixMulti = true;
                    startMatrix = i;
                }
                long timeStartMulti = System.nanoTime();
                multiplyByMatrix(matrixFirst, matrixSecond);
                long timeEndMulti = System.nanoTime();

                timeDataToMultiMatrix[i] = (timeEndMulti - timeStartMulti)/1000000000.0;
            }

            long timeEndCycleProg = System.nanoTime();
            timeDataProgram[i] = (timeEndCycleProg - timeStartCycleProg)/1000000000.0;
        }

        double averageBroot = 0;
        double averageBubbleSort = 0;
        double averageMultiMatrix = 0;
        double averageSingleCycleProg = 0;

        double countExistChooseBubble = 0;
        double countExistChooseMatrixMulti = 0;
        for (int i = 1; i < programIteration; i++) {
            averageBroot += timeDataToBroot[i];
            if (timeDataToBubbleSort[i] != 0 && startBubble != i) {
                averageBubbleSort += timeDataToBubbleSort[i];
                countExistChooseBubble++;
            }
            if (timeDataToMultiMatrix[i] != 0 && startMatrix != i) {
                averageMultiMatrix += timeDataToMultiMatrix[i];
                countExistChooseMatrixMulti++;
            }
            averageSingleCycleProg += timeDataProgram[i];
        }

        averageBroot /= programIteration;
        averageBubbleSort /= countExistChooseBubble;
        averageMultiMatrix /= countExistChooseMatrixMulti;
        averageSingleCycleProg /= programIteration;
        timeDataToBroot = averageSorting(timeDataToBroot, averageBroot);

        double averageTotalAlgoritms = averageBroot + averageBubbleSort + averageMultiMatrix;
        timeDataProgram = averageSorting(timeDataProgram, averageTotalAlgoritms);

        System.out.println("Average Broot: " + averageBroot);
        System.out.println("Average Bubble sort: " + averageBubbleSort);
        System.out.println("Average Multi matrix: " + averageMultiMatrix);
        System.out.println("Average cycle program: " + averageTotalAlgoritms);
        System.out.println();

        double minTimeBroot = arrayMin(timeDataToBroot);
        double maxTimeBroot = arrayMax(timeDataToBroot);

        System.out.println("Research time execution to Broot force");
        System.out.println("Time min: " + minTimeBroot);
        System.out.println("Time max: " + maxTimeBroot);
        System.out.println("Time: " + Math.max(maxTimeBroot - averageBroot, averageBroot - minTimeBroot));
        System.out.println("Distribution histogram: ");

        int[] matchIntervalBroot = new int[columNumber];
        double deltaTime = (arrayMax(timeDataToBroot) - arrayMin(timeDataToBroot)) / columNumber;
        double timeIntervalBroot = arrayMin(timeDataToBroot);
        double timeIntervalNextBroot = arrayMin(timeDataToBroot) + deltaTime;

        for (int i = 0; i < columNumber; i++) {
            for (int j = 1; j < programIteration; j++) {
                if (i == 0) {
                    if (timeDataToBroot[j] >= timeIntervalBroot && timeDataToBroot[j] <= timeIntervalNextBroot) {
                        matchIntervalBroot[i]++;
                    }
                } else {
                    if (timeDataToBroot[j] > timeIntervalBroot && timeDataToBroot[j] <= timeIntervalNextBroot + Double.MIN_VALUE) {
                        matchIntervalBroot[i]++;
                    }
                }
            }
            timeIntervalBroot = timeIntervalNextBroot;
            timeIntervalNextBroot += deltaTime;
        }

        timeIntervalBroot = arrayMin(timeDataToBroot);
        timeIntervalNextBroot = arrayMin(timeDataToBroot) + deltaTime;
        for (int i = 0; i < columNumber; i++) {
            int countMatches = matchIntervalBroot[i] / columNumber;
            double value = timeIntervalBroot;
            if (matchIntervalBroot[i] != 0 && countMatches == 0) {
                System.out.printf("%-2.10f%5s%n", value, "*");
            } else {
                if (countMatches > 0) {
                    System.out.printf("%-2.10f", value);
                    for (int j = 0; j < countMatches; j++) {
                        System.out.printf("%5s", "*");
                    }
                    System.out.println();
                }
            }
            timeIntervalBroot = timeIntervalNextBroot;
            timeIntervalNextBroot += deltaTime;
        }
        timeDataToBubbleSort = averageSorting(timeDataToBubbleSort, averageBubbleSort);
        System.out.println();

        double minTimeBubble = arrayMin(timeDataToBubbleSort);
        double maxTimeBubble = arrayMax(timeDataToBubbleSort);

        System.out.println("Research time execution to Bubble sort");
        System.out.println("Time min: " + minTimeBubble);
        System.out.println("Time max: " + maxTimeBubble);
        System.out.println("Time: " + Math.max(maxTimeBubble - averageBubbleSort, averageBubbleSort - minTimeBubble));
        System.out.println("Distribution histogram: ");

        int[] matchIntervalBubble = new int[columNumber];
        double deltaTimeBubble = (arrayMax(timeDataToBubbleSort) - arrayMin(timeDataToBubbleSort)) / columNumber;
        double timeIntervalBubble = arrayMin(timeDataToBubbleSort);
        double timeIntervalNextBubble = arrayMin(timeDataToBubbleSort) + deltaTimeBubble;

        for (int i = 0; i < columNumber; i++) {
            for (int j = 1; j < timeDataToBubbleSort.length; j++) {
                if (i == 0) {
                    if (timeDataToBubbleSort[j] >= timeIntervalBubble && timeDataToBubbleSort[j] <= timeIntervalNextBubble) {
                        matchIntervalBubble[i]++;

                    }
                } else {
                    if (timeDataToBubbleSort[j] > timeIntervalBubble && timeDataToBubbleSort[j] <= timeIntervalNextBubble + Double.MIN_VALUE) {
                        matchIntervalBubble[i]++;
                    }
                }
            }
            timeIntervalBubble = timeIntervalNextBubble;
            timeIntervalNextBubble += deltaTimeBubble;
        }

        timeIntervalBubble = arrayMin(timeDataToBubbleSort);
        timeIntervalNextBubble = arrayMin(timeDataToBubbleSort) + deltaTimeBubble;
        for (int i = 0; i < columNumber; i++) {
            int countMatches = matchIntervalBubble[i] / columNumber;
            double value = timeIntervalBubble;
            if (matchIntervalBubble[i] != 0 && countMatches == 0) {
                System.out.printf("%-2.10f%5s%n", value, "*");
            } else {
                if (countMatches > 0) {
                    System.out.printf("%-2.10f", value);
                    for (int j = 0; j < countMatches; j++) {
                        System.out.printf("%5s", "*");
                    }
                    System.out.println();
                }
            }
            timeIntervalBubble = timeIntervalNextBubble;
            timeIntervalNextBubble += deltaTimeBubble;
        }

        timeDataToMultiMatrix = averageSorting(timeDataToMultiMatrix, averageMultiMatrix);
        System.out.println();

        double minTimeMultiMatrix = arrayMin(timeDataToMultiMatrix);
        double maxTimeMultiMatrix = arrayMaxMatrix(timeDataToMultiMatrix, startMatrix);

        System.out.println("Research time execution to Matrix multi");
        System.out.println("Time min: " + minTimeMultiMatrix);
        System.out.println("Time max: " + maxTimeMultiMatrix);
        System.out.println("Time: " + Math.max(maxTimeMultiMatrix - averageMultiMatrix, averageMultiMatrix - minTimeMultiMatrix));
        System.out.println("Distribution histogram: ");

        int[] matchIntervalMatrix = new int[columNumber];
        double deltaTimeMatrix = (arrayMaxMatrix(timeDataToMultiMatrix, startMatrix) - arrayMin(timeDataToMultiMatrix)) / columNumber;
        double timeIntervalMatrix = arrayMin(timeDataToMultiMatrix);
        double timeIntervalNextMatrix = arrayMin(timeDataToMultiMatrix) + deltaTimeMatrix;

        for (int i = 0; i < columNumber; i++) {
            for (int j = 1; j < timeDataToMultiMatrix.length; j++) {
                if (i == 0) {
                    if (timeDataToMultiMatrix[j] >= timeIntervalMatrix && timeDataToMultiMatrix[j] <= timeIntervalNextMatrix) {
                        matchIntervalMatrix[i]++;

                    }
                } else {
                    if (timeDataToMultiMatrix[j] > timeIntervalMatrix && timeDataToMultiMatrix[j] <= timeIntervalNextMatrix + Double.MIN_VALUE) {
                        matchIntervalMatrix[i]++;
                    }
                }
            }
            timeIntervalMatrix = timeIntervalNextMatrix;
            timeIntervalNextMatrix += deltaTimeMatrix;
        }

        timeIntervalMatrix = arrayMin(timeDataToMultiMatrix);
        timeIntervalNextMatrix = arrayMin(timeDataToMultiMatrix) + deltaTimeMatrix;
        for (int i = 0; i < columNumber; i++) {
            int countMatches = matchIntervalMatrix[i] / columNumber;
            double value = timeIntervalMatrix;
            if (matchIntervalMatrix[i] != 0 && countMatches == 0) {
                System.out.printf("%-2.10f%5s%n", value, "*");
            } else {
                if (countMatches > 0) {
                    System.out.printf("%-2.10f", value);
                    for (int j = 0; j < countMatches; j++) {
                        System.out.printf("%5s", "*");
                    }
                    System.out.println();
                }
            }
            timeIntervalMatrix = timeIntervalNextMatrix;
            timeIntervalNextMatrix += deltaTimeMatrix;
        }
        System.out.println();

        double minTimeProgram = arrayMin(timeDataProgram);
        double maxTimeProgram = arrayMax(timeDataProgram);

        System.out.println("Research time execution to Program time");
        System.out.println("Time min: " + minTimeProgram);
        System.out.println("Time max: " + maxTimeProgram);
        System.out.println("Time: " + Math.max(maxTimeProgram - averageTotalAlgoritms, averageTotalAlgoritms - minTimeProgram));
        System.out.println("Distribution histogram: ");

        int[] matchIntervalProgram = new int[columNumber];
        double deltaTimeProgram = (arrayMax(timeDataProgram) - arrayMin(timeDataProgram)) / columNumber;
        double timeIntervalProgram = arrayMin(timeDataProgram);
        double timeIntervalNextProgram = arrayMin(timeDataProgram) + deltaTimeProgram;

        for (int i = 0; i < columNumber; i++) {
            for (int j = 2; j < programIteration; j++) {
                if (i == 0) {
                    if (timeDataProgram[j] >= timeIntervalProgram && timeDataProgram[j] <= timeIntervalNextProgram) {
                        matchIntervalProgram[i]++;

                    }
                } else {
                    if (timeDataProgram[j] > timeIntervalProgram && timeDataProgram[j] <= timeIntervalNextProgram + Double.MIN_VALUE) {
                        matchIntervalProgram[i]++;
                    }
                }
            }
            timeIntervalProgram = timeIntervalNextProgram;
            timeIntervalNextProgram += deltaTimeProgram;
        }

        timeIntervalProgram = arrayMin(timeDataProgram);
        timeIntervalNextProgram = arrayMin(timeDataProgram) + deltaTimeProgram;
        for (int i = 0; i < columNumber; i++) {
            int countMatches = matchIntervalProgram[i] / columNumber;
            double value = timeIntervalProgram;
            if (matchIntervalProgram[i] != 0 && countMatches == 0) {
                System.out.printf("%-2.10f%5s%n", value, "*");
            } else {
                if (countMatches > 0) {
                    System.out.printf("%-2.10f", value);
                    for (int j = 0; j < countMatches; j++) {
                        System.out.printf("%5s", "*");
                    }
                    System.out.println();
                }
            }
            timeIntervalProgram = timeIntervalNextProgram;
            timeIntervalNextProgram += deltaTimeProgram;
        }
    }
}
