import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

class TestCases {

    TestCases(int numberOfTests, int arraySize){
            for(int i=0;i<numberOfTests;i++) {
                int[] bubbleArray = createRandomArray(arraySize);

                int nanoSec = 1000000000;
                long timeStart = System.nanoTime();
                System.out.println("Bubble Start: " + timeStart);
                BubbleSort(bubbleArray);
                long timeEnd = System.nanoTime();
                System.out.println("Bubble Done: " + timeEnd);
                double bubbleTime = (double) (timeEnd - timeStart) / nanoSec;
                System.out.println(bubbleTime);

                System.out.println();

                timeStart = System.nanoTime();
                System.out.println("Merge Start: " + timeStart);
                MergeSort(bubbleArray);
                timeEnd = System.nanoTime();
                System.out.println("Merge Done: " + timeEnd);
                double mergeTime = (double)(timeEnd - timeStart) / nanoSec;
                System.out.println(mergeTime);

                System.out.println();
                try {
                    File file = new File("Comparisons.csv");
                    FileWriter fileWriter = new FileWriter(file.getName(),true);
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    if (file.createNewFile()) {
                        bw.write("Iteration,"+"Bubble Sort,"+"Merge Sort,\n");
                    }

                    bw.write((i+1)+","+bubbleTime+","+mergeTime+",\n");
                    bw.close();

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
    }

    //creates and returns an array of size from 0 to 1000
    private static int[] createRandomArray(int size){
        int[] array = new int[size];
        for(int i=0; i<size; i++){
            array[i]= (int) (Math.random() * 100000)+1;
        }

        return array;
    }


    private static void BubbleSort(int[] data){
        for(int i=0;i<data.length; i++){
           for(int j=0;j<data.length-1;j++){
               if (data[j] > data[j+1]) {
                   int temp = data[j];
                   data[j] = data[j+1];
                   data[j+1]=temp;
               }
           }
        }
    }

    public static void MergeSort(int [] data){
        MergeSort(data,0, data.length-1);
    }

    private static void MergeSort(int[] data, int first, int last){
        if (first < last) {
            int mid = (first+last)/2;

            MergeSort(data, first, mid);
            MergeSort(data, mid+1, last);

            Merge(data, first, mid, last);
        }
    }

    private static void Merge(int[] data, int first, int mid, int last) {
            int numLeft = mid - first + 1;
            int numRight = last - mid;

            int [] left = new int[numLeft];
            System.arraycopy(data,first,left,0, numLeft);

            int [] right = new int[numRight];
            System.arraycopy(data,mid+1,right, 0, numRight);

            int leftIndex =  0;
            int rightIndex = 0;
            int mergeIndex = first;

            while ((leftIndex < numLeft) && (rightIndex < numRight)){

                if (left[leftIndex] <= right[rightIndex]) {
                    data[mergeIndex] = left[leftIndex];
                    leftIndex++;
                }

                else {
                    data[mergeIndex] = right[rightIndex];
                    rightIndex++;
                }

                mergeIndex++;
            }

            while (leftIndex < numLeft) {
                data[mergeIndex] = left[leftIndex];
                leftIndex++;
                mergeIndex++;
            }

            while (rightIndex < numRight) {
                data[mergeIndex] = right[rightIndex];
                rightIndex++;
                mergeIndex++;
            }
    }
}
