import java.time.LocalDateTime;

public class StakingReward {

    final LocalDateTime time;
    final String currency;
    final float amountRecieved;
    final String description;

    public StakingReward(String currency, float amountRecieved, String description, LocalDateTime time) {
        this.currency = currency;
        this.amountRecieved = amountRecieved;
        this.description = description;
        this.time = time;
    }

    public String toCSVLine() {
        return String.join(",",
                time.toString(),
                currency,
                String.valueOf(amountRecieved),
                description);
    }

    @Override
    public String toString() {
        return "StakingReward{" +
                "currency='" + currency + '\'' +
                ", amountRecieved=" + amountRecieved +
                ", description='" + description + '\'' +
                ", date=" + time +
                '}';
    }
}
