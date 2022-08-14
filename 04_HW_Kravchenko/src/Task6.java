import java.util.Arrays;

public class Task6 {
    //    6.	+Реализовать алгоритм сортировки слиянием

    public static void main(String[] args) {

        int[] array = {0, 0, -200,  0, 10, 0, 7};
        System.out.println("A given array : " + Arrays.toString(array));
        System.out.println("A sorted array : " + Arrays.toString(arraySort(array, 0, array.length - 1)));
    }


    public static int[] arraySort(int[] array, int leftPointer, int rightPointer) {

        if (leftPointer == rightPointer) {
            return new int[]{array[leftPointer]};
        }

        int mediumPointer = leftPointer + (rightPointer - leftPointer) / 2;

        int[] leftPart = arraySort(array, leftPointer, mediumPointer);
        int[] rightPart = arraySort(array, mediumPointer + 1, rightPointer);
        int[] mergedArray = arrayMerge(leftPart, rightPart);

        return mergedArray;
    }


    public static int[] arrayMerge(int[] leftArray, int[] rightArray) {

        int resultArrayLength = leftArray.length + rightArray.length;
        int[] resultArray = new int[resultArrayLength];
        int indexLeftArray = 0, indexRightArray = 0, indexResultArray = 0;


            while (indexLeftArray < leftArray.length  || indexRightArray < rightArray.length ) {
                if (indexLeftArray == leftArray.length) {
                    resultArray[indexResultArray] = rightArray[indexRightArray++];
                }
                else if (indexRightArray == rightArray.length) {
                    resultArray[indexResultArray] = leftArray[indexLeftArray++];
                }
                else {
                    resultArray[indexResultArray] = rightArray[indexRightArray] < leftArray[indexLeftArray] ?
                                                    rightArray[indexRightArray++] :
                                                    leftArray[indexLeftArray++];
                }
                indexResultArray++;
            }
        return resultArray;
    }
}
