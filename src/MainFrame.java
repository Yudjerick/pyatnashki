import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainFrame extends JFrame {
    private JPanel panel;
    private ArrayList<JButton> buttons = new ArrayList<>();
    public MainFrame(){
        super();
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<String> shuffled = new ArrayList<>();
        shuffled.add("");
        for (int i = 1; i < 9; i++) {
            shuffled.add(i +"");
        }
        Collections.shuffle(shuffled);
        for(int i = 0; i < 9; i++){
            var button = new JButton(shuffled.get(i) + "");
            button.addActionListener(e->{
                JButton btn = (JButton) e.getSource();
                int index = buttons.indexOf(btn);
                int emptyIndex = 0;
                for (int j = 0; j < buttons.size(); j++) {
                    if(buttons.get(j).getText().equals("")){
                        emptyIndex = j;
                        break;
                    }
                }
                int btnX = index % 3;
                int btnY = index / 3;
                int emptyX = emptyIndex % 3;
                int emptyY = emptyIndex / 3;
                if(btnX == emptyX && Math.abs(btnY - emptyY) == 1 || btnY == emptyY && Math.abs(btnX - emptyX) == 1){
                    buttons.get(emptyIndex).setText(btn.getText());
                    btn.setText("");
                }
                System.out.println(checkWin());
                if(checkWin()){
                    System.out.println("Won");
                    JOptionPane.showMessageDialog(this, "You Won!");
                }
            });
            buttons.add(button);
            panel.add(button);

        }
        setContentPane(panel);
        setBounds(100,100,500,600);
        setVisible(true);
    }

    private boolean checkWin(){
        for (int j = 0; j < buttons.size() - 1; j++) {
            if(!buttons.get(j).getText().equals(((j+1) + ""))){
                System.out.println(buttons.get(j).getText() + " != "+ (j+1));
                return false;
            }
        }
        return true;
    }
}
