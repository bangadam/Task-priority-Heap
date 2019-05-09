import java.text.ParseException;
import java.util.Date;

public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;
    private int jml;

    public Heap(int size) {
        this.maxSize = size;
        this.currentSize = 0;
        this.jml = 0;
        this.heapArray = new Node[size];
    }

    public boolean insert(String task, Date tanggal) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node();
        newNode.setTask(task);
        newNode.setTanggal(tanggal);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        jml++;
        return true;
    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0 && heapArray[parent].getTanggal().compareTo(bottom.getTanggal()) < 0) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[leftChild].getTanggal().compareTo(heapArray[rightChild].getTanggal()) < 0 ) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            if (top.getTanggal().compareTo(heapArray[largerChild].getTanggal()) >= 0) {
                break;
            }
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public void displayHeap() {
        System.out.println("Heap Tree: ");
        System.out.println("");
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "............................................................................";
        System.out.println(dots + dots);
        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(' ');
                }
            }
            System.out.print((j+1) + ". "+heapArray[j].getTask());
            if (++j == currentSize) {
                break;
            }
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');
                }
            }
        }
        System.out.println("\n" + dots + dots);
    }

    public boolean change(int index, String task, Date tanggal) {
        if(index < 0 || index >= currentSize)
            return false;

        heapArray[index].setTask(task);
        heapArray[index].setTanggal(tanggal);

        if(heapArray[index].getTanggal().compareTo(tanggal) < 0)
            trickleUp(index);
        else
            trickleDown(index);

        return true;
    }

    public void buildMaxHeap(Node[] arr, int n)
    {
        for (int i = 1; i < n; i++)
        {
            // if child is bigger than parent
            if (arr[i].getTanggal().compareTo(arr[(i - 1) / 2].getTanggal()) > 0)
            {
                int j = i;

                // swap child and parent until
                // parent is smaller
                while (arr[j].getTanggal().compareTo(arr[(j - 1) / 2].getTanggal()) > 0)
                {
                    swap(arr, j, (j - 1) / 2);
                    j = (j - 1) / 2;
                }
            }
        }
    }

    public void heapSort()
    {
        int n = jml;

        buildMaxHeap(heapArray, n);

        for (int i = n - 1; i > 0; i--)
        {
            // swap value of first indexed
            // with last indexed
            swap(heapArray, 0, i);

            // maintaining heap property
            // after each swapping
            int j = 0, index;

            do
            {
                index = (2 * j + 1);

                // if left child is smaller than
                // right child point index variable
                // to right child
                if (index < (i - 1) && heapArray[index].getTanggal().compareTo(heapArray[index + 1].getTanggal()) < 0)
                    index++;

                // if parent is smaller than child
                // then swapping parent with child
                // having higher value
                if (index < i && heapArray[j].getTanggal().compareTo(heapArray[index].getTanggal()) < 0)
                    swap(heapArray, j, index);

                j = index;

            } while (index < i);
        }
    }

    public static void swap(Node[] a, int i, int j) {
        Node temp = a[i];
        a[i]=a[j];
        a[j] = temp;
    }


    public void displayTable() throws ParseException {
        if (jml == 0) {
            System.out.println("daftar tugas masih kosong! silahkan tambah tugas terlebih dahulu.\n");
        } else {
            String leftAlignFormat = "| %-3s | %-11s | %-29s |%n";

            System.out.format("+-----+-------------+-------------------------------+%n");
            System.out.format("| No  |  Nama Tugas |        Tanggal Deadline       |%n");
            System.out.format("+-----+-------------+-------------------------------+%n");
            int no = 1;
            for (int i = 0; i < jml; i++) {
                System.out.format(leftAlignFormat, (no++), heapArray[i].getTask(), heapArray[i].getTanggal());
            }
            System.out.format("+-----+-------------+-------------------------------+%n");
        }
    }
    public Node remove(int k) {
        Node root = heapArray[k];

        heapArray[k] = heapArray[--currentSize];
        trickleDown(k);
        return root;
    }

//    public Node remove( int k )
//    {
//        int parent;
//        Node r;             // Variable to hold deleted value
//
//        r = heapArray[k];             // Save return value
//
//        heapArray[k] = heapArray[currentSize-1];     // Replace deleted node with the right most leaf
//        // This fixes the "complete bin. tree" property
//
//        currentSize--;             // One less node in heap....
//
//        parent = k/2;
//
//      /* =======================================================
//	 Filter a[k] up or down depending on the result of:
//		a[k] <==> a[k's parent]
//         ======================================================= */
//        if ( k == 1 /* k is root */ || heapArray[parent].getTanggal().compareTo(heapArray[k].getTanggal()) < 0 )
//            trickleDown(k);  // Move the node a[k] DOWN the tree
//        else
//            trickleUp(k);    // Move the node a[k] UP the tree
//
//        return r;         // Return deleted value...
//    }

    public boolean isFull() {
        return (jml == maxSize);
    }

    public void tambahJumlah(int value) {
        maxSize += value;
    }

    public Node getDataByIndex(int index) {
        Node data = null;

        for (int i = 0; i < jml; i++) {
            if (heapArray[i].getTask() == heapArray[index].getTask()) {
                data = heapArray[i];
                return data;
            }
        }

        return null;
    }
}
