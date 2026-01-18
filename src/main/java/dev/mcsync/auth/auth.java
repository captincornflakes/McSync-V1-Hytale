package dev.mcsync.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;

import com.hypixel.hytale.logger.HytaleLogger;


public class auth {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    @SuppressWarnings("deprecation")
    public static String mcsyncAuth(String token, String uuid) {
        boolean authorize = false;
        int tier = 0;
        JSONObject result = new JSONObject();
        HttpURLConnection connection = null;
        String normalizedBase = normalizeBaseUrl("https://api.mcsync.live/");
        String userAgent = "Mozilla/5.0";
        boolean debugLogging = false;

        try {
            URL url = new URL(normalizedBase + "?token=" + token + "&uuid=" + uuid.replace("-", ""));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", userAgent);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    if (debugLogging) {
                        LOGGER.atInfo().log("McSync API response: " + response);
                    }
                    JSONObject data = new JSONObject(response.toString());
                    authorize = data.getBoolean("subscriber");
                    tier = data.getInt("tier");
                    LOGGER.atInfo().log("Authorization: " + authorize + ", Tier: " + tier);
                } catch (IOException ex) {
                    LOGGER.atSevere().withCause(ex).log("McSync Error!Code:002 - Failed to read response from McSync API.");
                }
            }
        } catch (ProtocolException ex) {
            LOGGER.atSevere().withCause(ex).log("McSync Error!Code:002 - Failed to read response from McSync API.");
        } catch (IOException ex) {
            LOGGER.atSevere().withCause(ex).log("McSync Error!Code:001 - Failed to connect to McSync API.");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        result.put("authorize", authorize);
        result.put("tier", tier);
        return result.toString();
    }

    private static String normalizeBaseUrl(String base) {
        if (base == null || base.isBlank()) {
            return "https://api.mcsync.live/";
        }
        return base.endsWith("/") ? base : base + "/";
    }
}
