package park.model;

public enum StayStatus {

    /**
     * Can be:
     * E - Entrance;
     * O - Outgoing;
     * C - Cancelled.
     */
    ENTRANCE("E"),
    OUTGOING("O"),
    CANCELLED("C");
    private final String status;

    public String getStatus() {
        return status;
    }

    StayStatus(String status) {
        this.status = status;
    }
}
