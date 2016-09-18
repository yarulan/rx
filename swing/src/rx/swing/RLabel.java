package rx.swing;

import rx.Rx;

import javax.annotation.Nonnull;
import javax.swing.*;

public class RLabel extends RComponent<JLabel> {
    public final Rx<String> text;

    public RLabel(@Nonnull Rx<String> text) {
        super(new JLabel());
        this.text = text;
        component.setText(text.getValue());
        text.onChange(this, e -> component.setText(e.value));
    }
}