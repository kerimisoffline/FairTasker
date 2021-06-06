package app.fairTasker.gg.model;

public class Job {
    int id;
    String job_name;
    int hardness;

    public Job(String job_name, int hardness) {
        this.job_name = job_name;
        this.hardness = hardness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public String toString(){
        return "işlem: "+job_name+" | zorluk: "+String.valueOf(hardness);
    }
}
