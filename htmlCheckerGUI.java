import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class htmlCheckerGUI extends JFrame {
    static htmlChecker checker = new htmlChecker();
	private JPanel contentPane;
	public JTextArea HTMLText;
    public JTextArea errorLogs;
	private JPanel panel;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public htmlCheckerGUI() {
		setTitle("HTML Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		HTMLText = new JTextArea();
		contentPane.add(HTMLText, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		errorLogs = new JTextArea();
		errorLogs.setEditable(false);
		errorLogs.setRows(4);
		panel.add(errorLogs, BorderLayout.CENTER);
		
		JButton button = new JButton("Analyze HTML");
		button.addActionListener(e -> checker.htmlParse());
		button.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.add(button, BorderLayout.SOUTH);
		
		label = new JLabel("Error Logs:");
		panel.add(label, BorderLayout.NORTH);
	}

}
