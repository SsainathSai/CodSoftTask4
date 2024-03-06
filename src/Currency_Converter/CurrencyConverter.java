package Currency_Converter;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CurrencyConverter {
	 private static final String USERNAME = "user";
	    private static final String PASSWORD = "password";

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(LoginFrame::new);
	    }

	    static class LoginFrame extends JFrame {
	        private JTextField usernameField;
	        private JPasswordField passwordField;

	        LoginFrame() {
	            super("Currency Converter - Login");
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setResizable(false);

	            
	            ImageIcon icon = new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	            setIconImage(icon.getImage());

	            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	            JLabel usernameLabel = new JLabel("Username:");
	            usernameField = new JTextField(20);
	            panel.add(usernameLabel);
	            panel.add(usernameField);

	            JLabel passwordLabel = new JLabel("Password:");
	            passwordField = new JPasswordField(20);
	            panel.add(passwordLabel);
	            panel.add(passwordField);

	            JButton loginButton = new JButton("Login");
	            loginButton.addActionListener(new LoginListener());
	            panel.add(loginButton);

	            add(panel);
	            pack();
	            setLocationRelativeTo(null);
	            setVisible(true);
	        }

	        class LoginListener implements ActionListener {
	            public void actionPerformed(ActionEvent e) {
	                String usernameInput = usernameField.getText();
	                String passwordInput = new String(passwordField.getPassword());

	                if (usernameInput.equals(USERNAME) && passwordInput.equals(PASSWORD)) {
	                    dispose(); 
	                    new CurrencyConverterFrame(); 
	                } else {
	                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }

	    static class CurrencyConverterFrame extends JFrame {
	        private JComboBox<String> baseCurrencyComboBox;
	        private JComboBox<String> targetCurrencyComboBox;
	        private JTextField amountField;
	        private JTextArea resultArea;

	        CurrencyConverterFrame() {
	            super("Currency Converter");
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setResizable(false);

	            JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
	            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	            
	            JPanel currencyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	            JLabel baseCurrencyLabel = new JLabel("Base Currency:");
	            String[] currencies = {"USD", "EUR", "GBP", "JPY", "INR"};
	            baseCurrencyComboBox = new JComboBox<>(currencies);
	            JLabel targetCurrencyLabel = new JLabel("Target Currency:");
	            targetCurrencyComboBox = new JComboBox<>(currencies);
	            currencyPanel.add(baseCurrencyLabel);
	            currencyPanel.add(baseCurrencyComboBox);
	            currencyPanel.add(targetCurrencyLabel);
	            currencyPanel.add(targetCurrencyComboBox);
	            panel.add(currencyPanel);

	            
	            JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	            JLabel amountLabel = new JLabel("Amount:");
	            amountField = new JTextField(10);
	            amountPanel.add(amountLabel);
	            amountPanel.add(amountField);
	            panel.add(amountPanel);

	            
	            JButton convertButton = new JButton("Convert");
	            convertButton.addActionListener(new ConvertListener());
	            panel.add(convertButton);

	            
	            resultArea = new JTextArea(5, 20);
	            resultArea.setEditable(false);
	            panel.add(new JScrollPane(resultArea));

	           
	            JButton exitButton = new JButton("Exit");
	            exitButton.addActionListener(e -> System.exit(0));
	            panel.add(exitButton);

	            add(panel);
	            pack();
	            setLocationRelativeTo(null);
	            setVisible(true);
	        }

	        class ConvertListener implements ActionListener {
	            public void actionPerformed(ActionEvent e) {
	                String baseCurrency = (String) baseCurrencyComboBox.getSelectedItem();
	                String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();
	                double amount = Double.parseDouble(amountField.getText());

	                // Simulate fetching exchange rates (replace with actual API call)
	                double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

	                double convertedAmount = amount * exchangeRate;

	                resultArea.setText("Converted Amount: " + convertedAmount + " " + targetCurrency);
	            }
	        }

	        
	        private double getExchangeRate(String baseCurrency, String targetCurrency) {
	            if (baseCurrency.equals("USD") && targetCurrency.equals("EUR")) {
	                return 0.85; 
	            } else if (baseCurrency.equals("USD") && targetCurrency.equals("GBP")) {
	                return 0.72; 
	            } else if (baseCurrency.equals("USD") && targetCurrency.equals("JPY")) {
	                return 105.50; 
	            } else if (baseCurrency.equals("USD") && targetCurrency.equals("INR")) {
	                return 75.00; 
	            } else if (baseCurrency.equals("EUR") && targetCurrency.equals("USD")) {
	                return 1.18; 
	            } else if (baseCurrency.equals("EUR") && targetCurrency.equals("GBP")) {
	                return 0.86; 
	            } else if (baseCurrency.equals("EUR") && targetCurrency.equals("JPY")) {
	                return 125.00; 
	            } else if (baseCurrency.equals("EUR") && targetCurrency.equals("INR")) {
	                return 88.50; 
	            } else if (baseCurrency.equals("GBP") && targetCurrency.equals("USD")) {
	                return 1.39; 
	            } else if (baseCurrency.equals("GBP") && targetCurrency.equals("EUR")) {
	                return 1.16; 
	            } else if (baseCurrency.equals("GBP") && targetCurrency.equals("JPY")) {
	                return 144.50; 
	            } else if (baseCurrency.equals("GBP") && targetCurrency.equals("INR")) {
	                return 99.25; 
	            } else if (baseCurrency.equals("JPY") && targetCurrency.equals("USD")) {
	                return 0.0095;
	            } else if (baseCurrency.equals("JPY") && targetCurrency.equals("EUR")) {
	                return 0.0080;
	            } else if (baseCurrency.equals("JPY") && targetCurrency.equals("GBP")) {
	                return 0.0069; 
	            } else if (baseCurrency.equals("JPY") && targetCurrency.equals("INR")) {
	                return 0.70; 
	            } else if (baseCurrency.equals("INR") && targetCurrency.equals("USD")) {
	                return 0.013; 
	            } else if (baseCurrency.equals("INR") && targetCurrency.equals("EUR")) {
	                return 0.011; 
	            } else if (baseCurrency.equals("INR") && targetCurrency.equals("GBP")) {
	                return 0.010; 
	            } else if (baseCurrency.equals("INR") && targetCurrency.equals("JPY")) {
	                return 1.43; 
	            } else {
	                return 1.0; 
	            }
	        }
	    }
}
