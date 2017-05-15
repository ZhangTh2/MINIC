package bianyiqi;
//Ö÷º¯Êý 
import java.awt.*;
import javax.swing.*;
public class bianyi {

	public static void main(String[] args) {
	// TODO Auto-generated method stub
		
	System.setProperty( "Quaqua.tabLayoutPolicy","wrap");
	try{
	JFrame.setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
	}catch(Exception e){}

Font vFont = new Font("Dialog", Font.PLAIN, 20); 

           UIManager.put("ToolTip.font", vFont); 

           UIManager.put("Table.font", vFont); 

           UIManager.put("TableHeader.font", vFont); 

           UIManager.put("TextField.font", vFont); 

           UIManager.put("ComboBox.font", vFont); 

           UIManager.put("TextField.font", vFont); 

           UIManager.put("PasswordField.font", vFont); 

           UIManager.put("TextArea.font", vFont); 

           UIManager.put("TextPane.font", vFont); 

           UIManager.put("EditorPane.font", vFont); 

           UIManager.put("FormattedTextField.font", vFont); 

           UIManager.put("Button.font", vFont); 

           UIManager.put("CheckBox.font", vFont); 

           UIManager.put("RadioButton.font", vFont); 

           UIManager.put("ToggleButton.font", vFont); 

           UIManager.put("ProgressBar.font", vFont); 

           UIManager.put("DesktopIcon.font", vFont); 

           UIManager.put("TitledBorder.font", vFont); 

           UIManager.put("Label.font", vFont); 

           UIManager.put("List.font", vFont); 

           UIManager.put("TabbedPane.font", vFont); 

           UIManager.put("MenuBar.font", vFont); 

          UIManager.put("Menu.font", vFont); 

           UIManager.put("MenuItem.font", vFont); 

           UIManager.put("PopupMenu.font", vFont); 

           UIManager.put("CheckBoxMenuItem.font", vFont); 

           UIManager.put("RadioButtonMenuItem.font", vFont); 

           UIManager.put("Spinner.font", vFont); 

           UIManager.put("Tree.font", vFont); 

           UIManager.put("ToolBar.font", vFont); 

           UIManager.put("OptionPane.messageFont", vFont); 

           UIManager.put("OptionPane.buttonFont", vFont); 
	//WinDowg win1 = new WinDowg();
    Window win = new Window();
    Listen listener  = new Listen();
    
//    win1.setListener(listener);
//    win1.setBounds(100,100,800,600);
//    win1.setTitle("±àÒëÆ÷");
    win.setListener(listener);
	}
}
