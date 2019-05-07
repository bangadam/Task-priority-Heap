import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static javax.swing.JOptionPane.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        showMessageDialog(null, "Selamat datang di aplikasi Task Priority");

        int maxSize = Integer.parseInt(showInputDialog(null, "Masukkan jumlah tugas :"));
        // add size heap
        Heap heap = new Heap(maxSize);

        while(true) {
            System.out.println("Pilih Menu Task Priority :");
            System.out.println("1. Tambah tugas");
            System.out.println("2. Edit tugas");
            System.out.println("3. Hapus tugas");
            System.out.println("4. Lihat tugas");
            System.out.println("5. Tambah jumlah tugas");

            System.out.print("Masukkan No. menu : ");
            char choice = getChar();
            switch (choice) {
                case '1':
                    if (heap.isFull()) {
                        System.out.println("jumlah tugas melebihi batas !!");
                    } else {
                        System.out.println("Masukkan nama tugas dan tanggal deadline");
                        System.out.print("Nama tugas : ");
                        String nama_tugas = getString();
                        System.out.print("Tanggal deadline (hari-bulan-tahun): ");
                        String tanggal = getString();
                        if (tanggal.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = format.parse(tanggal);
                            heap.insert(nama_tugas, date);
                            JOptionPane.showMessageDialog(null, "Tugas berhasil di tambahkan");
                        } else {
                            System.out.println("Format tanggal salah !!!");
                        }
                    }
                    break;
                case '2':
//                    heap.displayTable();
                    System.out.print("Pilih no. tugas untuk di ubah : ");
                    int no = getInt() - 1;
                    System.out.print("Nama tugas : ");
                    String nama_tugas_edit = getString();
                    System.out.print("Tanggal deadline (hari-bulan-tahun): ");
                    String tanggal = getString();
                    if (tanggal.matches("\\d{2}-\\d{2}-\\d{4}")) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = format.parse(tanggal);
                        heap.change(no, nama_tugas_edit, date);
                        JOptionPane.showMessageDialog(null, "Tugas berhasil di ubah");
                    } else {
                        System.out.println("Format tanggal salah !!!");
                    }
                    break;
                case '3':
                    System.out.print("Masukkan No. tugas yang dihapus : ");
                    int key = getInt();
                    heap.remove(key);
                    heap.displayHeap();
                    break;
                case '4':
                    heap.heapSort();
                    heap.displayHeap();
                    break;
                case '5':
                    System.out.print("Masukkan jumlah tugas : ");
                    int value = getInt();
                    heap.tambahJumlah(value);
                    break;
            }
        }
    }

    public static String getString() throws IOException {
        Scanner br = new Scanner(System.in);
        String s = br.next();
        return s;
    }

    public static int getInt() throws IOException {
        Scanner input = new Scanner(System.in);
        int d = input.nextInt();
        return d;
    }

    public static char getChar() throws IOException {
        String s = getString().toLowerCase();
        return s.charAt(0);
    }

}