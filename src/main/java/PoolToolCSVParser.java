import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PoolToolCSVParser {

    private final static int TYPE = 0;
    private final static int AMOUNT_RECIEVED = 1;
    private final static int CURRENCY = 2;
    private final static int COMMENT = 9;
    private final static int DATE = 10;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<StakingReward> parseCSV(List<String> csvLines) {
        List<StakingReward> stakingRewards = new ArrayList<>();
        csvLines.forEach(line -> {
            String[] parts = line.split(",");
            stakingRewards.add(new StakingReward(
                    parts[CURRENCY],
                    Float.parseFloat(parts[AMOUNT_RECIEVED]),
                    parts[COMMENT],
                    LocalDateTime.parse(parts[DATE], DATE_TIME_FORMATTER)
                    ));
        });
        return stakingRewards;
    }



}
