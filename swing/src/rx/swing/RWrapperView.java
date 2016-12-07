//package rx.swing;
//
//import rx.Rx;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class RWrapperView extends RComponent<JPanel>{
//    public RWrapperView(Rx<JComponent> wrappee) {
//        super(new JPanel());
//        component.setLayout(new BorderLayout());
//        wrappee.onChange(this, true, e -> {
//            component.remove(e.oldValue);
//            component.add(e.value, BorderLayout.CENTER);
//            component.revalidate();
//            component.repaint();
//        });
//    }
//}
