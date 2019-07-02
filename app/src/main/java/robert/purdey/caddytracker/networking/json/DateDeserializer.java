package robert.purdey.caddytracker.networking.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date>
{

    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
    {
        String date = element.getAsString();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date formattedDate = formatter.parse(date);

            return formattedDate;
        } catch (ParseException e) {
            System.err.println("Failed to parse Date due to:" + e.getMessage());
            return null;
        }
    }
}
