import javax.swing.*;
import java.awt.*;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame(String username) {
        // Ana pencere ayarları
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Arkaplan rengi (Açık Mavi)

        // Başlık
        JLabel titleLabel = new JLabel("Inventory Management System");
        titleLabel.setBounds(200, 10, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel);

        JLabel userLabel = new JLabel( username);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(400, 10, 180, 20); // Sağ üst köşe
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(userLabel);

        // Butonları oluştur
        JButton addButton = new JButton("ADD PRODUCT");
        JButton deleteButton = new JButton("PRODUCT DELETE");
        JButton updateButton = new JButton("PRODUCT UPDATE");
        JButton listButton = new JButton("PRODUCT LIST");

        // Buton konumları
        addButton.setBounds(50, 100, 180, 50);
        deleteButton.setBounds(50, 200, 180, 50);
        updateButton.setBounds(350, 100, 180, 50);
        listButton.setBounds(350, 200, 180, 50);

        // Sayfaya yönlendirme
        addButton.addActionListener(e -> new AddProductFrame());
        deleteButton.addActionListener(e -> new DeleteProductFrame());
        updateButton.addActionListener(e -> new UpdateProductFrame());
        listButton.addActionListener(e -> {
            ProductListFrame frame = new ProductListFrame();
            frame.setVisible(true);
        });



        add(addButton);
        add(deleteButton);
        add(updateButton);
        add(listButton);

        // Pencereyi görünür yapmak için
        setVisible(true);
    }


}
