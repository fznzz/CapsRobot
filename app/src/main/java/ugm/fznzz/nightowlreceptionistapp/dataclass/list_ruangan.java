package ugm.fznzz.nightowlreceptionistapp.dataclass;

public class list_ruangan {
    private String ruangan;
    private String lantai;

    public list_ruangan() {
    }

    public list_ruangan(String ruangan, String lantai) {
        this.ruangan = ruangan;
        this.lantai = lantai;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getLantai() {
        return lantai;
    }

    public void setLantai(String lantai) {
        this.lantai = lantai;
    }
}
