package robert.purdey.caddytracker.ui.models.scorecard;


public class ScoreColumnHeaderModel
{
    public ScoreColumnHeaderModel(String header)
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
