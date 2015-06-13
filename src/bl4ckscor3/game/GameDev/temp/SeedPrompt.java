package bl4ckscor3.game.GameDev.temp;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bl4ckscor3.game.GameDev.game.Game;
import bl4ckscor3.game.GameDev.menu.Menu;

public class SeedPrompt extends JFrame
{
	private static final long serialVersionUID = -7766742253902186804L;

	public SeedPrompt()
	{
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		String labelText = "Please enter a seed below./It can be no longer as " + String.valueOf(Integer.MAX_VALUE).toString().length() + " /characters and can only contain numbers.";
		String buttonText = "Set the seed!";
		JTextField text = new JTextField();
		JButton button = new JButton();
		Container cp = getContentPane();
		
		setTitle("Seed");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		cp.setLayout(null);
		
		label.setBounds(50, 10, labelText.split("/")[0].length() * 6, 20);
		label.setText(labelText.split("/")[0]);
		label2.setBounds(50, 20, labelText.split("/")[1].length() * 6, 20);
		label2.setText(labelText.split("/")[1]);
		label3.setBounds(50, 30, labelText.split("/")[2].length() * 6, 20);
		label3.setText(labelText.split("/")[2]);
		text.setBounds(50, 60, 200, 20);
		button.setBounds(90, 90, buttonText.length() * 10, 20);
		button.setText(buttonText);
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(text.getText() == null)
					Game.setSeed(123456789);
				else
					Game.setSeed(Integer.parseInt(text.getText()));
				
				dispose();
				Game.hasRunBefore = true;
				Menu.setState(Menu.STATE_OFF);
			}
		});
		
		cp.add(label);
		cp.add(label2);
		cp.add(label3);
		cp.add(text);
		cp.add(button);
		setVisible(true);
	}
}
