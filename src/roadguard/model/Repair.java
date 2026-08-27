package roadguard.model;

public class Repair {

    private int repairId;
    private String location;
    private String description;
    private String status;

    public Repair(String location,
                  String description,
                  String status) {

        this.location = location;
        this.description = description;
        this.status = status;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}