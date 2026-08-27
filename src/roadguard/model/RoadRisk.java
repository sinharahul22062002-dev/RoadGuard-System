package roadguard.model;

public class RoadRisk {

    private int riskId;
    private String location;
    private String roadCondition;
    private String trafficLevel;
    private int accidentCount;
    private String riskLevel;

    public RoadRisk(String location,
                    String roadCondition,
                    String trafficLevel,
                    int accidentCount,
                    String riskLevel) {

        this.location = location;
        this.roadCondition = roadCondition;
        this.trafficLevel = trafficLevel;
        this.accidentCount = accidentCount;
        this.riskLevel = riskLevel;
    }

    public int getRiskId() {
        return riskId;
    }

    public void setRiskId(int riskId) {
        this.riskId = riskId;
    }

    public String getLocation() {
        return location;
    }

    public String getRoadCondition() {
        return roadCondition;
    }

    public String getTrafficLevel() {
        return trafficLevel;
    }

    public int getAccidentCount() {
        return accidentCount;
    }

    public String getRiskLevel() {
        return riskLevel;
    }
}