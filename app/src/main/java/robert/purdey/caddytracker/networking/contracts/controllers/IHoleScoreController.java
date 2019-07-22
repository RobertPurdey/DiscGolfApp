package robert.purdey.caddytracker.networking.contracts.controllers;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import robert.purdey.caddytracker.domain.holescores.HoleScoreFilterModel;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;

public interface IHoleScoreController
{
    MutableLiveData<List<HoleScoreModel>> getWithFilter(HoleScoreFilterModel filter);
}
