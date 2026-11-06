public class ServiceRequest implements Comparable<ServiceRequest> {

    private String description;
    private int priority;
    public ServiceRequest(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
            return description;
    }

    public int getPriority() {
            return priority;
    }

    @Override
    public int compareTo(ServiceRequest other) {
        return Integer.compare(this.priority, other.priority);
    }
}
