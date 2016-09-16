package kz.kase.bot.model.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.Date;

public class MessageHolder implements Holder {

    private final Date date;
    private final String text;
    private final String title;
    private BooleanProperty read = new SimpleBooleanProperty(false);

    public MessageHolder(String title, String text, Date date) {
        this.date = date;
        this.text = text;
        this.title = title;
    }


    @SuppressWarnings("unchecked")
    public void addListener(ChangeListener listener) {
        read.addListener(listener);
    }

    public boolean getRead() {
        return read.get();
    }

    public BooleanProperty readProperty() {
        return read;
    }

    public void read() {
        read.set(true);
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public Object getKey() {
        return null;
    }
}
