package view;

import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Dashboard {

    User user;

    JFrame frame = new JFrame("Micro Star Cable company");
    JPanel head = new JPanel();
    JLabel logo = new JLabel();
    JLabel welcome = new JLabel();
    JPanel flowPanel = new JPanel();
    JPanel header = new JPanel();
    BufferedImage bufferedImage = ImageIO.read(this.getClass().getResource("/MicroStar.png"));
    Image image = bufferedImage.getScaledInstance(90, 90, Image.SCALE_DEFAULT);

    JButton status = new JButton("View Account Status");
    JButton lastPay = new JButton("View Last Payment");
    JButton viewComp = new JButton("View complaints");
    JButton viewAllPay = new JButton("View all Payments");
    String[] complaints = {"Channel Listing", "Pay Per View", "VR"};
    JComboBox<String> combobox = new JComboBox<>(complaints);
    JLabel other = new JLabel("Other Actions");
    JPanel ComboPanel = new JPanel();
    JButton submit = new JButton("Submit");
    JTextField text = new JTextField(10);
    JLabel RegisComp = new JLabel();
    Image img = Toolkit.getDefaultToolkit().getImage("/background.jpg");
    ImageIcon icon = new ImageIcon(image);


    public Dashboard(User user) throws IOException {
        welcome.setText("WELCOME USER: " + user.getCustomerID());
        setLayoutManager();
        setTexts();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeComponent();
    }

    private void setLocationAndSize() {
        //header.setBounds((200,));
        flowPanel.setBounds(250, 150, 200, 300);
        ComboPanel.setBounds(0, 150, 150, 250);
        head.setBounds(200, 0, 140, 400);
    }

    private void addActionEvent() {
    }

    private void initializeComponent() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
    }

    private void addComponentsToContainer() {
        frame.add(ComboPanel);
        frame.add(flowPanel);
        frame.add(head);
        //frame.setB
        flowPanel.add(other);
        flowPanel.add(status);
        flowPanel.add(lastPay);
        flowPanel.add(viewComp);
        flowPanel.add(viewAllPay);
        ComboPanel.add(RegisComp);
        ComboPanel.add(combobox);
        ComboPanel.add(text);
        ComboPanel.add(submit);
        head.add(logo);
        head.add(welcome);
    }

    private void setTexts() {
        RegisComp.setText("Register Complaints");
        combobox.setSelectedIndex(0);
        other.setText("Other Actions");
        logo.setIcon(icon);
    }

    private void setLayoutManager() {
        frame.setLayout(null);
        ComboPanel.setLayout(new FlowLayout());
    }
}