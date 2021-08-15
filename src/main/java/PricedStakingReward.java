import java.time.LocalDateTime;

public class PricedStakingReward {

    final LocalDateTime time;
    final String currency;
    final float amountRecieved;
    final String description;
    final Float convertedPrice;
    final Float price;

    private PricedStakingReward(String currency, float amountRecieved, String description, LocalDateTime time, Float convertedPrice, Float price) {
        this.currency = currency;
        this.amountRecieved = amountRecieved;
        this.description = description;
        this.time = time;
        this.convertedPrice = convertedPrice;
        this.price = price;
    }

    public static PricedStakingReward from(StakingReward reward, Float price) {
        if (price == null) {
            return new PricedStakingReward(reward.currency, reward.amountRecieved, reward.description, reward.time, null, null);
        }
        return new PricedStakingReward(reward.currency, reward.amountRecieved, reward.description, reward.time, price * reward.amountRecieved, price);
    }

    public String toCSVLine() {
        return String.join(",",
                time.toString(),
                currency,
                String.valueOf(amountRecieved),
                price == null ? "" : String.valueOf(price),
                convertedPrice == null ? "" : String.valueOf(convertedPrice),
                description);
    }

    @Override
    public String toString() {
        return "PricedStakingReward{" +
                "time=" + time +
                ", currency='" + currency + '\'' +
                ", amountRecieved=" + amountRecieved +
                ", description='" + description + '\'' +
                ", convertedPrice=" + convertedPrice +
                ", price=" + price +
                '}';
    }
}
