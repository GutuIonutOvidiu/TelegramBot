import Etereum.Ethereum_Blockchain;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
public class MyTelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        String commands = update.getMessage().getText();

        // Create a response object object
        SendMessage response = new SendMessage();

        switch (commands){
            case "/eth_1_minute_volume":
                String message_text0 = "Please wait for data processing, approximately 1 minute" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message_text0);
                try {
                    execute(response); // Sending our response object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                int ethPrice = 2000;
                String value  ;

                try {
                    value = Ethereum_Blockchain.getValueOfAllTransactionsInLastMinute();
                } catch (Exception e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                String message_text1 = "The eth transacted in last minute is: " + value + " -->> " + (ethPrice * Double.parseDouble(value)) + " dollars" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message_text1);
                break;

            default:
                String message_text2 = "Invalid command, enter a valid command" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message_text2);
                break;
        }

        try {
            execute(response); // Sending our response object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "Scanner_eth_bot";
    }
    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "5879103886:AAEbQ_jQneyhCRKjTOX1P62dwxn6HI8fBpY";
    }

}
