package Interface;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class Tela implements ActionListener{
    /*
    Cria instâncias de vários tipos de objetos que serão utilizados nessa classe:
    jframe, para criar uma gui; jlabel, para criar textos; jpanel, para criar painéis,
    que serão usados para delimitar margens na gui e o espaço em que todos os elementos
    ficarão; jbutton, para criar botões
    */
    JFrame tela = new JFrame("Projeto de matemática discreta");
    JLabel texto = new JLabel();
    JLabel textoBotao = new JLabel();
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JPanel painel4 = new JPanel();
    JPanel painel5 = new JPanel();
    JButton botao1 = new JButton("Gerar chave pública");
    JButton botao2 = new JButton("Encriptar texto");
    JButton botao3 = new JButton("Desencriptar texto");
    
    Tela(){
        //Configura o objeto tela
        tela.setVisible(true);
        tela.setSize(920,660);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setResizable(false);
        tela.getContentPane().setBackground(new Color(0,0,0));
        
        //Cria o texto
        texto.setText("Escolha uma das opções abaixo");
        texto.setFont(new Font("Arial",Font.BOLD,40));
        texto.setForeground(Color.white);
        
        //Define o tamanho dos painéis
        painel1.setPreferredSize(new Dimension(150,150));
        painel2.setPreferredSize(new Dimension(150,150));
        painel3.setPreferredSize(new Dimension(100,150));
        painel4.setPreferredSize(new Dimension(100,100));
        painel5.setPreferredSize(new Dimension(100,100));
        
        //Define a cor de todas as margens para preto
        painel1.setBackground(Color.black);
        painel2.setBackground(Color.black);
        painel3.setBackground(Color.black);
        painel4.setBackground(Color.black);
        painel5.setBackground(Color.black);
        
        //Adiciona um verificador de eventos aos objetos dos botões
        botao1.addActionListener(this);
        botao2.addActionListener(this);
        botao3.addActionListener(this);
        
        //Adiciona um layout de margens e define as posições dos painéis
        tela.setLayout(new BorderLayout());
        tela.add(painel1,BorderLayout.NORTH);
        tela.add(painel2,BorderLayout.SOUTH);
        tela.add(painel3,BorderLayout.EAST);
        tela.add(painel4,BorderLayout.WEST);
        tela.add(painel5,BorderLayout.CENTER);
        
        //Adiciona todos os elementos aos painéis
        painel1.add(texto);
        painel5.add(botao1);
        painel5.add(botao2);
        painel5.add(botao3);
        painel5.setLayout(new GridLayout(3,1,50,50));
    }
    
    /*
    Verifica quais botões foram pressionados e cria uma instância de uma classe
    específica dependendo da escolha do usuário
    */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == botao1){
            ChavePublica chavePublica = new ChavePublica("");
            //Cria uma nova instância da classe ChavePublica
        }
        if(e.getSource() == botao2){
            EncriptarTexto escriptarTexto = new EncriptarTexto();
            //Cria uma nova instância da classe EncriptarTexto
        }
        if(e.getSource() == botao3){
            DesencriptarTexto desencriptarTexto = new DesencriptarTexto();
            //Cria uma nova instância da classe DesencriptarTexto
        }
    }
}
