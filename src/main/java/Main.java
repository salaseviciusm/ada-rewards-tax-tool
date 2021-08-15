import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide path to CSV file");
            return;
        }

        try {
            List<String> csvLines = Files.readAllLines(Path.of(args[0]));

            List<StakingReward> stakingRewards = PoolToolCSVParser.parseCSV(csvLines.subList(1, csvLines.size()));
            Map<LocalDate, Float> timestampedPrices = KrakenAPI.getHistoricalPrices();

            List<PricedStakingReward> pricedStakingRewards = stakingRewards.stream().map(stakingReward -> PricedStakingReward.from(stakingReward, timestampedPrices.get(stakingReward.time.toLocalDate()))).toList();
            System.out.println("Time,Currency,Rewards (ADA),ADA Price (GBP),Rewards (GBP),Description");
            pricedStakingRewards.forEach(stakingReward -> System.out.println(stakingReward.toCSVLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
