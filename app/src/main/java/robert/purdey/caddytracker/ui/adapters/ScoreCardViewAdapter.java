package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.Map;

import robert.purdey.caddytracker.ui.models.scorecard.ScoreCellModel;
import robert.purdey.caddytracker.ui.models.scorecard.ScoreColumnHeaderModel;
import robert.purdey.caddytracker.ui.models.scorecard.ScoreRowHeaderModel;
import robert.purdey.caddytracker.R;

public class ScoreCardViewAdapter extends AbstractTableAdapter<ScoreColumnHeaderModel, ScoreRowHeaderModel, ScoreCellModel>
{
    public ScoreCardViewAdapter(Context context) {
        super(context);
    }

    /**
     * This is where you create your custom Cell ViewHolder. This method is called when Cell
     * RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given type to
     * represent an item.
     *
     * @param viewType : This value comes from #getCellItemViewType method to support different type
     *                 of viewHolder as a Cell item.
     *
     * @see #getCellItemViewType(int);
     */
    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        // Get cell xml layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.table_view_score_cell,
            parent, false);
        // Create a Custom ViewHolder for a Cell item.

        return new ScoreCellViewHolder(layout);
    }

    /**
     * This is sample CellViewHolder class
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    class ScoreCellViewHolder extends AbstractViewHolder {

        public final TextView cell;

        public ScoreCellViewHolder(View itemView) {
            super(itemView);

            cell = (TextView) itemView.findViewById(R.id.table_view_score_cell);
        }
    }

    /**
     * That is where you set Cell View Model data to your custom Cell ViewHolder. This method is
     * Called by Cell RecyclerView of the TableView to display the data at the specified position.
     * This method gives you everything you need about a cell item.
     *
     * @param holder       : This is one of your cell ViewHolders that was created on
     *                     ```onCreateCellViewHolder``` method. In this example we have created
     *                     "MyCellViewHolder" holder.
     * @param cellItemModel     : This is the cell view model located on this X and Y position. In this
     *                     example, the model class is "Cell".
     * @param columnPosition : This is the X (Column) position of the cell item.
     * @param rowPosition : This is the Y (Row) position of the cell item.
     *
     * @see #onCreateCellViewHolder(ViewGroup, int);
     */
    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int
        columnPosition, int rowPosition) {
        ScoreCellModel cell = (ScoreCellModel) cellItemModel;

        // Get the holder to update cell item text
        ScoreCellViewHolder viewHolder = (ScoreCellViewHolder) holder;

        viewHolder.cell.setText(cell.getCellData().toString());
    }


    /**
     * This is sample CellViewHolder class.
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    class ScoreColumnHeaderViewHolder extends AbstractViewHolder {

        public final TextView header;

        public ScoreColumnHeaderViewHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView.findViewById(R.id.table_view_score_column_header);
        }
    }

    /**
     * This is where you create your custom Column Header ViewHolder. This method is called when
     * Column Header RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given
     * type to represent an item.
     *
     * @param viewType : This value comes from "getColumnHeaderItemViewType" method to support
     *                 different type of viewHolder as a Column Header item.
     *
     * @see #getColumnHeaderItemViewType(int);
     */
    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Column Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(
            R.layout.table_view_score_column_header,
            parent,
            false);

        // Create a ColumnHeader ViewHolder
        return new ScoreColumnHeaderViewHolder(layout);
    }

    /**
     * That is where you set Column Header View Model data to your custom Column Header ViewHolder.
     * This method is Called by ColumnHeader RecyclerView of the TableView to display the data at
     * the specified position. This method gives you everything you need about a column header
     * item.
     *
     * @param holder   : This is one of your column header ViewHolders that was created on
     *                 ```onCreateColumnHeaderViewHolder``` method. In this example we have created
     *                 "MyColumnHeaderViewHolder" holder.
     * @param columnHeaderItemModel : This is the column header view model located on this X position. In this
     *                 example, the model class is "ColumnHeader".
     * @param position : This is the X (Column) position of the column header item.
     *
     * @see #onCreateColumnHeaderViewHolder(ViewGroup, int) ;
     */
    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int
        position) {
        ScoreColumnHeaderModel columnHeader = (ScoreColumnHeaderModel) columnHeaderItemModel;

        // Get the holder to update cell item text
        ScoreColumnHeaderViewHolder columnHeaderViewHolder = (ScoreColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.header.setText(columnHeader.getHeader());

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
       // columnHeaderViewHolder.column_header_container.getLayoutParams().width = LinearLayout
        //    .LayoutParams.WRAP_CONTENT;
       // columnHeaderViewHolder.column_header_textview.requestLayout();
    }

    /**
     * This is sample CellViewHolder class.
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    class ScoreRowHeaderViewHolder extends AbstractViewHolder {

        public final TextView header;


        public ScoreRowHeaderViewHolder(View itemView)
        {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.table_view_score_row_header);
        }
    }


    /**
     * This is where you create your custom Row Header ViewHolder. This method is called when
     * Row Header RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given
     * type to represent an item.
     *
     * @param viewType : This value comes from "getRowHeaderItemViewType" method to support
     *                 different type of viewHolder as a row Header item.
     *
     * @see #getRowHeaderItemViewType(int);
     */
    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(
            R.layout.table_view_score_row_header,
            parent,
            false);

        // Create a Row Header ViewHolder
        return new ScoreRowHeaderViewHolder(layout);
    }


    /**
     * That is where you set Row Header View Model data to your custom Row Header ViewHolder. This
     * method is Called by RowHeader RecyclerView of the TableView to display the data at the
     * specified position. This method gives you everything you need about a row header item.
     *
     * @param holder   : This is one of your row header ViewHolders that was created on
     *                 ```onCreateRowHeaderViewHolder``` method. In this example we have created
     *                 "MyRowHeaderViewHolder" holder.
     * @param rowHeaderItemModel : This is the row header view model located on this Y position. In this
     *                 example, the model class is "RowHeader".
     * @param position : This is the Y (row) position of the row header item.
     *
     * @see #onCreateRowHeaderViewHolder(ViewGroup, int) ;
     */
    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int
        position) {
        ScoreRowHeaderModel rowHeader = (ScoreRowHeaderModel) rowHeaderItemModel;

        // Get the holder to update row header item text
        ScoreRowHeaderViewHolder rowHeaderViewHolder = (ScoreRowHeaderViewHolder) holder;
        rowHeaderViewHolder.header.setText(rowHeader.getHeader());
    }


    @Override
    public View onCreateCornerView() {
        // Get Corner xml layout
        return LayoutInflater.from(mContext).inflate(R.layout.table_view_corner, null);
    }

    @Override
    public int getColumnHeaderItemViewType(int columnPosition) {
        // The unique ID for this type of column header item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of CellViewHolder on "onCreateCellViewHolder"
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int rowPosition) {
        // The unique ID for this type of row header item
        // If you have different items for Row Header View by Y (Row) position,
        // then you should fill this method to be able create different
        // type of RowHeaderViewHolder on "onCreateRowHeaderViewHolder"
        return 0;
    }

    @Override
    public int getCellItemViewType(int columnPosition) {
        // The unique ID for this type of cell item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of CellViewHolder on "onCreateCellViewHolder"
        return 0;
    }
}
