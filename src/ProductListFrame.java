import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ProductListFrame extends JFrame {

    private JTable productTable;
    private DefaultTableModel tableModel;
    private static final String url = "jdbc:mysql://localhost:3306/StokTakipSistemi?useSSL=false&serverTimezone=UTC";
    String username = "root";
    String password = "sifreniz";

    public ProductListFrame() {
        setTitle("Products");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Arka plan rengini
        getContentPane().setBackground(new Color(173, 216, 230));

        // Tablo
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Category", "Product Name", "Stock", "Price"});
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);


        JButton listButton = new JButton("LIST");
        listButton.addActionListener(e -> listProducts());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(listButton);
        add(buttonPanel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
    }

    private void listProducts() {
        String query = "SELECT id, category, productname, stock, price FROM Products";
        try (Connection conn = DriverManager.getConnection(url,"root","34141603");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            tableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                String productName = rs.getString("productname");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                tableModel.addRow(new Object[]{id, category, productName, stock, price});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching product data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductListFrame frame = new ProductListFrame();
            frame.setVisible(true);
        });
    }
}
