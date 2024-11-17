package com.gerenciamento.backend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DonativosApplication {
    public static void main(String[] args) {
        System.out.println("Iniciando Gerenciador de Donativos");
        try {
            SpringApplication.run(DonativosApplication.class, args);
            System.out.println("Gerenciador de Donativos iniciado com sucesso");
        } catch (Exception e) {
            System.err.println("Erro ao Iniciar o Gerenciador de Donativos: " + e.getMessage());
        }
    }
}