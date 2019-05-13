import java.text.ParseException;
import java.util.Date;

public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int size) {
        this.maxSize = size;
        this.currentSize = 0;
        this.heapArray = new Node[maxSize];
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
        int n = currentSize;

        buildMaxHeap(heapArray, n);

        for (int i = n - 1; i > 0; i--)
        {
            swap(heapArray, 0, i);
            int j = 0, index;
            do
            {
                index = (2 * j + 1);
                if (index < (i - 1) && heapArray[index].getTanggal().compareTo(heapArray[index + 1].getTanggal()) < 0)
                    index++;
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
        if (currentSize == 0) {
            System.out.println("daftar tugas masih kosong! silahkan tambah tugas terlebih dahulu.\n");
        } else {
            String leftAlignFormat = "| %-3s | %-11s | %-29s |%n";

            System.out.format("+-----+-------------+-------------------------------+%n");
            System.out.format("| No  |  Nama Tugas |        Tanggal Deadline       |%n");
            System.out.format("+-----+-------------+-------------------------------+%n");
            int no = 1;
            for (int i = 0; i < currentSize; i++) {
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


    public boolean isFull() {
        return (currentSize == maxSize);
    }

    public void tambahJumlah(int value) {
        maxSize += value;
    }

    public Node getDataByIndex(int index) {
        Node data = null;

        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i].getTask() == heapArray[index].getTask()) {
                data = heapArray[i];
                return data;
            }
        }

        return null;
    }
}
