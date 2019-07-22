package robert.purdey.caddytracker.domain.games;

import androidx.annotation.Nullable;

public class GameFilter
{
    @Nullable
    public GameState State;

    @Nullable
    public GameState getState()
    {
        return State;
    }

    public void setState(@Nullable GameState state)
    {
        State = state;
    }
}
