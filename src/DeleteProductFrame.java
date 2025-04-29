import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteProductFrame extends JFrame {

    private JTextField productIdField;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private static final String url = "jdbc:mysql://localhost:3306/StokTakipSistemi?useSSL=false&serverTimezone=UTC";
    String username = "root";
    String password = "sifreniz";

    public DeleteProductFrame() {
        setTitle("Product Delete");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230)); // Açık mavi arka plan
        setLayout(null);

        // Başlık
        JLabel titleLabel = new JLabel("PRODUCT DELETE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(300, 10, 300, 30);
        add(titleLabel);

        // Product ID TextField ve Button
        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setBounds(50, 60, 100, 25);
        add(productIdLabel);

        productIdField = new JTextField();
        productIdField.setBounds(150, 60, 200, 25);
        add(productIdField);

        JButton enterButton = new JButton("ENTER");
        enterButton.setBounds(370, 60, 100, 25);
        add(enterButton);


        String[] columnNames = {"id", "Product Name", "Category", "Stock", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 100, 700, 200);
        add(scrollPane);

        // DELETE Butonu
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBounds(670, 320, 100, 30);
        add(deleteButton);

        // ENTER Butonuna Tıklanınca Ürün Detaylarını Getir
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                fetchProductDetails(productId);
            }
        });

        // DELETE Butonuna Tıklanınca Ürünü Sil
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    int productId = (int) tableModel.getValueAt(selectedRow, 0);
                    deleteProduct(productId);
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product to delete.");
                }
            }
        });

        setVisible(true);
    }


    private void fetchProductDetails(String productId) {
        String query = "SELECT id, productname, category, stock, price FROM Products WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url,"root","34141603");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Integer.parseInt(productId));
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0);  //

            while (rs.next()) {
                int id = rs.getInt("id");  // "product_id" yerine "id"
                String name = rs.getString("productname");
                String category = rs.getString("category");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");

                tableModel.addRow(new Object[]{id, name, category, stock, price});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching product: " + e.getMessage());
        }
    }



    private void deleteProduct(int productId) {
        String query = "DELETE FROM Products WHERE id = ?";  // "product_id" yerine "id" yazıldı
        try (Connection conn = DriverManager.getConnection(url,"root","34141603");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productId);  // Ürünün ID'sini parametre olarak ekle
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Product deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No product found with the given ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting product: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new DeleteProductFrame();
    }
}
