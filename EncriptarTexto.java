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

public class EncriptarTexto implements ActionListener{
    /*
    Cria instâncias de vários tipos de objetos que serão utilizados nessa classe:
    jframe, para criar uma gui; jlabel, para criar textos; jpanel, para criar painéis,
    que serão usados para delimitar margens na gui e o espaço em que todos os elementos
    ficarão; jbutton, para criar botões; scanner, para fazer leitura de arquivos;
    filewriter, para escrever arquivos; file, para criar abrir um arquivo; ProcessBuilder,
    para executar o executável do backend; jtextfield, para criar espaços para que
    o usuário possa digitar textos
    */
    JFrame tela = new JFrame();    
    ProcessBuilder executa = new ProcessBuilder("./encriptar.exe");    
    JButton botao = new JButton("Encriptar");
    JButton botaoVolta = new JButton("Voltar");    
    FileWriter escrever;
    FileWriter escreverChave;   
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JPanel painel4 = new JPanel();
    JPanel painel5 = new JPanel();   
    JTextField digitarMensagem = new JTextField();
    JTextField digitarN = new JTextField();
    JTextField digitarE = new JTextField();
    JLabel textoMens = new JLabel();
    JLabel textoN = new JLabel();
    JLabel textoE = new JLabel();
    
    EncriptarTexto(){
        //Configura a fonte, o tamanho e a cor dos textos
        textoMens.setText("Digite a mensagem a ser encriptada");
        textoMens.setFont(new Font("Arial",Font.BOLD,18));
        textoMens.setForeground(Color.white);
        
        textoN.setText("Digite o número N");
        textoN.setFont(new Font("Arial",Font.BOLD,18));
        textoN.setForeground(Color.white);
        
        textoE.setText("Digite o número E");
        textoE.setFont(new Font("Arial",Font.BOLD,18));
        textoE.setForeground(Color.white);
        
        //Adiciona um veriricador de eventos aos botões
        botao.addActionListener(this);
        botaoVolta.addActionListener(this);
        
        //Define o tamanho dos painéis
        painel1.setPreferredSize(new Dimension(75,75));
        painel2.setPreferredSize(new Dimension(75,75));
        painel3.setPreferredSize(new Dimension(100,150));
        painel4.setPreferredSize(new Dimension(100,100));
        painel5.setPreferredSize(new Dimension(100,100));
        
        //Define a cor das margens para preto
        painel1.setBackground(Color.black);
        painel2.setBackground(Color.black);
        painel3.setBackground(Color.black);
        painel4.setBackground(Color.black);
        painel5.setBackground(Color.black);
        
        //Configura o objeto tela
        tela.setVisible(true);
        tela.setSize(920,660);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setResizable(true);
        tela.getContentPane().setBackground(new Color(0,0,0));
        
        //Adiciona todos os painéis à tela como margens
        tela.setLayout(new BorderLayout());
        tela.add(painel1,BorderLayout.NORTH);
        tela.add(painel2,BorderLayout.SOUTH);
        tela.add(painel3,BorderLayout.EAST);
        tela.add(painel4,BorderLayout.WEST);
        tela.add(painel5,BorderLayout.CENTER);
        
        //Adiciona os elementos ao painel central
        painel5.add(botao);
        painel5.add(textoMens);
        painel5.add(digitarMensagem);
        painel5.add(textoN);
        painel5.add(digitarN);
        painel5.add(textoE);
        painel5.add(digitarE);
        painel5.add(botaoVolta);
        painel5.setLayout(new GridLayout(8,1,15,15));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == botao){
            try{
                escrever = new FileWriter("./paraBack/mensagemPura.txt");
                escrever.write(digitarMensagem.getText());
                escrever.close();
                
                escreverChave = new FileWriter("./paraBack/chaveCripto.txt");
                escreverChave.write(digitarN.getText()+ " " + digitarE.getText());
                escreverChave.close();            
                
                tela.dispose();
            }catch (IOException f){
                f.printStackTrace();
            }
            
            try{
                Process processo = executa.start();
                int status = processo.waitFor();
                if(status != 0){
                    System.out.println("erro");
                }
            }catch(Exception f){
                f.printStackTrace();
            }
        }
        
        if(e.getSource() == botaoVolta){
            tela.dispose();
        }
    }
}
