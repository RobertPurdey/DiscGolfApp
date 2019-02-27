package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

/**
 * Created by r_pur on 2/25/2019.
 */

public class HoleModel
{
    public UUID IdKey;

    public int Par;

    public int Order;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public int getPar()
    {
        return Par;
    }

    public void setPar(int par)
    {
        Par = par;
    }

    public int getOrder()
    {
        return Order;
    }

    public void setOrder(int order)
    {
        Order = order;
    }
}
