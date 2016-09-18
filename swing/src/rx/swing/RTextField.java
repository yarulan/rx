package rx.swing;

import rx.Var;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RTextField extends RComponent<JTextField> {
    public final Var<String> text;

    private boolean inNotification = false;

    public RTextField(Var<String> text) {
        super(new JTextField());

        component.setText(text.getValue());

        this.text = text;

        text.onChange(this, e -> {
            if (!inNotification) component.setText(e.value);
        });

        component.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                inNotification = true;
                text.setValue(component.getText());
                inNotification = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                inNotification = true;
                text.setValue(component.getText());
                inNotification = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                inNotification = true;
                text.setValue(component.getText());
                inNotification = false;
            }
        });
    }
}