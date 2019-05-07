import java.util.Date;

public class Node {
    private String task;
    private Date tanggal;

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
