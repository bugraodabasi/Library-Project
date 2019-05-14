/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import javax.swing.*;
import java.awt.*;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sun.java2d.pipe.DrawImage;


public class Kitaplik extends JFrame {

    private JList list;
    private JLabel l1,l2,anasayfa_resim,gl_id,gl_sifre,kel_yazar,kel_kitap_adi,kel_tur,kel_resim_yolu,kel_yili,kitap_resim,ks_kitap_adi,ka_yazar,kal_kitap_adi,kal_yazar,kal_tur,kal_yayim_yili,akr1,c1,c2,kitaplik_l1,kitaplik_l2,kitaplik_l3,kitaplik_l4,kitaplik_l5,kitaplik_l6;
    
    private JTable tablo,tablo2;
    private JScrollPane sp,sp2;
    
    private JTabbedPane TabMenu;
    private JPanel anasayfa,kitaplik,kitap_ekleme,kitap_silme,kitap_arama,kitap_listele,tablo_panel,kitap_arama_panel,genel_panel,sol,sag,ak1,ak2,ak3,ak4,ak5,ak6;
    
    private JTextField id,ket_yazar,ket_kitap_adi,ket_tur,ket_resim_yolu,ket_yili,ka_kitap_adi;
    private JPasswordField sifre;
    private JButton giris_buton,kitap_ekle_buton,kitap_sil_b,kitap_arama_b,kitap_listele_b;
    
    private File dosya_kitaplik,dosya_kitap_ekleme;
    private ArrayList<String>kullanicilar,kitaplar;
    
    private JComboBox kitap_silme_c,cb_yazar,cb_tur,ke_cb;
    private JRadioButton rb_yazar,rb_tur;
    
    
    
    private JCheckBox yazar_cb,tur_cb;
    private boolean giris_kontrol;
    
    private JOptionPane j1;
    
    private DefaultTableModel model;
    
    private ImageIcon img,img2;
    
