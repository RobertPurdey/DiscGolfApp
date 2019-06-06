package robert.purdey.caddytracker.domain.games;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;

import java.lang.reflect.Type;

@JsonAdapter(GameState.GsonSerialize.class)
public enum GameState
{
    InProgress(0),
    Completed(1);

    public final int value;

    GameState(int value)
    {
        this.value = value;
    }

    static GameState getLevelByCode(int val) {
        for (GameState state : values())
            if (state.value == val)
                return state;

        return null;
    }

    static class GsonSerialize implements JsonSerializer<GameState>, JsonDeserializer<GameState>
    {
        @Override
        public JsonElement serialize(GameState src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src.value);
        }

        @Override
        public GameState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            try {
                return getLevelByCode(json.getAsNumber().intValue());
            } catch (JsonParseException e) {
                return null;
            }
        }
    }
}