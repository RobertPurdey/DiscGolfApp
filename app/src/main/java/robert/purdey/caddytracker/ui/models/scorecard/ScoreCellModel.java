package robert.purdey.caddytracker.ui.models.scorecard;


public class ScoreCellModel
{
    public ScoreCellModel(Object o)
    {
        setCellData(o);
    }

    public Object cellData;

    public Object getCellData()
    {
        return cellData;
    }

    public void setCellData(Object cellData)
    {
        this.cellData = cellData;
    }
}
