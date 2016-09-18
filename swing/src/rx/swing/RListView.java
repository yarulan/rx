package rx.swing;

import rx.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Function;

public class RListView<T, U> extends RComponent<JList<U>> {
    private final DefaultListModel<U> listModel = new DefaultListModel<>();

    private final java.util.List<Subscription<ValueChangeEvent<U>>> subscriptions = new ArrayList<>();

    public final RxList<T> list;

    public final RxList<T> selection = new VarList<>();

    public RListView(RxList<T> list, Function<T, Rx<U>> f) {
        super(new JList<U>());
        component.setModel(listModel);
        this.list = list;
        list.onChange(true, e -> {
            for (int i = 0; i < e.removedItems.size(); i++) {
                listModel.remove(e.removalPosition);
                subscriptions.get(e.removalPosition).end();
                subscriptions.remove(e.removalPosition);
            }
            for (int i = 0; i < e.addedItems.size(); i++) {
                Rx<U> rx = f.apply(e.addedItems.get(i));
                listModel.add(e.additionPosition + i, rx.getValue());
                Subscription<ValueChangeEvent<U>> subscription = rx.onChange(this, e1 -> listModel.set(e.additionPosition, e1.value));
                subscriptions.add(e.additionPosition + i, subscription);
            }
        });
        component.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int[] selectedIndices = component.getSelectedIndices();
                ArrayList<T> items = new ArrayList<>(selectedIndices.length);
                for (int i = 0; i < selectedIndices.length; i++) {
                    items.add(list.getItem(selectedIndices[i]));
                }
                ((VarList<T>) selection).replace(items);
            }
        });
    }
}