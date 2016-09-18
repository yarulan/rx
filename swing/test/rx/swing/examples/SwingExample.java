package rx.swing.examples;

import javafx.scene.control.SplitPane;
import rx.Expr;
import rx.RxList;
import rx.Var;
import rx.VarList;
import rx.swing.RLabel;
import rx.swing.RListView;
import rx.swing.RTextField;
import rx.swing.RWrapperView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Arrays;

public class SwingExample {
    public static void main(String[] args) {
        VarList<User> userList = new VarList<>();
        userList.add(new User("John", "Doe"));
        userList.add(new User("Jack", "Daniel's"));

//        for (int i = 0; i < 10000; i++) {
//            userList.add(new User(Integer.toString(i), Integer.toString(i)));
//        }

        RListView<User, String> listView = new RListView<>(userList, (User user) -> {
            return Expr.of(() -> user.firstName.getValue() + " " + user.lastName.getValue())
                .map(s -> s.trim().isEmpty() ? "<Empty>" : s);
        });
        listView.component.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton addButton = new JButton(new ImageIcon(SwingExample.class.getResource("/Actions-list-add-icon.png")));
        addButton.addActionListener(e -> {
            userList.add(new User());
            listView.component.setSelectedIndex(listView.component.getModel().getSize() - 1);
        });

        JButton removeButton = new JButton(new ImageIcon(SwingExample.class.getResource("/Actions-edit-delete-icon.png")));
        removeButton.addActionListener(e -> {
            int index = listView.component.getSelectedIndex();
            userList.remove(listView.selection.getItems());
            listView.component.setSelectedIndex(Math.min(index, listView.component.getModel().getSize() - 1));
        });

        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.add(addButton);
        toolBar.add(removeButton);

        RLabel usersCountLabel = new RLabel(userList.size().map(size -> "Users: " + size.toString()));

        RLabel selectionSizeLabel = new RLabel(listView.selection.size().map(size -> "Selected: " + size.toString()));

        JPanel statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));
        statusBar.add(usersCountLabel.component);
        statusBar.add(Box.createHorizontalStrut(10));
        statusBar.add(selectionSizeLabel.component);
        statusBar.add(Box.createHorizontalGlue());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(new JScrollPane(listView.component), BorderLayout.CENTER);
        leftPanel.add(toolBar, BorderLayout.NORTH);
        leftPanel.add(statusBar, BorderLayout.SOUTH);

        RWrapperView detailsPanel = new RWrapperView(listView.selection.first().map(userOpt ->
            userOpt.map(user -> {
                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                Insets insets = new Insets(5, 10, 5, 10);
                panel.add(new JLabel("First name"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
                panel.add(new RTextField(user.firstName).component, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
                panel.add(new JLabel("Last name"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
                panel.add(new RTextField(user.lastName).component, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
                return (JComponent) panel;
            }).getOrElse(() -> new JLabel("<Empty>"))
        ));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(detailsPanel.component, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(splitPane);
        frame.setVisible(true);

        splitPane.setDividerLocation(0.3);
    }
}

class User {
    public final Var<String> firstName;
    public final Var<String> lastName;

    public User() {
        this("", "");
    }

    public User(String firstName, String lastName) {
        this.firstName = Var.of(firstName);
        this.lastName = Var.of(lastName);
    }

    @Override
    public String toString() {
        return firstName.getValue() + "" + lastName.getValue();
    }
}