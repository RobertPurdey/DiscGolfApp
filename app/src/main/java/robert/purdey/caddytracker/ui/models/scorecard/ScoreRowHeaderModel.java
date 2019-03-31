package robert.purdey.caddytracker.ui.models.scorecard;

public class ScoreRowHeaderModel
{
    public ScoreRowHeaderModel(String header)
    {
        Header = header;
    }

    public String Header;

    public String getHeader()
    {
        return Header;
    }

    public void setHeader(String header)
    {
        Header = header;
    }
}
