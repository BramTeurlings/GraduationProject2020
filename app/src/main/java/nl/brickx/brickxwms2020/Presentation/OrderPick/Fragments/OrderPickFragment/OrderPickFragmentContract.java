package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

public interface OrderPickFragmentContract {

    interface Presenter {

        void startPresenting();
    }

    interface View {

        void handleScan(String scan);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}
