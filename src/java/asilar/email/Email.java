package asilar.email;

import asilar.model.entity.Usuario;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import ldsutils.TXTPersist;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Email extends Thread {

    Usuario interno = new Usuario();

    public Email(Usuario entity) {
        interno = entity;
    }

    private String username;
    private String senha;

    @Override
    public void run() {
        try {
            this.lerConfig();
            this.executar(interno);
        } catch (EmailException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void executar(Usuario entity) throws EmailException, MalformedURLException, UnknownHostException {
        // Cria o e-mail para ser enviado
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.addTo(entity.getEmail(), entity.getNome());
        email.setFrom("asilarpfc@gmail.com", "Asilar");
        email.setSubject("Criando Senha");
        //email.setSSL(true); testando um novo comando por esse ser Deprecated
        email.setSSLOnConnect(true);
        email.setAuthentication(username, senha);

// configura a mensagem para o formato HTML
        email.setHtmlMsg("<html><h1>Para cadastrar ou alterar uma senha, por favor clique no link abaixo<h1> <br> <a href='http://" + InetAddress.getLocalHost().getHostAddress() + ":8084/asilar/redefinir/" + entity.getId() + "/" + entity.getSenha() + "'>http://" + InetAddress.getLocalHost().getHostAddress() + ":8084/asilar/redefinir/" + entity.getId() + "/" + entity.getSenha() + " </a></html>");

// configure uma mensagem alternativa caso o servidor nÃ£o suporte HTML
        email.setTextMsg("Seu servidor de e-mail nÃ£o suporta mensagem HTML");

// envia o e-mail
        email.send();
    }

    private void lerConfig() {
        List<String> entrada = new ArrayList<String>();
        try {
            entrada = TXTPersist.readFromFile("d://config.txt");
        } catch (Exception ex) {
            try {
                entrada = TXTPersist.readFromFile("//opt//asilar//config.txt");
            } catch (Exception ex1) {
                System.out.println("Não foi possivel acessar as configurações do email");
                ex.printStackTrace();
                ex1.printStackTrace();
            }
        }
        for (String linha : entrada) {
            String[] config = linha.split("=");
            for (int i = 0; i < config.length; i++) {
                if (config[i].equals("username")) {
                    username = config[i + 1];
                } else if (config[i].equals("senha")) {
                    senha = config[i + 1];
                }
            }
        }
    }
}
