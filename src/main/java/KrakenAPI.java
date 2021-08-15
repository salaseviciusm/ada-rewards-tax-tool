import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class KrakenAPI {

    private final static String PAIR = "ADAGBP";
    private final static int INTERVAL = 1440;

    public static Map<LocalDate, Float> getHistoricalPrices() throws IOException {
        String query = "https://api.kraken.com/0/public/OHLC?pair=%s&interval=%s".formatted(PAIR, INTERVAL);

        URL url = new URL(query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        Map<LocalDate, Float> timestampedPrices = new HashMap<>();

        JSONArray priceData = (new JSONObject(content.toString())).getJSONObject("result").getJSONArray(PAIR);
        priceData.forEach(dataObj -> {
            JSONArray data = (JSONArray) dataObj;
            Timestamp timestamp = new Timestamp(data.getInt(0)* 1000L);
            float avgPrice = (data.getFloat(1) + data.getFloat(4))/2;
            timestampedPrices.put(timestamp.toLocalDateTime().toLocalDate(), avgPrice);
        });

        return timestampedPrices;
    }

}
