package com.jamun.vinsol.variables;


public interface BluePrints {
    interface ofActivity {
        void setToolbar();
    }

    interface ofSheet {
        void initializeSheet();
    }

    interface ofView {
        void initializeView();

        void initializeListeners();

        void initializeData();

        void initializePicker();

        void initializeViewModel();

        void initializeTabView();

        void closeEverything();
    }

    interface ofRecycler {
        void initializeRecyclerView();

        void initializeEmptyView(boolean isEmpty);
    }

    interface ofFrag {
        void initializeFragsView();
    }

}
