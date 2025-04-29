import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductFrame extends JFrame {

    private JTextField categoryField, nameField, stockField, priceField;
    private JButton addButton;

    private static final String url = "jdbc:mysql://localhost:3306/StokTakipSistemi?useSSL=false&serverTimezone=UTC";
    String username = "root";  // MySQL kullanıcı adı
    String password = "34141603";  // MySQL şifresi


    public AddProductFrame() {
        setTitle("Add Product");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Açık mavi arkaplan

        // Başlık
        JLabel titleLabel = new JLabel("ADD PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(160, 10, 200, 30);
        add(titleLabel);

        // Kategori
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 70, 100, 25);
        add(categoryLabel);
        categoryField = new JTextField();
        categoryField.setBounds(150, 70, 250, 25);
        add(categoryField);

        // Ürün Adı
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setBounds(50, 110, 100, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 110, 250, 25);
        add(nameField);

        // Stok
        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(50, 150, 100, 25);
        add(stockLabel);
        stockField = new JTextField();
        stockField.setBounds(150, 150, 250, 25);
        add(stockField);

        // Fiyat
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 190, 100, 25);
        add(priceLabel);
        priceField = new JTextField();
        priceField.setBounds(150, 190, 250, 25);
        add(priceField);

        // Ekle Butonu
        addButton = new JButton("ADD");
        addButton.setBounds(350, 250, 80, 30);
        add(addButton);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = categoryField.getText();
                String name = nameField.getText();
                String stock = stockField.getText();
                String price = priceField.getText();


                addProductToDatabase(category, name, stock, price);

                JOptionPane.showMessageDialog(null, "Product Added:\n" +
                        "Category: " + category + "\n" +
                        "Name: " + name + "\n" +
                        "Stock: " + stock + "\n" +
                        "Price: " + price);

                // temizleme
                categoryField.setText("");
                nameField.setText("");
                stockField.setText("");
                priceField.setText("");
            }
        });

        setVisible(true);
    }

    // Ürünü veritabanının eklendiği yer
    private void addProductToDatabase(String category, String name, String stock, String price) {
        System.out.println("Veritabanına bağlanılıyor...");

        String query = "INSERT INTO Products (category, productname, stock, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url ,"root","34141603");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category);
            pstmt.setString(2, name);
            pstmt.setInt(3, Integer.parseInt(stock));  // Stok sayısını integer olarak alıyoruz
            pstmt.setDouble(4, Double.parseDouble(price));  // Fiyatı double olarak alıyoruz

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product successfully added!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding product to database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AddProductFrame();
    }
}


