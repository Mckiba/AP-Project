package view;

import client.Client;
import model.Complaints;
import model.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TechDashboard extends javax.swing.JFrame {

    String selectedImagePath = "";
    String[] col = {"ISSUE ID", "CUSTOMER ID", "Category", "Issue Details"};

    DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    User globalUser;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox jComboBoxSubjects;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldName;


    /**
     * Creates new form RepDashboard
     *
     * @param user
     */
    public TechDashboard(User user) {
        globalUser = user;
        initComponents();
        populateInputfields();
        //Calling method/function to add column header to jtable
    }
    /**
     * @param args the command line arguments
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTable1 = new JTable(tableModel);

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        JScrollPane jScrollPane1 = new JScrollPane();

        jTextFieldName = new javax.swing.JTextField();
        JLabel jLabel1 = new JLabel();
        jComboBoxSubjects = new javax.swing.JComboBox();
        JButton jButton1 = new JButton();
        jLabelImage = new javax.swing.JLabel();
        JButton jButtonBrowseImage = new JButton();
        JButton jButton2 = new JButton();
        JButton jButton4 = new JButton();
        JButton jButton5 = new JButton();
        JButton jButton6 = new JButton();
        JButton jButtonImportExcelToJtable = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jScrollPane1.setViewportView(jTable1);

        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        jLabel1.setText("CUSTOMER ID");


        jComboBoxSubjects.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Pay-Per View", "Channel Listing", "VR", "Business Information"}));

        jButton1.setText("Refresh Data");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5ActionPerformed();
                populateInputfields();
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonBrowseImage.setText("Browse //");
        jButtonBrowseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseImageActionPerformed(evt);
            }
        });

        jButton2.setText("Respond to Issue");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResponseScreen responseScreen = new ResponseScreen(globalUser);
                responseScreen.setVisible(true);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });



        jButton4.setText("Filter by Category");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed();
            }
        });

        jButton6.setText("Export (Excel)");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButtonImportExcelToJtable.setText("Import (Excel)");
        jButtonImportExcelToJtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportExcelToJTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                ))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel1)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                                                .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jComboBoxSubjects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jButtonImportExcelToJtable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jButtonBrowseImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel1)))
                                                        .addComponent(jButtonBrowseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        )
                                                        .addComponent(jButtonImportExcelToJtable, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        ))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void populateInputfields() {
        Client client = null;
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String operation = "GET-COMPLAINTS";
        client.sendAction(operation);
        client.sendUser(globalUser);
        client.receiveResponse();
        ArrayList<Complaints> complaintsList = Client.complaintsArrayList;

        for (Complaints complaints : complaintsList) {
            Object[] rowData =  {complaints.getId(),complaints.getCustomerID(),complaints.getCategory(),
                    complaints.getIssueDetails()};
            tableModel.addRow(rowData);
        }
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        populateInputfields();
    }

    private void jButtonBrowseImageActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // Setting default browser location
        JFileChooser browseImageFile = new JFileChooser("C:\\Users\\Public\\Pictures\\Sample Pictures");
        //Filter image extensions
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(fnef);
        int showOpenDialogue = browseImageFile.showOpenDialog(null);

        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = browseImageFile.getSelectedFile();
            selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, selectedImagePath);
            ImageIcon ii = new ImageIcon(selectedImagePath);
            Image image = ii.getImage().getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(), Image.SCALE_SMOOTH);
            jLabelImage.setIcon(new ImageIcon(image));
        }
    }

    //Move up button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {


    }


    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add get complaints from server code here:
        Client client = null;
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String operation = "GET-COMPLAINTS";
        assert client != null;
        client.sendAction(operation);
        System.out.println("MESSAGE SENT TO SERVER");
        client.sendUser(globalUser);
        System.out.println("RECORD SENT TO SERVER");
        client.receiveResponse();
        System.out.println("RESPONSE RECEIVED FROM SERVER");
    }

    private void jButton5ActionPerformed() {
        // Clear field:
        tableModel.setRowCount(0);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        clearFields();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void jButtonImportExcelToJTableActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void clearFields() {
        jTextFieldName.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        jComboBoxSubjects.setSelectedIndex(0);
        jLabelImage.setIcon(null);
    }
    //End of variables declaration

    class myTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            //Change Image column minimum width and maximum width
            TableColumn tb = jTable1.getColumn("Image");
            tb.setMaxWidth(60);
            tb.setMinWidth(60);
            jTable1.setRowHeight(60);
            return (Component) value;

        }

    }

}