package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DesencriptarTexto implements ActionListener{
    JFrame tela = new JFrame();
    
    ProcessBuilder executa = new ProcessBuilder("./descriptar.exe");
    
    JButton botao = new JButton("Desencriptar");
    JButton botaoVolta = new JButton("Voltar");
    
    FileWriter escrever;
    FileWriter escreverNumeros;    
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JPanel painel4 = new JPanel();
    JPanel painel5 = new JPanel();    
    JTextField digitarMensagem = new JTextField();
    JTextField digitarP = new JTextField();
    JTextField digitarQ = new JTextField();
    JTextField digitarE = new JTextField();   
    JLabel textoMens = new JLabel();
    JLabel textoP = new JLabel();
    JLabel textoQ = new JLabel();
    JLabel textoE = new JLabel();
    
    DesencriptarTexto(){
        botao.addActionListener(this);
        botaoVolta.addActionListener(this);
        
        textoMens.setText("Digite a mensagem a ser desencriptada");
        textoMens.setFont(new Font("Arial",Font.BOLD,18));
        textoMens.setForeground(Color.white);
        
        textoP.setText("Digite o número P");
        textoP.setFont(new Font("Arial",Font.BOLD,18));
        textoP.setForeground(Color.white);
        
        textoQ.setText("Digite o número Q");
        textoQ.setFont(new Font("Arial",Font.BOLD,18));
        textoQ.setForeground(Color.white);
        
        textoE.setText("Digite o número E");
        textoE.setFont(new Font("Arial",Font.BOLD,18));
        textoE.setForeground(Color.white);
        
        painel1.setPreferredSize(new Dimension(50,50));
        painel2.setPreferredSize(new Dimension(50,50));
        painel3.setPreferredSize(new Dimension(100,150));
        painel4.setPreferredSize(new Dimension(100,100));
        painel5.setPreferredSize(new Dimension(100,100));
        
        painel1.setBackground(Color.black);
        painel2.setBackground(Color.black);
        painel3.setBackground(Color.black);
        painel4.setBackground(Color.black);
        painel5.setBackground(Color.black);
        
        tela.setVisible(true);
        tela.setSize(920,660);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setResizable(false);
        tela.getContentPane().setBackground(new Color(0,0,0));
        
        tela.setLayout(new BorderLayout());
        tela.add(painel1,BorderLayout.NORTH);
        tela.add(painel2,BorderLayout.SOUTH);
        tela.add(painel3,BorderLayout.EAST);
        tela.add(painel4,BorderLayout.WEST);
        tela.add(painel5,BorderLayout.CENTER);
        
        painel5.add(botao);
        painel5.add(textoMens);
        painel5.add(digitarMensagem);
        painel5.add(textoP);
        painel5.add(digitarP);
        painel5.add(textoQ);
        painel5.add(digitarQ);
        painel5.add(textoE);
        painel5.add(digitarE);
        painel5.add(botaoVolta);
        painel5.setLayout(new GridLayout(10,1,10,10));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == botao){
            try{
                escrever = new FileWriter("./paraBack/mensagemEncriptada.txt");
                escrever.write(digitarMensagem.getText());
                escrever.close();
                
                escreverNumeros = new FileWriter("./paraBack/chaveDescripto.txt");
                escreverNumeros.write(digitarP.getText()+" "+digitarQ.getText()+" "+digitarE.getText());
                escreverNumeros.close();
                
                try{
                    Process processo = executa.start();
                    int status = processo.waitFor();
                    if(status != 0){
                        System.out.println("erro");
                    }
                }catch(Exception f){
                    f.printStackTrace();
                }
                
                tela.dispose();
            }catch(IOException f){
                f.printStackTrace();
            }
        }
        
        if(e.getSource() == botaoVolta){
            tela.dispose();
        }
    }
}