    private JLabel arama_resim;
    
    
  
    
    public Kitaplik(File dosya_kitaplik,ArrayList<String> kitaplar) throws FileNotFoundException, IOException 
    {  
        
        Scanner oku=new Scanner(dosya_kitaplik);
        while(oku.hasNext())
        {
            String cikti=oku.nextLine();
            kitaplar.add(cikti);
        }
        
        new JFrame("Kütüphane");
        setSize(900,511);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        anasayfa=new JPanel();
        anasayfa.setLayout(null);
        anasayfa.setBackground(Color.LIGHT_GRAY);
        kitaplik=new JPanel();
        kitaplik.setLayout(null);
        kitaplik.setBackground(Color.LIGHT_GRAY);
        kitap_ekleme=new JPanel();
        kitap_ekleme.setLayout(null);
        kitap_ekleme.setBackground(Color.LIGHT_GRAY);
        kitap_silme=new JPanel();
        kitap_silme.setLayout(null);
        kitap_silme.setBackground(Color.LIGHT_GRAY);
        kitap_arama=new JPanel();
        kitap_arama.setLayout(null);
        kitap_arama.setBackground(Color.LIGHT_GRAY);
        kitap_listele=new JPanel();
        kitap_listele.setLayout(null);
        kitap_listele.setBackground(Color.LIGHT_GRAY);
        
        TabMenu=new JTabbedPane();
        
        j1=new JOptionPane();
        
        
        anasayfa();
        
        kitaplik(dosya_kitaplik,kitaplar);
        
        kitap_ekleme();
        
        kitap_silme(kitaplar);
        
        kitap_arama();
        
        kitap_listeleme();
        
        alt_kisim();
   
        
        
        add(TabMenu);
        setVisible(true);
        
        
    }
    public void anasayfa()
    {
        TabMenu.addTab("ANASAYFA",anasayfa);//anasayfa panel

     
        
        ImageIcon ImageIcon=new ImageIcon("C:\\Users\\Fe\\Desktop\\resimler\\LOGO1.png");
        anasayfa_resim=new JLabel(ImageIcon);
        anasayfa_resim.setBounds(100, 30, 300, 300);
        anasayfa.add(anasayfa_resim);
                
        
        gl_id=new JLabel("Kullanıcı Adı");
        gl_id.setBounds(550, 120, 100, 20);
        anasayfa.add(gl_id);
        
        gl_sifre=new JLabel("Şifre");
        gl_sifre.setBounds(550, 150, 100, 20);
        anasayfa.add(gl_sifre);
        
        id=new JTextField();
        id.setBounds(650, 120, 100, 20);
        anasayfa.add(id);
        
        sifre=new JPasswordField();
        sifre.setBounds(650, 150, 100, 20);
        anasayfa.add(sifre);
        
        giris_buton=new JButton("GİRİŞ");
        giris_buton.setBounds(615, 185, 70, 25);
        anasayfa.add(giris_buton);
        
        c2=new JLabel("HOŞGELDİN ADMİN");
        c2.setBounds(610, 150, 150, 20);
        c2.setVisible(false);
        anasayfa.add(c2);
        
        giris_kontrol=false;
        
        giris_buton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
           String pass=new String(sifre.getPassword());
            if(id.getText().equals("admin"))
            {
                if(pass.equals("12345"))
                {
                    j1.showMessageDialog(null,"Giriş Başarılı");
                    id.setText("");
                    id.setVisible(false);
                    sifre.setText("");
                    sifre.setVisible(false);
                    giris_buton.setVisible(false);
                    gl_id.setVisible(false);
                    gl_sifre.setVisible(false);
                    
                    c2.setVisible(true);
                    giris_kontrol=true;//sisteme girildi
                }
            }
            
            if(giris_kontrol!=true)
            {
                j1.showMessageDialog(null,"Kullanıcı Adı veya Şifre Hatalı!!!!");
            }
            
              
        } 
        });
    }
    
    public void kitaplik(File dosya_kitaplik,ArrayList<String> kitaplar)
    {
        TabMenu.addTab("KİTAPLİK",kitaplik);//kitaplık panel

        this.dosya_kitaplik=dosya_kitaplik;
        this.dosya_kitaplik=new File("kitap.txt");
        
        this.kitaplar=kitaplar;
        this.kitaplar=new ArrayList<String>();
        
        
        sol=new JPanel();// **************************************************
        sol.setLayout(new BorderLayout());                    //SOL PANEL
        sol.setBounds(0, 0, 600, 400);
        sol.setBorder(new TitledBorder(new LineBorder(Color.gray, 2)));//kenarlık
        
        
        model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("Kitap Adı");
        model.addColumn("Yazar");
        model.addColumn("Tür");
        model.addColumn("Resim Yolu");
        model.addColumn("Basım Yılı");
        tablo = new JTable(model);
        tablo.setLayout(new BorderLayout());
        sp=new JScrollPane(tablo);
        sol.add(sp,BorderLayout.CENTER);
        
        int x=0,i=1;          
            do{
                model.addRow(new Object[]{String.valueOf(i),kitaplar.get(x),kitaplar.get(x+1),kitaplar.get(x+2),kitaplar.get(x+3),kitaplar.get(x+4)});
                x=x+5;
                i++;
            }while(x<kitaplar.size());
        kitaplik.add(sol);// **********************************************
        
        
        
       
        
        sag=new JPanel();// *************************************************
        sag.setLayout(null);                 //SAĞ PANEL
        sag.setBounds(590, 0, 290, 400);
        sag.setBorder(new TitledBorder(new LineBorder(Color.gray, 2)));//kenarlık
           
        img=new ImageIcon("");
        kitap_resim=new JLabel();
        kitap_resim.setIcon(img);
        kitap_resim.setBounds(50,10, 200, 200);
        sag.add(kitap_resim);
        
        kitaplik_l2=new JLabel();
        kitaplik_l2.setBounds(50, 230, 200, 20);
        sag.add(kitaplik_l2);
        
        kitaplik_l3=new JLabel();
        kitaplik_l3.setBounds(50, 260, 200, 20);
        sag.add(kitaplik_l3);
        
        kitaplik_l4=new JLabel();
        kitaplik_l4.setBounds(50, 290, 200, 20);
        sag.add(kitaplik_l4);
        
        kitaplik_l5=new JLabel();
        kitaplik_l5.setBounds(50, 320, 200, 20);
        sag.add(kitaplik_l5);
        
        kitaplik_l6=new JLabel();
        kitaplik_l6.setBounds(50, 350, 200, 20);
        sag.add(kitaplik_l6);
        
        
        
        //tablo.setCellSelectionEnabled(true);
        ListSelectionModel tablo_secim = tablo.getSelectionModel();
        tablo_secim.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablo_secim.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
        String selectedData = null;
        
        
        int[] selectedRow = tablo.getSelectedRows();
        int[] selectedColumns = tablo.getSelectedColumns();
        
        ArrayList<String>tablo_secim_dizi=new ArrayList<String>();

        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
                    selectedData = (String) tablo.getValueAt(selectedRow[i], 0);                  
                }
        }    
        //listModel.removeAllElements();
       
       
        for(int i=1;i<6;i++)
        {
            tablo_secim_dizi.add((String) tablo.getValueAt(tablo_secim.getAnchorSelectionIndex(),i));
            //listModel.addElement((String) tablo.getValueAt(Integer.valueOf(selectedData)-1,i));
        }
        
        for(int i=0;i<tablo_secim_dizi.size();i=+5)
        {
           
            //kitap_resim.setIcon(new ImageIcon("C:\\Users\\Fe\\Desktop\\resimler\\"+tablo_secim_dizi.get(i)+".jpg"));
            
            kitap_resim.setIcon(new ImageIcon(tablo_secim_dizi.get(i+3)));
            
            
            
            //kitaplik_l1.setText(tablo_secim_dizi.get(i));
            kitaplik_l2.setText(tablo_secim_dizi.get(i));
            kitaplik_l3.setText(tablo_secim_dizi.get(i+1));
            kitaplik_l4.setText(tablo_secim_dizi.get(i+2));
            kitaplik_l5.setText(tablo_secim_dizi.get(i+3));
            kitaplik_l6.setText(tablo_secim_dizi.get(i+4));
            
        }
        
       
        
        
  
        }

           

        });
        
        

        kitaplik.add(sag);// **************************************************
    }
    
    public void kitap_ekleme()
    {
        TabMenu.addTab("KİTAP EKLEME",kitap_ekleme);
        kel_kitap_adi=new JLabel("Kitap Adı");//******************Kitap Ekleme*************************
        kel_kitap_adi.setBounds(280, 90, 100, 20);
        kitap_ekleme.add(kel_kitap_adi);
        
        kel_yazar=new JLabel("Yazar Adı");
        kel_yazar.setBounds(280, 120, 100, 20);
        kitap_ekleme.add(kel_yazar);
        
        kel_tur=new JLabel("Tür");
        kel_tur.setBounds(280, 150, 100, 20);
        kitap_ekleme.add(kel_tur);
       
        kel_resim_yolu=new JLabel("Resim Yolu");
        kel_resim_yolu.setBounds(280, 180, 100, 20);
        kitap_ekleme.add(kel_resim_yolu);
        
        kel_yili=new JLabel("Yayım Yılı");
        kel_yili.setBounds(280, 210, 100, 20);
        kitap_ekleme.add(kel_yili);


        ket_kitap_adi=new JTextField();
        ket_kitap_adi.setBounds(420, 90, 150, 20);
        kitap_ekleme.add(ket_kitap_adi);
        
        ket_yazar=new JTextField();
        ket_yazar.setBounds(420, 120, 150, 20);
        kitap_ekleme.add(ket_yazar);
        
        /*ket_tur=new JTextField();
        ket_tur.setBounds(420, 150, 150, 20);
        kitap_ekleme.add(ket_tur);*/
        ke_cb=new JComboBox();
        ke_cb.setBounds(420, 150, 150, 20);
        
       ke_cb.addItem("Polisiye");      
       ke_cb.addItem("Gizem");
       ke_cb.addItem("Felsefi Roman");
       ke_cb.addItem("Edebi Kurgu");
       ke_cb.addItem("Parodi");
       ke_cb.addItem("Tarihi Kurgu");
       ke_cb.addItem("Drama");
       ke_cb.addItem("Aşk Romanı");
       ke_cb.addItem("Realizm");
       ke_cb.addItem("Kurgu");
       ke_cb.addItem("Oyun Romanı");
       ke_cb.addItem("Macera");
       ke_cb.addItem("Savaş Hikayesi");
       ke_cb.addItem("Psikoloji");
       ke_cb.addItem("Epik");
       ke_cb.addItem("Hiciv");
       ke_cb.addItem("Gizem");
       kitap_ekleme.add(ke_cb);
        
        ket_resim_yolu=new JTextField();
        ket_resim_yolu.setBounds(420, 180, 150, 20);
        kitap_ekleme.add(ket_resim_yolu);
        
        ket_yili=new JTextField();
        ket_yili.setBounds(420, 210, 150, 20);
        kitap_ekleme.add(ket_yili);
        

        kitap_ekle_buton=new JButton("Kitap Ekle");
        kitap_ekle_buton.setBounds(375, 250, 100, 20);
        kitap_ekleme.add(kitap_ekle_buton);
        
        
        
        kitap_ekle_buton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            File dosya_ekle=new File("kitap.txt");
            
            cb_yazar.addItem(ket_yazar.getText());
            
            kitaplar.add(ket_kitap_adi.getText());
            kitaplar.add(ket_yazar.getText());            
            kitaplar.add((String) ke_cb.getSelectedItem());
            kitaplar.add(ket_resim_yolu.getText());
            kitaplar.add(ket_yili.getText());
            
            model.addRow(new Object[]{model.getRowCount()+1,ket_kitap_adi.getText(),ket_yazar.getText(),(String) ke_cb.getSelectedItem(),ket_resim_yolu.getText(),ket_yili.getText()});
            
            kitap_silme_c.addItem(ket_kitap_adi.getText());
            
            FileWriter writer=null;
            try{
                writer = new FileWriter(dosya_ekle);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                for(int i=0;i<kitaplar.size();i++)
                {
                    
                    writer.write(kitaplar.get(i)+"\n");
                    
                    
                }
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try{
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            j1.showMessageDialog(null,"Kitap Eklendi");
        }
        });
        
    }
    public void kitap_silme(ArrayList<String> kitaplar)
    {
        TabMenu.addTab("KİTAP SİLME",kitap_silme);
        
        this.kitaplar=kitaplar;
        
        ArrayList<String> kitap_silme_dizi=new ArrayList<String>();
        
        kitap_silme_c=new JComboBox();
        kitap_silme_c.setBounds(220, 100, 300, 20);
        for(int k=0;k<kitaplar.size();k=k+5)
        {       
            kitap_silme_c.addItem(kitaplar.get(k));//kitapların adı       
        }
        
        ks_kitap_adi=new JLabel("SİLMEK İSTEDİĞİNİZ KİTABIN ADINI SEÇİN");
        ks_kitap_adi.setBounds(295, 60, 300, 20);
        kitap_silme.add(ks_kitap_adi);
        
        
        
        kitap_silme_c.addActionListener(new ActionListener(){//combo boxdan seçildiğinde
        public void actionPerformed(ActionEvent e)
        {
            String deger=(String) kitap_silme_c.getSelectedItem();
   
        }
        });
        kitap_silme.add(kitap_silme_c);
        
        kitap_sil_b=new JButton("SİL");
        kitap_sil_b.setBounds(530, 100, 70, 20);
        kitap_silme.add(kitap_sil_b);
        
        l2=new JLabel();
        l2.setBounds(80, 80, 400, 20);
        kitap_silme.add(l2);
        
        
        
       
        
        kitap_sil_b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            if(giris_kontrol==true)
            {
                String deger=(String) kitap_silme_c.getSelectedItem();
                int sayac=0;
                for(int xy=0;xy<kitaplar.size();xy=xy+5)
                {
                    if(kitaplar.get(xy).equals(deger))
                    {
                        model.removeRow(sayac);//tablodan çıkarmak için
                    }
                    sayac++;
                }
                for(int c=0;c<kitaplar.size();c++)
                {
                    kitap_silme_dizi.add(kitaplar.get(c));
                }
                kitaplar.clear();
            
                for(int a=0;a<kitap_silme_dizi.size();a=a+5)
                {
                    if(kitap_silme_dizi.get(a).equals(deger))
                    {
                        continue;
                    }
                    else
                    {
                        kitaplar.add(kitap_silme_dizi.get(a));
                        kitaplar.add(kitap_silme_dizi.get(a+1));
                        kitaplar.add(kitap_silme_dizi.get(a+2));
                        kitaplar.add(kitap_silme_dizi.get(a+3));
                        kitaplar.add(kitap_silme_dizi.get(a+4));
                    }
                }
            
                kitap_silme_dizi.clear();
            
                File dosya_silme=new File("kitap.txt");
                FileWriter writer=null;
                try{
                    writer = new FileWriter(dosya_silme);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    for(int i=0;i<kitaplar.size();i++)
                    {           
                        writer.write(kitaplar.get(i)+"\n");
                    }
                
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try{
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            
                j1.showMessageDialog(null,"Kitap Silindi");
            
                kitap_silme_c.removeAllItems();
                for(int ab=0;ab<kitaplar.size();ab=ab+5)
                {
                    kitap_silme_c.addItem(kitaplar.get(ab));
                }
            }
            else
            {
                j1.showMessageDialog(null,"Kitap Silme İşlemini Sadece Admin Gerçekleştirebilir!!!!!!");
            }
            
        }
        });
    }
    
    public void kitap_arama()
    {
        TabMenu.addTab("KİTAP ARAMA",kitap_arama);
        
        kitap_arama_panel=new JPanel();
        kitap_arama_panel.setBounds(165, 100, 500, 280);
        kitap_arama_panel.setLayout(null);
        kitap_arama_panel.setBorder(new TitledBorder(new LineBorder(Color.gray, 2),"ARANAN KİTAP"));
        kitap_arama.add(kitap_arama_panel);
        
        ka_yazar=new JLabel("ARAMAK İSTEDİĞİNİZ KİTABIN ADINI GİRİN");    
        ka_yazar.setBounds(307, 40, 300, 20);
        kitap_arama.add(ka_yazar);
        
        
        ka_kitap_adi=new JTextField();
        ka_kitap_adi.setBounds(310, 70, 150, 20);
        kitap_arama.add(ka_kitap_adi);
        
        kitap_arama_b=new JButton("ARA");
        kitap_arama_b.setBounds(470,70,70,20);
        kitap_arama.add(kitap_arama_b);
        
        img2=new ImageIcon();
        arama_resim=new JLabel();
        arama_resim.setBounds(40, 40, 200, 200);
        arama_resim.setIcon(img2);
        kitap_arama_panel.add(arama_resim); 
        
        kal_kitap_adi=new JLabel();
        kal_kitap_adi.setBounds(270, 60, 100, 20);
        kitap_arama_panel.add(kal_kitap_adi);
        
        kal_yazar=new JLabel();
        kal_yazar.setBounds(270, 90, 180, 20);
        kitap_arama_panel.add(kal_yazar);
        
        kal_tur=new JLabel();
        kal_tur.setBounds(270, 120, 100, 20);
        kitap_arama_panel.add(kal_tur);
        
        kal_yayim_yili=new JLabel();
        kal_yayim_yili.setBounds(270, 150, 100, 20);
        kitap_arama_panel.add(kal_yayim_yili);
        
        
        
        kitap_arama_b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            boolean kontrol=false;
            String cekilen=ka_kitap_adi.getText();
            for(int i=0;i<kitaplar.size();i=i+5)
            {
                if(kitaplar.get(i).equals(cekilen))
                {
                    kal_kitap_adi.setText(kitaplar.get(i));
                    kal_yazar.setText(kitaplar.get(i+1));
                    kal_tur.setText(kitaplar.get(i+2));
                    arama_resim.setIcon(new ImageIcon(kitaplar.get(i+3)));
                    kal_yayim_yili.setText(kitaplar.get(i+4));
                    
                    
                    
                    j1.showMessageDialog(null,"Kitap Bulundu");
                    kontrol=true;
                    break;
                }
            }
            if(kontrol!=true)
            {
                kal_kitap_adi.setText("");
                kal_yazar.setText("");
                kal_tur.setText("");
                  
                kal_yayim_yili.setText("");
                j1.showMessageDialog(null,"Kitap Bulunamadı!!!!");
            }
            
        }
        });
    }
    
    public void kitap_listeleme()
    {
       TabMenu.addTab("KİTAP LİSTELEME",kitap_listele);
       
       ArrayList<String> kitap_turleri_dizi=new ArrayList<String>();
       
       

       yazar_cb=new JCheckBox("Yazar");
       yazar_cb.setBounds(210, 50, 100, 20);
       kitap_listele.add(yazar_cb);
       
       tur_cb=new JCheckBox("Tür");
       tur_cb.setBounds(210, 80, 100, 20);
       kitap_listele.add(tur_cb);
       
       cb_yazar=new JComboBox();
       cb_yazar.setBounds(320, 50, 200, 20);
      
       kitap_listele.add(cb_yazar);
       
       cb_tur=new JComboBox();
       cb_tur.setBounds(320, 80, 200, 20);
       
       kitap_listele.add(cb_tur);
       
       kitap_listele_b=new JButton("LİSTELE");
       kitap_listele_b.setBounds(540, 60, 90, 25);
       kitap_listele.add(kitap_listele_b);
       
       
       
       for(int s=0;s<kitaplar.size();s=s+5)
       {
           cb_yazar.addItem(kitaplar.get(s+1));
           
       }
       kitap_turleri_dizi.add("Polisiye");      
       kitap_turleri_dizi.add("Gizem");
       kitap_turleri_dizi.add("Felsefi Roman");
       kitap_turleri_dizi.add("Edebi Kurgu");
       kitap_turleri_dizi.add("Parodi");
       kitap_turleri_dizi.add("Tarihi Kurgu");
       kitap_turleri_dizi.add("Drama");
       kitap_turleri_dizi.add("Aşk Romanı");
       kitap_turleri_dizi.add("Realizm");
       kitap_turleri_dizi.add("Kurgu");
       kitap_turleri_dizi.add("Oyun Romanı");
       kitap_turleri_dizi.add("Macera");
       kitap_turleri_dizi.add("Savaş Hikayesi");
       kitap_turleri_dizi.add("Psikoloji");
       kitap_turleri_dizi.add("Epik");
       kitap_turleri_dizi.add("Hiciv");
       kitap_turleri_dizi.add("Gizem");
       for(int s=0;s<kitap_turleri_dizi.size();s++)
       {
           cb_tur.addItem(kitap_turleri_dizi.get(s));
           
       }
       
       
       
       tablo_panel=new JPanel();
       tablo_panel.setBounds(0, 130, 880, 270);
       tablo_panel.setLayout(new BorderLayout());  
       
       
       
       DefaultTableModel model2 = new DefaultTableModel();
       
        model2.addColumn("Kitap Adı");
        model2.addColumn("Yazar");
        model2.addColumn("Tür");
        model2.addColumn("Resim Yolu");
        model2.addColumn("Basım Yılı");
        tablo2 = new JTable(model2);
        tablo2.setLayout(new BorderLayout());
        sp2=new JScrollPane(tablo2);
        tablo_panel.add(sp2,BorderLayout.CENTER);
        
        kitap_listele_b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            while(model2.getRowCount()>0)
            {
                for(int i=0;i<model2.getRowCount();i++)
                {
                    model2.removeRow(i);
                }
            }
            if(yazar_cb.isSelected()==true && tur_cb.isSelected()==true)
            {
                int q=1;          
                do{
                if(kitaplar.get(q).equals((String) cb_yazar.getSelectedItem()) && kitaplar.get(q+1).equals((String) cb_tur.getSelectedItem()))
                {
                    model2.addRow(new Object[]{kitaplar.get(q-1),kitaplar.get(q),kitaplar.get(q+1),kitaplar.get(q+2),kitaplar.get(q+3)});

                }
                q=q+5;
                
                }while(q<kitaplar.size());
            }     
            else if(tur_cb.isSelected()==true)
            {
                int q=2;          
                do{
                if(kitaplar.get(q).equals((String) cb_tur.getSelectedItem()))
                {
                    model2.addRow(new Object[]{kitaplar.get(q-2),kitaplar.get(q-1),kitaplar.get(q),kitaplar.get(q+1),kitaplar.get(q+2)});

                }
                q=q+5;
                
                }while(q<kitaplar.size());
            }
            else if(yazar_cb.isSelected()==true)
            {
                int q=1;          
                do{
                if(kitaplar.get(q).equals((String) cb_yazar.getSelectedItem()))
                {
                    model2.addRow(new Object[]{kitaplar.get(q-1),kitaplar.get(q),kitaplar.get(q+1),kitaplar.get(q+2),kitaplar.get(q+3)});

                }
                q=q+5;
                
                }while(q<kitaplar.size());
            }
            
            
            
            
        }
        });
        kitap_listele.add(tablo_panel);
        
    }
    public void alt_kisim()
    {
        ak1=new JPanel();
        ak1.setBounds(2, 426, 900, 42);
        ak1.setLayout(null);
        
        c1=new JLabel("Çıkış");
        c1.setBounds(820, 11, 100, 20);
        ak1.add(c1);
        
        akr1=new JLabel(new ImageIcon("C:\\Users\\Fe\\Desktop\\resimler\\panel_alt.png"));
        akr1.setBounds(0, 0, 900, 42);
        ak1.add(akr1);
        
        
        c1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//mouse ucu değiştirme
        c1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
               if(giris_kontrol!=false)
                {
                    j1.showMessageDialog(null,"Çıkış Başarılı.");
                
                
                    giris_kontrol=false;
                    id.setVisible(true);
                    sifre.setVisible(true);
                    giris_buton.setVisible(true);
                    gl_id.setVisible(true);
                    gl_sifre.setVisible(true);
                    c2.setVisible(false);
                }
                else
                {
                    j1.showMessageDialog(null,"Lütfen Önce Giriş Yapın.");
                }
        
            }

        });
        
        add(ak1);
    }
    
    
   
    
    
    
   
  
}
