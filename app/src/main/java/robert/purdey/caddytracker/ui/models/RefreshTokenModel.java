package robert.purdey.caddytracker.ui.models;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class RefreshTokenModel
{
    private String refreshToken;

    @SerializedName("grant_type")
    private String grantType;

    public RefreshTokenModel(String refreshToken)
    {
        this.refreshToken  = refreshToken;
        this.grantType     = "refresh_token";
    }

    public Map<String, String> getRequestFields()
    {
        HashMap<String, String> fields = new HashMap<>();

        fields.put("grant_type", grantType);
        fields.put("refresh_token", refreshToken);

        return fields;
    }
}
