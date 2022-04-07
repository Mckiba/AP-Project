package view;
import client.Client;
import model.Complaints;
import model.CustomerRep;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

    public class CusRepDash extends JFrame {
        JFrame frame = new JFrame("Micro Star Cable company");
        JPanel head = new JPanel();
        JLabel logo = new JLabel();
        JLabel welcome = new JLabel();
        JPanel flowPanel = new JPanel();
        JPanel header = new JPanel();
        BufferedImage bufferedImage = ImageIO.read(this.getClass().getResource("/MicroStar.png"));

        Image image = bufferedImage.getScaledInstance(90, 90, Image.SCALE_DEFAULT);

        JButton details = new JButton("Details of Complaint");
        JButton respond = new JButton("Respond");
        JButton assign = new JButton("Assign");
        String[] services = {"Resloved", "Unresolved"};
        JComboBox<String> combobox = new JComboBox<>(services);
        JLabel other = new JLabel("Services");
        JPanel ComboPanel = new JPanel();
        JButton run = new JButton("Run");
        JLabel RegisComp = new JLabel();
        Image img = Toolkit.getDefaultToolkit().getImage("/background.jpg");
        ImageIcon icon = new ImageIcon(image);

        public CusRepDash(CustomerRep rep) throws IOException {
            welcome.setText(rep.getRepId());
            setLayoutManager();
            setTexts();
            setLocationAndSize();
            addComponentsToContainer();
            //addActionEvent();
            initializeComponent();
        }
        public CusRepDash() throws IOException {
            setLayoutManager();
            setTexts();
            setLocationAndSize();
            addComponentsToContainer();
            //addActionEvent();
            initializeComponent();
        }


        private void setLocationAndSize() {
            //header.setBounds((200,));
            flowPanel.setBounds(250, 150, 200, 300);
            ComboPanel.setBounds(0, 150, 150, 250);
            head.setBounds(200, 0, 140, 400);
        }

        private void addActionEvent() {
            run.addActionListener((ActionListener) this);
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
            flowPanel.add(details);
            flowPanel.add(respond);
            flowPanel.add(assign);
            ComboPanel.add(RegisComp);
            ComboPanel.add(combobox);
            ComboPanel.add(run);
            head.add(logo);
            head.add(welcome);
        }

        private void setTexts() {
            RegisComp.setText("Register Complaints");
            combobox.setSelectedIndex(0);
            other.setText("Other Actions");
            //logo.setIcon(icon);
        }

        private void setLayoutManager() {
            frame.setLayout(null);
            ComboPanel.setLayout(new FlowLayout());
        }


        public void actionPerformed(ActionEvent e) {

            //User user = null;

            try {
                Client client = new Client();
                if (e.getSource() == run) {
                    //ArrayList<String> complaint = new ArrayList<String>();
                    //complaint.add(text.getText());
                                   }


            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
        public static void main(String[] args) throws IOException {
            new CusRepDash();

        }
    }


