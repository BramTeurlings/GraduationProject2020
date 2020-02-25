package nl.brickx.domain.Models;

import android.graphics.drawable.Drawable;

public class MainMenuRecyclerModel {
    private String title;
    private Drawable image;
    private MenuItemIdentifier identifier;

    public MainMenuRecyclerModel() {
    }

    public MainMenuRecyclerModel(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public MainMenuRecyclerModel(String title, Drawable image, MenuItemIdentifier identifier) {
        this.title = title;
        this.image = image;
        this.identifier = identifier;
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

    public MenuItemIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(MenuItemIdentifier identifier) {
        this.identifier = identifier;
    }
}
