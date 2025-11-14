// Interface que define o contrato de um método de pagamento
interface PaymentMethod {
    void process(double amount);
}

// Implementação para pagamento com cartão de crédito
class CreditCardPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

// Implementação para pagamento com PayPal
class PayPalPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

// Classe responsável apenas por enviar e-mails
class EmailService {
    public void sendConfirmationEmail(String paymentType, double amount) {
        System.out.println("Email sent confirming " + paymentType + " payment of $" + amount);
    }
}

// Classe que processa pagamentos usando injeção de dependência
class PaymentProcessor {
    private final PaymentMethod paymentMethod;
    private final EmailService emailService;

    // Injeção de dependência via construtor
    public PaymentProcessor(PaymentMethod paymentMethod, EmailService emailService) {
        this.paymentMethod = paymentMethod;
        this.emailService = emailService;
    }

    public void processPayment(String paymentType, double amount) {
        paymentMethod.process(amount);
        emailService.sendConfirmationEmail(paymentType, amount);
    }
}

// Classe principal (Main)
public class Main {
    public static void main(String[] args) {
        // Cria as dependências
        PaymentMethod creditCard = new CreditCardPayment();
        PaymentMethod paypal = new PayPalPayment();
        EmailService emailService = new EmailService();

        // Injeta dependências no processador
        PaymentProcessor processor1 = new PaymentProcessor(creditCard, emailService);
        processor1.processPayment("credit card", 250.00);

        System.out.println();

        PaymentProcessor processor2 = new PaymentProcessor(paypal, emailService);
        processor2.processPayment("PayPal", 150.00);
    }
}

/* Refatore esse código criado:
        com uma interface paymentmethod com um metodo rocess(double amount).
        Duas implementações: CreditCardPayment e PayPalPayment.
        uma classe EmailService separada.
        Um novo PaymentProcessor que use injeção de dependência para
        escolher o método de pagamento e o serviço de e-mail.
        o envio do email pode ser alterado sem mexer no processo de pagamento.
        Garanta que cada classe tenha uma responsabilidade.

        garanta que: cada classe tenha uma única responsabilidade. o PaymentProcessor
        não precise saber como o pagamento é feito, apenas que ele pode ser feito.
        o envio de email pode ser alterado sem mexer no processo de pagamento.
 */