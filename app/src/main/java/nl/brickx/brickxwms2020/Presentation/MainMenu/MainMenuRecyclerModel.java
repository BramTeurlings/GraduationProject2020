package nl.brickx.brickxwms2020.Presentation.MainMenu;

import android.graphics.drawable.Drawable;

public class MainMenuRecyclerModel {
    private String title;
    private Drawable image;

    public MainMenuRecyclerModel(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
