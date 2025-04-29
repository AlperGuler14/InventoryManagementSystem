import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateProductFrame extends JFrame {
    private JTextField idField, categoryField, nameField, stockField, priceField;
    private JButton enterButton, updateButton;
    private final String  url = "jdbc:mysql://localhost:3306/StokTakipSistemi?useSSL=false&serverTimezone=UTC";
    String username = "root";
    String password = "34141603";

    public UpdateProductFrame() {
        setTitle("Product Update");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        getContentPane().setBackground(new Color(173, 216, 230));

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setBounds(50, 30, 100, 30);
        add(productIdLabel);

        idField = new JTextField();
        idField.setBounds(150, 30, 100, 30);
        add(idField);

        enterButton = new JButton("ENTER");
        enterButton.setBounds(270, 30, 100, 30);
        add(enterButton);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 80, 100, 30);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(150, 80, 150, 30);
        add(categoryField);

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setBounds(50, 130, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 130, 150, 30);
        add(nameField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(50, 180, 100, 30);
        add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(150, 180, 150, 30);
        add(stockField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 230, 100, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(150, 230, 150, 30);
        add(priceField);

        updateButton = new JButton("Update");
        updateButton.setBounds(150, 280, 100, 30);
        add(updateButton);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchProductDetails();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });


        setVisible(true);
    }

    private void fetchProductDetails() {
        int productId = Integer.parseInt(idField.getText());
        String query = "SELECT * FROM Products WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url,"root","34141603");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                categoryField.setText(rs.getString("category"));
                nameField.setText(rs.getString("productname"));
                stockField.setText(String.valueOf(rs.getInt("stock")));
                priceField.setText(String.valueOf(rs.getDouble("price")));
            } else {
                JOptionPane.showMessageDialog(null, "Product not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching product: " + e.getMessage());
        }
    }

    private void updateProduct() {
        int productId = Integer.parseInt(idField.getText());
        String category = categoryField.getText();
        String productName = nameField.getText();
        int stock = Integer.parseInt(stockField.getText());
        double price = Double.parseDouble(priceField.getText());

        String query = "UPDATE Products SET category = ?, productname = ?, stock = ?, price = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url,"root","34141603");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category);
            pstmt.setString(2, productName);
            pstmt.setInt(3, stock);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, productId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating product: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new UpdateProductFrame();
    }
}
