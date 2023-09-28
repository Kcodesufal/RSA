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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChavePublica implements ActionListener{
    /*
    Cria instâncias de vários tipos de objetos que serão utilizados nessa classe:
    jframe, para criar uma gui; jlabel, para criar textos; jpanel, para criar painéis,
    que serão usados para delimitar margens na gui e o espaço em que todos os elementos
    ficarão; jbutton, para criar botões; scanner, para fazer leitura de arquivos;
    filewriter, para escrever arquivos; file, para criar abrir um arquivo; ProcessBuilder,
    para executar o executável do backend; jtextfield, para criar espaços para que
    o usuário possa digitar textos
    */
    Scanner ler;    
    ProcessBuilder executa = new ProcessBuilder("./chaveP.exe");    
    JFrame tela = new JFrame();    
    FileWriter escreverP;
    FileWriter escreverQ;
    FileWriter escreverE;    
    File verifica;    
    JButton botao = new JButton("Confirmar P, Q e E");
    JButton botaoVolta = new JButton("Voltar");    
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JPanel painel4 = new JPanel();
    JPanel painel5 = new JPanel();   
    JLabel textoP = new JLabel();
    JLabel textoQ = new JLabel();
    JLabel textoE = new JLabel();
    JLabel mensagemErro = new JLabel();    
    JTextField digitarP = new JTextField();
    JTextField digitarQ = new JTextField();
    JTextField digitarE = new JTextField();
    

    ChavePublica(String mensagem){
        //Adiciona um verificador de eventos aos objetos dos botões
        botao.addActionListener(this);
        botaoVolta.addActionListener(this);
        
        //Cria textos para orientar o usuário
        textoP.setText("Digite um número primo P");
        textoQ.setText("Digite um número primo Q");
        textoE.setText("Digite um número E relativamente primo a (p-1)(q-1)");
        mensagemErro.setText(mensagem);
        
        //Define a cor dos textos
        textoP.setForeground(Color.white);
        textoQ.setForeground(Color.white);
        textoE.setForeground(Color.white);
        mensagemErro.setForeground(Color.white);
        
        //Define a fonte e o tamanho dos textos
        textoP.setFont(new Font("Arial",Font.BOLD,18));
        textoQ.setFont(new Font("Arial",Font.BOLD,18));
        textoE.setFont(new Font("Arial",Font.BOLD,18));
        mensagemErro.setFont(new Font("Arial",Font.BOLD,18));
        
        //Define o tamanho dos painéis
        painel1.setPreferredSize(new Dimension(50,50));
        painel2.setPreferredSize(new Dimension(50,50));
        painel3.setPreferredSize(new Dimension(100,150));
        painel4.setPreferredSize(new Dimension(100,100));
        painel5.setPreferredSize(new Dimension(100,100));
        
        //Define a cor de todas as margens para preto
        painel1.setBackground(Color.black);
        painel2.setBackground(Color.black);
        painel3.setBackground(Color.black);
        painel4.setBackground(Color.black);
        painel5.setBackground(Color.black);
        
        //Configura o objeto tela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setResizable(true);
        tela.setSize(920, 660);
        tela.getContentPane().setBackground(new Color(0,0,0));
        tela.setLayout(new BorderLayout());
        tela.setVisible(true);
        
        //Define as posições dos painéis
        tela.add(painel1,BorderLayout.NORTH);
        tela.add(painel2,BorderLayout.SOUTH);
        tela.add(painel3,BorderLayout.EAST);
        tela.add(painel4,BorderLayout.WEST);
        tela.add(painel5,BorderLayout.CENTER);
        
        //Adiciona todos os elementos aos painéis
        painel5.add(botao);
        painel5.add(textoP);
        painel5.add(digitarP);
        painel5.add(textoQ);
        painel5.add(digitarQ);
        painel5.add(textoE);
        painel5.add(digitarE);
        painel5.add(botaoVolta);
        painel5.add(mensagemErro);        
        painel5.setLayout(new GridLayout(9,4,10,10));
    }
    
    /*
    Verifica quais botões foram pressionados e realiza os comandos apropriados
    */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == botao){
            /*
            Quando o botao de confirmar os dados é pressionado, o executável do
            backend é executado
            */
            try{
                escreverP = new FileWriter("./paraBack/p.txt");
                escreverP.write(digitarP.getText());
                escreverP.close();
                
                escreverQ = new FileWriter("./paraBack/q.txt");
                escreverQ.write(digitarQ.getText());
                escreverQ.close();
                
                escreverE = new FileWriter("./paraBack/e.txt");
                escreverE.write(digitarE.getText());
                escreverE.close();
            }catch (IOException f) {
                f.printStackTrace();
            }
            
            try{
                Process processo = executa.start();
                int status = processo.waitFor();
                if(status != 0){
                    System.out.print("erro");
                }
            }catch(Exception f){
                f.printStackTrace();
            }
            
            verifica = new File("./paraBack/verifica.txt");
            try {
                ler = new Scanner(verifica);
            } catch (FileNotFoundException f) {
                f.printStackTrace();
            }
            
            String validar = ler.nextLine();
            
            if(validar.equals("erro")){
                tela.dispose();
                new ChavePublica("Por favor, digite dois número primos diferentes e um número E válido");
            }
            else{
                tela.dispose();
            }
        }
        
        //Quando o botão "voltar" é pressionado, o programa fecha a tela dessa classe
        if(e.getSource() == botaoVolta){
            tela.dispose();
        }
    }
}
