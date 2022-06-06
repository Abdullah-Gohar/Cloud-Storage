import gui.*;
public class GuiTest {
    public static void main(String args[]) {
        // new Main().setVisible(true);        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
